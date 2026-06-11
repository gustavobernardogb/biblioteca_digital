<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Livro - Biblioteca Digital</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1 class="title">Cadastrar Livro</h1>
        <p class="subtitle">Adicione um novo exemplar ao acervo</p>

        <form method="post" action="livro">
            <div class="form-group">
                <label for="nome">Título do Livro</label>
                <input type="text" id="nome" name="nome" placeholder="Ex: Dom Casmurro" required>
            </div>

            <div class="form-group">
                <label for="genero">Gênero Literário</label>
                <input type="text" id="genero" name="genero" placeholder="Ex: Romance, Ficção" required>
            </div>

            <div class="form-group">
                <label for="localizacao">Localização (Estante/Prateleira)</label>
                <input type="text" id="localizacao" name="localizacao" placeholder="Ex: Estante A, Prateleira 3" required>
            </div>

            <button type="submit" class="btn-submit">Salvar Livro</button>
        </form>

        <a href="index.jsp" class="footer-link">← Voltar ao menu principal</a>
    </div>
</body>
</html>
