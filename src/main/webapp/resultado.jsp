<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado da Operação - Biblioteca Digital</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${requestScope.tipo == 'sucesso'}">
                <div class="status-card success">
                    <h2>✓ Operação Concluída</h2>
                    <p>${requestScope.mensagem}</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="status-card error">
                    <h2>⚠️ Falha na Operação</h2>
                    <p>${requestScope.mensagem}</p>
                </div>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty requestScope.detalhes}">
            <div class="details">
                <p>${requestScope.detalhes}</p>
            </div>
        </c:if>

        <div style="display: flex; flex-direction: column; gap: 10px; margin-top: 1.5rem;">
            <a href="${requestScope.linkVoltar}" class="btn-submit" style="text-align: center; text-decoration: none;">
                ${not empty requestScope.textoVoltar ? requestScope.textoVoltar : 'Tentar Novamente'}
            </a>
            <a href="index.jsp" class="footer-link">← Voltar ao menu principal</a>
        </div>
    </div>
</body>
</html>
