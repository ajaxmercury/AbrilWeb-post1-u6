<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty cookie.idiomaPreferido ? cookie.idiomaPreferido.value : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="lista.nueva"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="cabecera">
        <div class="saludo">
            <fmt:message key="lista.bienvenida"/>, <c:out value="${sessionScope.usuarioActual.nombreCompleto}"/> 
            [<c:out value="${sessionScope.usuarioActual.rol}"/>]
            | <a href="${pageContext.request.contextPath}/app?comando=logout"><fmt:message key="lista.salir"/></a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=es">Español</a> | 
            <a href="${pageContext.request.contextPath}/app?comando=idioma&lang=en">English</a>
        </div>
    </div>

    <h1><fmt:message key="lista.nueva"/></h1>
    
    <c:if test="${not empty errores}">
        <div class="alert-error">Por favor, corrija los errores en el formulario.</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/app" method="POST">
        <input type="hidden" name="comando" value="guardar">
        
        <div class="form-group">
            <label for="titulo"><fmt:message key="lista.titulo"/>:</label>
            <input type="text" id="titulo" name="titulo" class="${not empty errores.titulo ? 'input-error' : ''}" 
                   value="<c:out value='${titulo}'/>" maxlength="${applicationScope.maxLongitudTitulo}">
            <c:if test="${not empty errores.titulo}">
                <div class="campo-error"><c:out value="${errores.titulo}"/></div>
            </c:if>
        </div>
        
        <div class="form-group">
            <label for="categoria"><fmt:message key="lista.categoria"/>:</label>
            <input type="text" id="categoria" name="categoria" class="${not empty errores.categoria ? 'input-error' : ''}" 
                   value="<c:out value='${categoria}'/>">
            <c:if test="${not empty errores.categoria}">
                <div class="campo-error"><c:out value="${errores.categoria}"/></div>
            </c:if>
        </div>
        
        <div class="form-group">
            <label for="prioridad"><fmt:message key="lista.prioridad"/>:</label>
            <select id="prioridad" name="prioridad" class="${not empty errores.prioridad ? 'input-error' : ''}">
                <option value="Alta" ${prioridad == 'Alta' ? 'selected' : ''}>Alta</option>
                <option value="Media" ${prioridad == 'Media' ? 'selected' : ''}>Media</option>
                <option value="Baja" ${prioridad == 'Baja' ? 'selected' : ''}>Baja</option>
            </select>
            <c:if test="${not empty errores.prioridad}">
                <div class="campo-error"><c:out value="${errores.prioridad}"/></div>
            </c:if>
        </div>
        
        <div class="form-group">
            <label for="fechaLimite"><fmt:message key="lista.fecha"/>:</label>
            <input type="date" id="fechaLimite" name="fechaLimite" class="${not empty errores.fechaLimite ? 'input-error' : ''}" 
                   value="<c:out value='${fechaLimite}'/>">
            <c:if test="${not empty errores.fechaLimite}">
                <div class="campo-error"><c:out value="${errores.fechaLimite}"/></div>
            </c:if>
        </div>
        
        <button type="submit" class="btn">Guardar</button>
        <a href="${pageContext.request.contextPath}/app?comando=listar" class="btn">Cancelar</a>
    </form>
</body>
</html>
