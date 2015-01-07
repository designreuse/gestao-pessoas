<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../modulos/header-estrutura.jsp" />

<title>Projetos</title>
</head>
<body>
	<jsp:include page="../modulos/header-coordenador.jsp" />

	<div class="container">
		<div class="tab-pane active" id="meus-projetos">
			<div align="right" style="margin-bottom: 20px;">
				<a href="<c:url value="/turma/${turma.id}/vincularEstagiarios" ></c:url>">
					<button class="btn btn-primary">Add <span class="glyphicon glyphicon-user"></span></button>
				</a>
			</div>
		
			
			<label>Detalhes turma..: </label>
			
			<c:if test="${empty turma.estagiarios}">
				<div class="alert alert-warning" role="alert">Não há estagiarios vinculadas a esta turma.</div>
			</c:if>			
			
			<c:if test="${not empty turma.estagiarios}">
				<div class="panel panel-default">
					<div class="panel-heading" align="center">
						<h4>Estagiarios</h4>
					</div>
					<!-- Table -->
					<table class="table" id="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Matricula</th>
								<th>A</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="estagiario" items="${turma.estagiarios}">
								<tr class="linha">
									<td>${estagiario.pessoa.nome}</td>
									<td>${estagiario.matricula}</td>
									<td>
										<a href="<c:url value="/#" />" class="btn btn-default">Detalhes <span class="glyphicon glyphicon-user"></span></a>
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