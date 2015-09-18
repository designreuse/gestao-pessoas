<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
	<head>
		<jsp:include page="../modulos/header-estrutura1.jsp" />
		<title>Atualizar Membros do Projeto</title>
	</head>
<body>
	<jsp:include page="../modulos/header1.jsp" />
	
<div class="container">
	<div class="row">
	<div class="panel panel-success">

		<div class="panel-heading">
			<h2 class="titulo-panels"><span class="fa fa-briefcase"></span> Atualizar Membros</h2>
			
			<div class="pull-right">
				<a title="Voltar" class="btn btn-success back"><span class="fa fa-arrow-circle-o-left"></span> Voltar</a>
			</div>
		</div>

		<c:if test="${empty estagiarios }">
			<div class="panel-body">
				<div class="alert alert-warning" role="alert">Não há estagiários.</div>
			</div>
		</c:if>
		
		<c:if test="${not empty estagiarios }">
			<form:form id="vincularParcicipanteForm" role="form" modelAttribute="projeto" servletRelativeAction="/supervisor/projeto/${projeto.id}/vincular" method="POST" cssClass="">
				<form:hidden path="id"/>
				<form:hidden path="nome"/>
				<form:hidden path="descricao"/>

				<div class="panel-body">
					<table id="table-membros" class="table table-striped table-hover">
						<thead>
							<tr>
								<th class="col-md-5">Membros</th>
								<th class="col-md-1">Matrícula</th>
								<th class="col-md-3">Curso</th>
								<th class="col-md-3">Local Estágio</th>
				           </tr>
				       </thead>
				       <tbody>
							<c:forEach var="estagiario" items="${estagiarios}" varStatus="cont">
								<c:set var="membroDoProjeto" value="${estagiario.projeto.id eq projeto.id ? true : false}"></c:set>
								<c:if test="${membroDoProjeto}">
									<tr>
										<td>
											<form:checkbox id="membro${cont.index}" path="membros[${cont.index}].id" value="${estagiario.id}" checked="checked"/>
											<label for="membro${cont.index}">${estagiario.nomeCompleto}</label>
										</td>
										<td>${estagiario.matricula}</td>
										<td>${estagiario.curso.labelCurso}</td>
										<td>${estagiario.localEstagio.labelLocal}</td>
									</tr>
								</c:if>
							</c:forEach>
				       </tbody>
					</table>
				</div>
				

				<div class="panel-body">
					<table id="table-vincular-membros" class="table table-striped table-hover">
						<thead>
							<tr>
								<th class="col-md-5">Selecionar Membros</th>
								<th class="col-md-1">Matrícula</th>
								<th class="col-md-3">Curso</th>
								<th class="col-md-3">Local Estágio</th>
				           </tr>
				       </thead>
				       <tbody>
							<c:forEach var="estagiario" items="${estagiarios}" varStatus="cont">
								<c:set var="membroDoProjeto" value="${estagiario.projeto.id eq projeto.id ? true : false}"></c:set>
								<c:if test="${not membroDoProjeto}">
									<tr>
										<td>
											<form:checkbox id="membro${cont.index}" path="membros[${cont.index}].id" value="${estagiario.id}"/>
											<label for="membro${cont.index}">${estagiario.nomeCompleto}</label>
										</td>
										<td>${estagiario.matricula}</td>
										<td>${estagiario.curso.labelCurso}</td>
										<td>${estagiario.localEstagio.labelLocal}</td>
									</tr>
								</c:if>
							</c:forEach>
				       </tbody>
					</table>
				</div>
				<div class="panel-footer" align="center">
					<div class="controls">
						<button type="submit" class="btn btn-success" title="Atualizar vínculos"><span class="fa fa-refresh"></span> Atualizar vínculos</button>
					</div>
				</div>
	       </form:form>
		</c:if>
	</div>
	</div>
</div>

	<jsp:include page="../modulos/footer1.jsp" />

    <script type="text/javascript">
		$(".menu #menu-projetos").addClass("active");
		
		$('#table-vincular-membros').DataTable({
			 "pageLength": 10,
			"language": ptBR,
			 "order": [0, 'asc'],
			 "columnDefs": [
				{ "orderable": false, "targets": 0 },
			],
		});

		$('.dataTables_length label').addClass('text-view-info');
		$('.dataTables_filter label').addClass('text-view-info');
		
	</script>	

</body>
</html>