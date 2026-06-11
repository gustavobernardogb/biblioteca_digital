package br.com.bibliotecadigital.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void deveCriarUsuarioComEmailValido() {
        Usuario usuario = new Usuario("Gustavo", "gustavo@example.com", "senha123");
        assertEquals("gustavo@example.com", usuario.getEmail());
    }

    @Test
    public void deveLancarExcecaoParaEmailSemArroba() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Gustavo", "gustavoexample.com", "senha123");
        });
        assertEquals("E-mail inválido.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoParaEmailNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Gustavo", null, "senha123");
        });
        assertEquals("E-mail inválido.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoAlterarParaEmailInvalido() {
        Usuario usuario = new Usuario("Gustavo", "gustavo@example.com", "senha123");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuario.setEmail("invalido");
        });
        assertEquals("E-mail inválido.", exception.getMessage());
    }
}
