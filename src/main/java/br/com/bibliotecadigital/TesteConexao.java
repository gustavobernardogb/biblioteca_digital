package br.com.bibliotecadigital;

import br.com.bibliotecadigital.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class TesteConexao {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        System.out.println("Conectado com sucesso!");

        em.close();
    }
}