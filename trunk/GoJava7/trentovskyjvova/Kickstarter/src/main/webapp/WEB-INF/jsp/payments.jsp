<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />

<h3>Payments:</h3>

<c:forEach var="reward" items="${rewards}">
	<a href="pledge?rewardId=${reward.getId()}&projectId=${projectId}">Pledge
		${reward.getPledge()} - get ${reward.getBenefit()}</a>
	<br>
</c:forEach>

<a href="pledge?rewardId=0&projectId=${projectId}">own amount</a>
<br>

<jsp:include page="footer.jsp" />