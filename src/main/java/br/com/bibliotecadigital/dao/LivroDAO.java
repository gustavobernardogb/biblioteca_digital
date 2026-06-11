package br.com.bibliotecadigital.dao;

import br.com.bibliotecadigital.model.Livro;
import br.com.bibliotecadigital.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * DAO responsável pelas operações de persistência da entidade Livro.
 */
public class LivroDAO {

    /**
     * Salva um novo livro no banco de dados.
     * @param livro Objeto Livro a ser persistido.
     */
    public void salvar(Livro livro) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Lista todos os livros cadastrados no acervo.
     * @return Lista de livros ordenada pelo nome.
     */
    public List<Livro> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l ORDER BY l.nome", Livro.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}