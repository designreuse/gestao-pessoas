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
	<div class="row">

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h2 class="titulo-panels"><a class="header-anchor" href="#"><span class="fa fa-folder-open"></span></a> Turma ${turma.nome}</h2>
		</div>

		<div class="panel-body">
			<c:if test="${empty turma}"><div class="alert alert-warning" role="alert">Turma inexistente.</div></c:if>

			<c:if test="${not empty turma}">
				<div class="form-group">
					<label class="col-sm-1 text-view-info"><strong>Supervisor: </strong></label><label class="col-sm-11 text-view-info">${turma.supervisor.nome}</label>
				</div>

				<div class="form-group">
					<label class="col-sm-1 text-view-info"><strong>Semestre: </strong></label><label class="col-sm-3 text-view-info">${turma.ano}.${turma.semestre}</label>

					<label class="col-sm-1 text-view-info"><strong>Periodo: </strong></label><label class="col-sm-3 text-view-info">de ${turma.inicio} a ${turma.termino}</label>

					<label class="col-sm-1 text-view-info"><strong>Status: </strong></label><label class="col-sm-3 text-view-info">${turma.statusTurma}</label>
				</div>

				<div class="form-group">
					<label class="col-sm-12 text-view-info"><strong>Horários</strong></label>
				
					<table id="horarios-turma" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Horário</th>
								<th>Dia da Semana</th>
								<th>Início do Expediente</th>
								<th>Término do Expediente</th>
				           </tr>
				       </thead>
				       <tbody class="text-view-info">
							<c:forEach var="horario" items="${turma.horarios}" varStatus="indice">
								<tr align="justify">
									<td>${indice.count}ª</td>
									<td>${horario.dia.labelDia}</td>
									<td>${horario.inicioExpediente}</td>
									<td>${horario.finalExpediente}</td>
								</tr>
							</c:forEach>
				       </tbody>
			       </table>
					
				</div>
			</c:if>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h2 class="titulo-panels"><a class="header-anchor" href="#"><span class="fa fa-folder-open"></span></a> Expediente</h2>

			<div class="pull-right">
				<a href="<c:url value="/supervisor/turma/${turma.id}/horarios" />" title="Vincular Estagiários" class="btn btn-info"><span class="fa fa-plus-o"></span>Horario</a>
			</div>
		</div>

		<div class="panel-body">
			<c:if test="${empty turma.horarios}"><div class="alert alert-warning" role="alert">Expedoente não definido.</div></c:if>

			<c:if test="${not empty turma.horarios}">
				<div class="form-group">
					<label class="col-sm-12 text-view-info"><strong>Horários</strong></label>
				
					<table id="horarios-turma" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Horário</th>
								<th>Dia da Semana</th>
								<th>Início do Expediente</th>
								<th>Término do Expediente</th>
				           </tr>
				       </thead>
				       <tbody class="text-view-info">
							<c:forEach var="horario" items="${turma.horarios}" varStatus="indice">
								<tr align="justify">
									<td>${indice.count}ª</td>
									<td>${horario.dia.labelDia}</td>
									<td>${horario.inicioExpediente}</td>
									<td>${horario.finalExpediente}</td>
								</tr>
							</c:forEach>
				       </tbody>
			       </table>
					
				</div>
			</c:if>
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h2 class="titulo-panels"><a class="header-anchor" href="#"><span class="glyphicon glyphicon-user"></span></a> Estagiários</h2>

			<div class="pull-right">
				<a href="<c:url value="/supervisor/turma/${turma.id}/vincular" />" title="Vincular Estagiários" class="btn btn-success"><span class="glyphicon glyphicon-link"></span>&nbsp;</a>
			</div>
		</div>

		<div class="panel-body">
			<c:if test="${empty turma.estagiarios}"><div class="alert alert-warning" role="alert">Não há estagiários vinculados a esta turma.</div></c:if>

			<c:if test="${not empty turma.estagiarios}">
				<table id="estagiarios-turma" class="table table-striped table-hover">
					<thead>
						<tr>
							<th class="col-md-4">Nome</th>
							<th class="col-md-1">Matrícula</th>
							<th class="col-md-3">Curso</th>
							<th class="col-md-3">Local Estágio</th>
							<th class="col-md-1"></th>
			           </tr>
			       </thead>
			       <tbody class="text-view-info">
						<c:forEach var="estagiario" items="${turma.estagiarios}">
							<tr>
								<td>${estagiario.nomeCompleto}</td>
								<td>${estagiario.matricula}</td>
								<td>${estagiario.curso.labelCurso}</td>
								<td>${estagiario.localEstagio.labelLocal}</td>
								<td align="right">
									<a href="<c:url value="/supervisor/turma/${idTurma }/estagiario/${estagiario.id}/frequencia" />" title="Frequências" class="btn btn-info btn-sm"><span class="fa fa-calendar"></span> Frequências</a>
								</td>
							</tr>
						</c:forEach>
			       </tbody>
				</table>
			</c:if>
		</div>
		<c:if test="${not empty turma.estagiarios}">
			<div class="panel-footer" align="center">
				<div class="controls">
					<a class="btn btn-default" href="<c:url value="/supervisor/turma/${idTurma }/tce" ></c:url>" title="Termo de Compromisso"><i class="fa fa-file-pdf-o"></i> Termo de Compromisso</a>
					<a class="btn btn-primary" href="<c:url value="/supervisor/turma/${idTurma }/declaracoes" ></c:url>" title="Declaração de Estágio"><i class="fa fa-file-pdf-o"></i> Declaração de Estágio</a>
				</div>
			</div>
		</c:if>
	</div>
	
	</div>
</div>
<br><br>
	<jsp:include page="../modulos/footer1.jsp" />

    <script type="text/javascript">
		$(".menu #turmas").addClass("active");
	</script>	

</body>
</html>