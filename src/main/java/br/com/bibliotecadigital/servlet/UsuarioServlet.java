package br.com.bibliotecadigital.servlet;

import br.com.bibliotecadigital.dao.UsuarioDAO;
import br.com.bibliotecadigital.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("usuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            Usuario usuario = new Usuario(nome, email, senha);
            usuarioDAO.salvar(usuario);

            req.setAttribute("tipo", "sucesso");
            req.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
            req.setAttribute("detalhes", "O leitor <strong>" + usuario.getNome() + "</strong> foi registrado com o e-mail: " + usuario.getEmail());
            req.setAttribute("linkVoltar", "usuario");
            req.setAttribute("textoVoltar", "Cadastrar Outro");
        } catch (IllegalArgumentException e) {
            req.setAttribute("tipo", "erro");
            req.setAttribute("mensagem", "Erro de validação: " + e.getMessage());
            req.setAttribute("linkVoltar", "usuario");
            req.setAttribute("textoVoltar", "Tentar Novamente");
        } catch (Exception e) {
            req.setAttribute("tipo", "erro");
            req.setAttribute("mensagem", "Ocorreu um erro no servidor: " + e.getMessage());
            req.setAttribute("linkVoltar", "usuario");
            req.setAttribute("textoVoltar", "Tentar Novamente");
        }

        req.getRequestDispatcher("resultado.jsp").forward(req, resp);
    }
}