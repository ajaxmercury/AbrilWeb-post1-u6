<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${applicationScope.nombreApp}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <h1>${applicationScope.nombreApp}</h1>
    
    <a href="${pageContext.request.contextPath}/app?comando=formulario" class="btn">+ Nueva tarea</a>
    
    <table>
        <thead>
            <tr>
                <th>Título</th>
                <th>Categoría</th>
                <th>Prioridad</th>
                <th>Fecha límite</th>
                <th>Estado</th>
                <th>Acciones</th>
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
                            <c:when test="${tarea.completada}">Completada</c:when>
                            <c:otherwise>Pendiente</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${!tarea.completada}">
                            <a href="${pageContext.request.contextPath}/app?comando=completar&id=${tarea.id}">Completar</a> |
                        </c:if>
                        <a href="${pageContext.request.contextPath}/app?comando=eliminar&id=${tarea.id}" 
                           class="btn-danger" style="padding: 2px 5px; text-decoration: none; color: white; border-radius: 3px;"
                           onclick="return confirm('¿Eliminar esta tarea?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
