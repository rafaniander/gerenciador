<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:if test="${not empty usuarioLogado}">
			Usuário logado: ${usuarioLogado.email}
		</c:if>
	</body>
</html>