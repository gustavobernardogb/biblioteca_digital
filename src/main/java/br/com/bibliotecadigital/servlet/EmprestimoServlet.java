package br.com.bibliotecadigital.servlet;

import br.com.bibliotecadigital.dao.EmprestimoDAO;
import br.com.bibliotecadigital.dao.LivroDAO;
import br.com.bibliotecadigital.dao.UsuarioDAO;
import br.com.bibliotecadigital.model.Emprestimo;
import br.com.bibliotecadigital.model.Livro;
import br.com.bibliotecadigital.model.Usuario;
import br.com.bibliotecadigital.service.EmprestimoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Servlet responsável pelo registro de empréstimos de livros.
 * Utiliza e-mail e senha do usuário para autenticação e dropdown de livros para seleção.
 */
@WebServlet("/emprestimo")
public class EmprestimoServlet extends HttpServlet {

    private final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final EmprestimoService emprestimoService = new EmprestimoService();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Carrega a lista de livros disponíveis e encaminha para a tela de empréstimo.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Busca todos os livros para popular o dropdown
        List<Livro> livros = livroDAO.listar();
        req.setAttribute("livros", livros);
        req.getRequestDispatcher("emprestimo.jsp").forward(req, resp);
    }

    /**
     * Processa o formulário de empréstimo.
     * Autentica o usuário por e-mail e senha, valida o livro selecionado e cria o empréstimo.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String livroIdStr = req.getParameter("livroId");
        String dataEmprestimoStr = req.getParameter("dataEmprestimo");

        try {
            // Autentica o usuário por e-mail e senha
            Usuario usuario = usuarioDAO.buscarPorEmail(email);

            if (usuario == null || !usuario.getSenha().equals(senha)) {
                req.setAttribute("tipo", "erro");
                req.setAttribute("mensagem", "E-mail ou senha incorretos.");
                req.setAttribute("detalhes", "Verifique suas credenciais e tente novamente.");
                req.setAttribute("linkVoltar", "emprestimo");
                req.setAttribute("textoVoltar", "Tentar Novamente");
                req.getRequestDispatcher("resultado.jsp").forward(req, resp);
                return;
            }

            // Busca o livro selecionado no dropdown
            Long livroId = Long.parseLong(livroIdStr);
            LocalDate dataEmprestimo = LocalDate.parse(dataEmprestimoStr);

            Livro livro = livroDAO.listar().stream()
                .filter(l -> l.getId().equals(livroId))
                .findFirst().orElse(null);

            if (livro == null) {
                req.setAttribute("tipo", "erro");
                req.setAttribute("mensagem", "Livro não encontrado.");
                req.setAttribute("linkVoltar", "emprestimo");
                req.setAttribute("textoVoltar", "Tentar Novamente");
                req.getRequestDispatcher("resultado.jsp").forward(req, resp);
                return;
            }

            // Cria o empréstimo com prazo de 10 dias
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setUsuario(usuario);
            emprestimo.setLivro(livro);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataPrevistaDevolucao(emprestimoService.calcularDataDevolucao(dataEmprestimo));
            emprestimo.setMulta(0.0);

            emprestimoDAO.salvar(emprestimo);

            req.setAttribute("tipo", "sucesso");
            req.setAttribute("mensagem", "Empréstimo registrado com sucesso!");
            req.setAttribute("detalhes",
                "Leitor: <strong>" + usuario.getNome() + "</strong><br>" +
                "Livro: <strong>" + livro.getNome() + "</strong><br>" +
                "Data do empréstimo: " + dataEmprestimo.format(FORMATTER) + "<br><br>" +
                "<span style='color: var(--accent); font-weight: bold;'>" +
                "📅 Devolver até: " + emprestimo.getDataPrevistaDevolucao().format(FORMATTER) +
                "</span>");
            req.setAttribute("linkVoltar", "emprestimo");
            req.setAttribute("textoVoltar", "Novo Empréstimo");

        } catch (Exception e) {
            req.setAttribute("tipo", "erro");
            req.setAttribute("mensagem", "Erro ao registrar empréstimo: " + e.getMessage());
            req.setAttribute("linkVoltar", "emprestimo");
            req.setAttribute("textoVoltar", "Tentar Novamente");
        }

        req.getRequestDispatcher("resultado.jsp").forward(req, resp);
    }
}