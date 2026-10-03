<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty cookie.idiomaPreferido ? cookie.idiomaPreferido.value : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.titulo"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div style="text-align: right; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=es">Español</a> | 
        <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=en">English</a>
    </div>

    <h1><fmt:message key="login.titulo"/></h1>

    <c:if test="${not empty errorLogin}">
        <div class="alert-error"><c:out value="${errorLogin}"/></div>
    </c:if>

    <form action="${pageContext.request.contextPath}/app" method="POST">
        <input type="hidden" name="comando" value="login">
        
        <div class="form-group">
            <label for="username"><fmt:message key="login.usuario"/>:</label>
            <input type="text" id="username" name="username" required>
        </div>
        
        <div class="form-group">
            <label for="clave"><fmt:message key="login.clave"/>:</label>
            <input type="password" id="clave" name="clave" required>
        </div>
        
        <button type="submit" class="btn"><fmt:message key="login.entrar"/></button>
    </form>
</body>
</html>
