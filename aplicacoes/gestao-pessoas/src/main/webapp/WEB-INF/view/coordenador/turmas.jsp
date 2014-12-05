<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../modulos/header-estrutura.jsp" />

<title>Turmas</title>
</head>
<body>
	<jsp:include page="../modulos/header-coordenador.jsp" />

	<div class="container">
		<div class="tab-pane active" id="meus-projetos">
			<div align="right" style="margin-bottom: 20px;">
				<a href="<c:url value="/coordenador/turma" ></c:url>">
					<button class="btn btn-primary">Nova Turma <span class="glyphicon glyphicon-plus"></span></button>
				</a>
			</div>
			<c:if test="${empty turmas}">
				<div class="alert alert-warning" role="alert">Não há Turmas cadastrados.</div>
			</c:if>
			<c:if test="${not empty turmas}">
				<div class="panel panel-default">
					<div class="panel-heading" align="center">
						<h4>Turmas Cadastrados</h4>
					</div>
					<!-- Table -->
					<table class="table" id="table">
						<thead>
							<tr>
								<th>Turma</th>
								<th>Supervisor</th>
								<th>A</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="turma" items="${turmas}">
								<tr class="linha">
									<td>${turma.codigo}</td>
									<td>${tuma.supervisor}</td>
									<td>
										<a href="<c:url value="/coordenador/${projeto.id}/vincularParticipantes" />" class="btn btn-default glyphicon glyphicon-plus"> <span class="glyphicon glyphicon-user"></span></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>