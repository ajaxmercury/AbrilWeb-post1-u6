<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nueva Tarea</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <h1>Nueva Tarea</h1>
    
    <c:if test="${not empty error}">
        <div style="color: red; margin-bottom: 10px;"><c:out value="${error}"/></div>
    </c:if>

    <form action="${pageContext.request.contextPath}/app" method="POST">
        <input type="hidden" name="comando" value="guardar">
        
        <div class="form-group">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" required maxlength="${applicationScope.maxLongitudTitulo}">
        </div>
        
        <div class="form-group">
            <label for="categoria">Categoría:</label>
            <input type="text" id="categoria" name="categoria" required>
        </div>
        
        <div class="form-group">
            <label for="prioridad">Prioridad:</label>
            <select id="prioridad" name="prioridad">
                <option value="Alta">Alta</option>
                <option value="Media">Media</option>
                <option value="Baja">Baja</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="fechaLimite">Fecha límite:</label>
            <input type="date" id="fechaLimite" name="fechaLimite" required>
        </div>
        
        <button type="submit" class="btn">Guardar</button>
        <a href="${pageContext.request.contextPath}/app?comando=listar" class="btn">Cancelar</a>
    </form>
</body>
</html>
