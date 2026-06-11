<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Empréstimo - Biblioteca Digital</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1 class="title">Empréstimo de Livro</h1>
        <p class="subtitle">Identifique o leitor e selecione o livro desejado</p>

        <form method="post" action="emprestimo">

            <%-- Seção: Identificação do Leitor --%>
            <div class="section-label">👤 Identificação do Leitor</div>

            <div class="form-group">
                <label for="email">E-mail do Leitor</label>
                <input type="email" id="email" name="email"
                       placeholder="Ex: joao@email.com" required>
            </div>

            <div class="form-group">
                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha"
                       placeholder="••••••••" required>
            </div>

            <div class="divider"></div>

            <%-- Seção: Seleção do Livro --%>
            <div class="section-label">📚 Seleção do Livro</div>

            <div class="form-group">
                <label for="livroId">Livro do Acervo</label>
                <c:choose>
                    <c:when test="${empty livros}">
                        <p class="info-box">Nenhum livro cadastrado ainda. <a href="livro">Cadastrar livro</a>.</p>
                    </c:when>
                    <c:otherwise>
                        <select id="livroId" name="livroId" required>
                            <option value="" disabled selected>— Selecione um livro —</option>
                            <c:forEach var="livro" items="${livros}">
                                <option value="${livro.id}">
                                    ${livro.nome} &nbsp;(${livro.genero} · ${livro.localizacao})
                                </option>
                            </c:forEach>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="form-group">
                <label for="dataEmprestimo">Data do Empréstimo</label>
                <input type="date" id="dataEmprestimo" name="dataEmprestimo" required>
            </div>

            <button type="submit" class="btn-submit">Registrar Empréstimo</button>
        </form>

        <script>
            // Define a data atual como padrão
            document.getElementById('dataEmprestimo').valueAsDate = new Date();
        </script>

        <a href="index.jsp" class="footer-link">← Voltar ao menu principal</a>
    </div>
</body>
</html>
