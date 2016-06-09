<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <!DOCTYPE html>
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <title></title>
            <meta name="description" content="">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="assets/bootstrap-3.3.5/css/bootstrap.min.css">
            <link rel="stylesheet" href="assets/bootstrap-3.3.5/css/bootstrap-theme.min.css">
            <link rel="stylesheet" href="assets/bootstrap-vertical-tabs/bootstrap.vertical-tabs.min.css">
            <link rel="stylesheet" href="assets/others.css">
        </head>
        <body>
            <input type="hidden" id="url" value="<%=request.getContextPath()%>"/>
            <!--[if lt IE 7]>
                <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            <![endif]-->
            <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header" >
                        <span class="navbar-brand">TCC 2 - Ferramenta para criação de ambientes gamificados</span>
                    </div>
                    <div class="nav navbar-nav navbar-right" style="margin-top: 1%;s">
                    </div>
                </div>
            </div>

            <!-- Main jumbotron for a primary marketing message or call to action -->
            <input type="hidden" id="url" value="<%=request.getContextPath()%>"/>
            <div class="jumbotron">
                <div class="container">
                    <div class="row">

                        <!-- ---------------- MESSAGES ---------------- -->
                        <div id="message-div" class="col-md-12"></div>
                        <!-- ---------------- COFIGURAÇÕES ---------------- -->
                        <div class="col-md-12" >
                            <s:form id="connectionForm" namespace="/" action="index" cssClass="form-horizontal" method="post" theme="simple">
                                <div class="panel panel-default">
                                    <div class="panel-heading" style="padding: 2px 30px;">
                                        <h4 class="panel-title">
                                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseConfig" aria-expanded="true" aria-controls="collapseOne">
                                                <label style='margin-bottom: 0px; padding-top: 8px; padding-bottom: 7px; cursor: pointer; width: 50%'>
                                                    <span class="glyphicon glyphicon-cog"></span> Configuração com sistema de gestão
                                                </label>
                                            </a>

                                            <span class="pull-right">
                                                <select name="connection.dbType" class="form-control">
                                                    <option value="1">PostgresSql</option>
                                                    <option value="2">MySql</option>
                                                </select>
                                            </span>
                                        </h4>
                                    </div>
                                    <div id="collapseConfig" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Endereço IP</span>
                                                    <s:textfield type="text" id="nrIp" name="connection.nrIp" cssClass="form-control" placeholder="Ex: 127.0.0.1"/>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Porta</span>
                                                    <s:textfield type="text" id="nrPort" name="connection.nrPort" cssClass="form-control" placeholder="Ex: 5432"/>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Esquema</span>
                                                    <s:textfield type="text" id="nmSchema" name="connection.nmSchema" cssClass="form-control"/>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Banco de dados</span>
                                                    <s:textfield type="text" id="nmDatabase" name="connection.nmDatabase" cssClass="form-control" placeholder="Database name"/>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Usuário</span>
                                                    <s:textfield type="text" id="nmUser" name="connection.nmUser" cssClass="form-control" placeholder="Ex: root"/>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Senha</span>
                                                    <s:textfield id="cdPass" name="connection.cdPass" cssClass="form-control" placeholder="Ex: myP@ssword"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel-footer">
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6 col-xs-6 text-left">
                                                    <div class="dropdown">
                                                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                            Baixar
                                                            <span class="caret"></span>
                                                        </button>
                                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadIndividuos" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Indivíduo
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadGrupos" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Grupo
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadDesafios" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Desafio
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadGrupoIndividuo" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Grupo Indivíduo
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadIndividuoDesafio" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Indivíduo Desafio
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="<%=request.getContextPath()%>/downloadGrupoDesafio" target="_new">
                                                                    <i class="glyphicon glyphicon-download-alt"></i> Grupo Desafio
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                                                    <a class="btn btn-default" href="javascript:clearForm('connectionForm');"><i class="glyphicon glyphicon-repeat"></i> Limpar</a>
                                                    <a class="btn btn-primary" href="javascript:process();"><i class="glyphicon glyphicon-log-in"></i> Processar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                        <!-- ---------------- FIM COFIGURAÇÕES ---------------- -->

                    </div>
                </div>
            </div>

            <div class="container">
                <footer class="row">
                    <div class="col-md-12 text-center">© Copyright 2016.IESAM - UNISC - Universidade de Santa Cruz do Sul - Todos os direitos reservados.</br>
                        <a href="mailto:grohr@mx2.unisc.br">Guilherme Rohr</a>
                    </div>
                </footer>
            </div>
            <!-- /container -->

            <script>window.jQuery || document.write('<script src="assets/jquery/jquery-1.11.3.min.js"><\/script>')</script>
            <script src="assets/bootstrap-3.3.5/js/bootstrap.min.js"></script>
            <script src="assets/jquery.blockUI.min.js"></script>
            <script src="assets/main.js"></script>
        </body>
    </html>
