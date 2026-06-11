<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Devolução - Biblioteca Digital</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1 class="title">Devolução de Livro</h1>
        <p class="subtitle">Selecione o empréstimo ativo para registrar a devolução</p>

        <form method="post" action="devolucao">

            <div class="form-group">
                <label for="emprestimoId">Empréstimo Ativo</label>
                <c:choose>
                    <c:when test="${empty emprestimosAtivos}">
                        <p class="info-box">✅ Nenhum empréstimo pendente de devolução no momento.</p>
                    </c:when>
                    <c:otherwise>
                        <select id="emprestimoId" name="emprestimoId" required>
                            <option value="" disabled selected>— Selecione o empréstimo —</option>
                            <c:forEach var="emp" items="${emprestimosAtivos}">
                                <option value="${emp.id}">
                                    📖 ${emp.livro.nome} &nbsp;→&nbsp; ${emp.usuario.nome}
                                    &nbsp;(devolver até ${emp.dataPrevistaDevolucao})
                                </option>
                            </c:forEach>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="form-group">
                <label for="dataDevolucaoReal">Data da Devolução Real</label>
                <input type="date" id="dataDevolucaoReal" name="dataDevolucaoReal" required>
            </div>

            <c:if test="${not empty emprestimosAtivos}">
                <button type="submit" class="btn-submit">Confirmar Devolução</button>
            </c:if>
        </form>

        <script>
            // Define a data atual como padrão
            document.getElementById('dataDevolucaoReal').valueAsDate = new Date();
        </script>

        <a href="index.jsp" class="footer-link">← Voltar ao menu principal</a>
    </div>
</body>
</html>
