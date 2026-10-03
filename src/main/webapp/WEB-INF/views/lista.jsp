<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty cookie.idiomaPreferido ? cookie.idiomaPreferido.value : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title>${applicationScope.nombreApp}</title>
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

    <h1>${applicationScope.nombreApp}</h1>
    
    <c:if test="${not empty error}">
        <div class="alert-error"><c:out value="${error}"/></div>
    </c:if>

    <a href="${pageContext.request.contextPath}/app?comando=formulario" class="btn"><fmt:message key="lista.nueva"/></a>
    
    <table>
        <thead>
            <tr>
                <th><fmt:message key="lista.titulo"/></th>
                <th><fmt:message key="lista.categoria"/></th>
                <th><fmt:message key="lista.prioridad"/></th>
                <th><fmt:message key="lista.fecha"/></th>
                <th><fmt:message key="lista.estado"/></th>
                <th><fmt:message key="lista.acciones"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tarea" items="${tareas}">
                <tr>
                    <td><c:out value="${tarea.titulo}" /></td>
                    <td><c:out value="${tarea.categoria}" /></td>
                    <td><c:out value="${tarea.prioridad}" /></td>
                    <td><fmt:formatDate value="${tarea.fechaLimite}" pattern="dd/MM/yyyy" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${tarea.completada}"><fmt:message key="tarea.completada"/></c:when>
                            <c:otherwise><fmt:message key="tarea.pendiente"/></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${!tarea.completada}">
                            <a href="${pageContext.request.contextPath}/app?comando=completar&id=${tarea.id}"><fmt:message key="lista.completar"/></a> |
                        </c:if>
                        <a href="${pageContext.request.contextPath}/app?comando=eliminar&id=${tarea.id}" 
                           class="btn-danger" style="padding: 2px 5px; text-decoration: none; color: white; border-radius: 3px;"
                           onclick="return confirm('¿Eliminar esta tarea?');"><fmt:message key="lista.eliminar"/></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
