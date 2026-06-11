package br.com.bibliotecadigital.servlet;

import br.com.bibliotecadigital.dao.EmprestimoDAO;
import br.com.bibliotecadigital.model.Emprestimo;
import br.com.bibliotecadigital.service.EmprestimoService;
import br.com.bibliotecadigital.util.JPAUtil;
import jakarta.persistence.EntityManager;
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
 * Servlet responsável pelo registro de devoluções de livros emprestados.
 * Apresenta os empréstimos ativos em um dropdown para facilitar a seleção.
 */
@WebServlet("/devolucao")
public class DevolucaoServlet extends HttpServlet {

    private final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private final EmprestimoService emprestimoService = new EmprestimoService();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Carrega a lista de empréstimos ativos (sem devolução) e encaminha para a tela de devolução.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Busca apenas empréstimos sem devolução registrada
        List<Emprestimo> emprestimosAtivos = emprestimoDAO.listarAtivos();
        req.setAttribute("emprestimosAtivos", emprestimosAtivos);
        req.getRequestDispatcher("devolucao.jsp").forward(req, resp);
    }

    /**
     * Processa a devolução do livro selecionado, calcula multa se houver atraso.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long emprestimoId = Long.parseLong(req.getParameter("emprestimoId"));
            LocalDate dataDevolucaoReal = LocalDate.parse(req.getParameter("dataDevolucaoReal"));

            EntityManager em = JPAUtil.getEntityManager();
            Emprestimo emprestimo = em.find(Emprestimo.class, emprestimoId);

            if (emprestimo == null) {
                em.close();
                req.setAttribute("tipo", "erro");
                req.setAttribute("mensagem", "Empréstimo não encontrado.");
                req.setAttribute("detalhes", "Certifique-se de que o empréstimo selecionado é válido.");
                req.setAttribute("linkVoltar", "devolucao");
                req.setAttribute("textoVoltar", "Tentar Novamente");
                req.getRequestDispatcher("resultado.jsp").forward(req, resp);
                return;
            }

            // Calcula multa por atraso (R$ 10,00 se passar da data prevista)
            emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
            double multa = emprestimoService.calcularMulta(
                emprestimo.getDataPrevistaDevolucao(),
                dataDevolucaoReal
            );
            emprestimo.setMulta(multa);

            em.getTransaction().begin();
            em.merge(emprestimo);
            em.getTransaction().commit();
            em.close();

            String statusMultaClass = multa > 0 ? "com-multa" : "sem-multa";
            String msgMulta = multa > 0
                ? "⚠️ Devolução em atraso! Multa de R$ 10,00 aplicada."
                : "✅ Devolução no prazo! Sem multa.";

            String detalhes = String.format(
                "Livro: <strong>%s</strong><br>Leitor: <strong>%s</strong><br>" +
                "Data prevista: %s<br>Data real: %s<br><br>" +
                "<span class='multa-tag %s'>R$ %.2f</span><br><br>%s",
                emprestimo.getLivro().getNome(),
                emprestimo.getUsuario().getNome(),
                emprestimo.getDataPrevistaDevolucao().format(FORMATTER),
                dataDevolucaoReal.format(FORMATTER),
                statusMultaClass,
                multa,
                msgMulta
            );

            req.setAttribute("tipo", "sucesso");
            req.setAttribute("mensagem", "Devolução registrada com sucesso!");
            req.setAttribute("detalhes", detalhes);
            req.setAttribute("linkVoltar", "devolucao");
            req.setAttribute("textoVoltar", "Nova Devolução");

        } catch (Exception e) {
            req.setAttribute("tipo", "erro");
            req.setAttribute("mensagem", "Erro ao registrar devolução: " + e.getMessage());
            req.setAttribute("linkVoltar", "devolucao");
            req.setAttribute("textoVoltar", "Tentar Novamente");
        }

        req.getRequestDispatcher("resultado.jsp").forward(req, resp);
    }
}