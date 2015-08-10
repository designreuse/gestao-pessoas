<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
	<head>
		<jsp:include page="../modulos/header-estrutura1.jsp" />
		<title>Informações da Turma</title>
	</head>
<body>
	<jsp:include page="../modulos/header1.jsp" />
	
	<div class="container">
		<div class="tab-pane active" id="meus-projetos">
			<c:if test="${empty turma}"><div class="alert alert-warning" role="alert">Turma inexistente.</div></c:if>

			<c:if test="${not empty turma}">
				<h3 align="left" style="border-bottom: 1px solid #333;">Informações da Turma</h3>

				<div class="form-group">
					<label class="col-sm-1">Supervisor: </label><label>${turma.supervisor.nome}</label>
				</div>

				<div class="form-group">
					<label class="col-sm-1">Turma: </label><label>${turma.nome}</label>
				</div>

				<div class="form-group">
					<label class="col-sm-1">Semestre: </label><label>${turma.periodo.ano}.${turma.periodo.semestre}</label>
				</div>
	
				<h4 align="left" style="border-bottom: 1px solid #333;">Estagiarios</h4>
	
				<c:if test="${empty turma.estagiarios}"><div class="alert alert-warning" role="alert">Não há Estagiarios vinculados a esta turma.</div></c:if>
	
				<c:if test="${not empty turma.estagiarios}">
					<table id="membros-projeto" class="table table-striped">
						<thead>
							<tr class="">
								<th class="col-sm-1">Matrícula</th>
								<th class="col-sm-5">Nome</th>
				           </tr>
				       </thead>
		
				       <tbody class="panel">
							<c:forEach var="estagiario" items="${turma.estagiarios}">
								<tr class="linha">
									<td>${estagiario.matricula}</td>
									<td>${estagiario.pessoa.nome}</td>
								</tr>
							</c:forEach>
				       </tbody>
					</table>
				</c:if>
			</c:if>
		</div>
	</div>

	
	<jsp:include page="../modulos/footer1.jsp" />
	
    <script type="text/javascript">
		$(document).ready(function(){
			$(".menu #turmas").addClass("active");
		});
	</script>	

</body>
</html>