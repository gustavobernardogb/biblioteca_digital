<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Usuário - Biblioteca Digital</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1 class="title">Cadastrar Usuário</h1>
        <p class="subtitle">Insira as informações do novo leitor</p>

        <form method="post" action="usuario">
            <div class="form-group">
                <label for="nome">Nome Completo</label>
                <input type="text" id="nome" name="nome" placeholder="Ex: João Silva" required>
            </div>

            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="email" placeholder="Ex: joao@email.com" required>
            </div>

            <div class="form-group">
                <label for="senha">Senha de Acesso</label>
                <input type="password" id="senha" name="senha" placeholder="••••••••" required>
            </div>

            <button type="submit" class="btn-submit">Salvar Cadastro</button>
        </form>

        <a href="index.jsp" class="footer-link">← Voltar ao menu principal</a>
    </div>
</body>
</html>
