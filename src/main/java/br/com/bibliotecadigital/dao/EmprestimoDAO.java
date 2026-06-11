package br.com.bibliotecadigital.dao;

import br.com.bibliotecadigital.model.Emprestimo;
import br.com.bibliotecadigital.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * DAO responsável pelas operações de persistência da entidade Emprestimo.
 */
public class EmprestimoDAO {

    /**
     * Salva um novo empréstimo no banco de dados.
     * @param emprestimo Objeto Emprestimo a ser persistido.
     */
    public void salvar(Emprestimo emprestimo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emprestimo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Lista os empréstimos que ainda não foram devolvidos (dataDevolucaoReal nula).
     * @return Lista de empréstimos ativos.
     */
    public List<Emprestimo> listarAtivos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NULL " +
                "ORDER BY e.dataEmprestimo DESC", Emprestimo.class)
                .getResultList();
        } finally {
            em.close();
        }
    }
}