<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="nome" value="${sessionScope.usuario.nome}"/>
<c:set var="nomeFormatado" value="${fn:substring(nome, 0, fn:indexOf(nome, ' '))}" />

<html>
	<head>
		<jsp:include page="../modulos/header-estrutura1.jsp" />
		<title>Minha Presença</title>
	</head>
<body>
	<jsp:include page="../modulos/header1.jsp" />

<div class="container">
	<div class="row">
	
	<c:if test="${not frequenciaNaoRealizada}">
	<div class="alert alert-dismissible alert-success">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h5><strong>Parabéns! ${nomeFormatado},</strong> você esta presente no NPI. Tenha um Ótimo dia de trabalho <i class="fa fa-smile-o fa-2x"></i></h5>
	</div>
	</c:if>

	<c:if test="${liberarPresenca}">
	<div class="panel panel-info">
		<div class="panel-heading">
			<form class="form-inline form-minha-presenca" action="<c:url value="/estagiari/minha-presenca"></c:url>" method="POST" align="center">
				<c:if test="${not empty error}">
					<div class="form-group" align="center">
						<label class="control-label col-xs-2"></label>
						<div class="col-xs-10"><div class="form-control label label-danger"><i class="fa fa-times-circle-o"></i> ${error}</div></div>
					</div>
				</c:if>
				<fieldset class="form-group">
					<label class="panel-title"><strong>Minha Presença</strong></label>
				</fieldset>
				<div class="form-group">
					<label for="cpf" class="sr-only">CPF</label>
					<input type="text" class="form-control" id="cpf" name="cpf" placeholder="CPF" value="${estagiario.pessoa.cpf}" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="senha" class="sr-only">Senha</label>
					<input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" autofocus="autofocus">
				</div>
				<button type="submit" class="btn btn-success">Estou Presente !!!</button>
			</form>
		  </div>
		</div>
	</c:if>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h2 id="titulo-cadastro-npi"><a class="header-anchor" href="#"><span class="glyphicon glyphicon-user"></span></a> Frequência</h2>
		</div>

		<div class="panel-body">
			<div>
				<label>Data: ${frequenciaHoje.data}</label> 
				<label>Hora: ${frequenciaHoje.tempo}</label> 
				<label>Faltas: ${dadosConsolidados.faltas}</label> 
				<label>Dias Trabalhados: ${dadosConsolidados.diasTrabalhados}</label> 
				<label>Frequência (%): ${dadosConsolidados.porcentagemFrequencia}</label>
			</div>
			
			<c:if test="${not empty message}"><div class="alert alert-info msg"><i class="fa fa-info-circle"> </i> ${message}</div></c:if>

			<table id="periodos" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Data</th>
						<th>Observação</th>
						<th>Status</th>
					</tr>
		       </thead>
		       <tbody class="text-view-info">
					<c:forEach var="frequencia" items="${frequencias}">
						<tr>
							<td><fmt:formatDate value="${frequencia.data}" pattern="dd/MM/yyyy" /></td>
							<td>${frequencia.observacao}</td>
							<td>${frequencia.statusFrequencia}</td>
						</tr>
					</c:forEach>
		       </tbody>
			</table>
		</div>
	</div>
	</div>
</div><br><br>

	<jsp:include page="../modulos/footer1.jsp" />
	<script src="<c:url value="/resources/js/frequencia.js" />"></script>

</body>