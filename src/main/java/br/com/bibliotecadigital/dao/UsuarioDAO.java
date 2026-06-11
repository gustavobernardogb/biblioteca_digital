package br.com.bibliotecadigital.dao;

import br.com.bibliotecadigital.model.Usuario;
import br.com.bibliotecadigital.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO responsável pelas operações de persistência da entidade Usuario.
 */
public class UsuarioDAO {

    /**
     * Salva um novo usuário no banco de dados.
     * @param usuario Objeto Usuario a ser persistido.
     */
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     * @param email E-mail do usuário.
     * @return Usuario encontrado ou null se não existir.
     */
    public Usuario buscarPorEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            List<Usuario> resultado = query.getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);
        } finally {
            em.close();
        }
    }

    /**
     * Lista todos os usuários cadastrados.
     * @return Lista de usuários.
     */
    public List<Usuario> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u ORDER BY u.nome", Usuario.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}