package br.com.bibliotecadigital.servlet;

import br.com.bibliotecadigital.dao.LivroDAO;
import br.com.bibliotecadigital.model.Livro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/livro")
public class LivroServlet extends HttpServlet {

    private final LivroDAO livroDAO = new LivroDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("livro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String genero = req.getParameter("genero");
        String localizacao = req.getParameter("localizacao");

        try {
            Livro livro = new Livro(nome, genero, localizacao);
            livroDAO.salvar(livro);

            req.setAttribute("tipo", "sucesso");
            req.setAttribute("mensagem", "Livro cadastrado com sucesso!");
            req.setAttribute("detalhes", "O exemplar <strong>" + livro.getNome() + "</strong> (" + livro.getGenero() + ") foi catalogado em: " + livro.getLocalizacao());
            req.setAttribute("linkVoltar", "livro");
            req.setAttribute("textoVoltar", "Cadastrar Outro");
        } catch (Exception e) {
            req.setAttribute("tipo", "erro");
            req.setAttribute("mensagem", "Erro ao cadastrar livro: " + e.getMessage());
            req.setAttribute("linkVoltar", "livro");
            req.setAttribute("textoVoltar", "Tentar Novamente");
        }

        req.getRequestDispatcher("resultado.jsp").forward(req, resp);
    }
}