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
                        <span class="navbar-brand">GPT - Gamification Provider Tool</span>
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
                        <!-- ---------------- COFIGURAÇÕES GERAIS ---------------- -->
                        <div class="col-md-12" >
                            <s:form id="configurationForm" namespace="/" action="salvaConfiguracao" cssClass="form-horizontal" method="post" theme="simple">
                                <div class="panel panel-default">
                                    <div class="panel-heading" style="padding: 2px 30px;">
                                        <h4 class="panel-title">
                                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseConfig" aria-expanded="true" aria-controls="collapseOne">
                                                <label style='margin-bottom: 0px; padding-top: 8px; padding-bottom: 7px; cursor: pointer; width: 100%'>
                                                    <span class="glyphicon glyphicon-cogs"></span> Configurações Gerais
                                                </label>
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseConfig" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">

                                            <div class="form-group">
                                                <label for="nmConfiguracao" class="col-sm-2 control-label">Configuração</label>
                                                <div class="col-sm-4">
                                                    <s:select list="configuracaoList"
                                                              listKey="id"
                                                              listValue="nmConfiguracao"
                                                              name="id"
                                                              id="configuracaoCombo"
                                                              cssClass="form-control"
                                                              onchange="carregaConfiguracao();"
                                                              />
                                                </div>
                                                <s:if test="id != null">
                                                    <div class="col-sm-4 col-sm-offset-2">
                                                        <a href="javascript:processar(<s:property value="id"/>);" 
                                                           class="btn btn-primary">
                                                            <i class="glyphicon glyphicon-play"></i> Processar Importação
                                                        </a>
                                                    </div>
                                                </s:if>

                                            </div>
                                            <s:hidden name="configuracao.id"/>
                                            <div class="form-group">
                                                <label for="nmConfiguracao" class="col-sm-2 control-label">Nome</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="configuracao.nmConfiguracao" cssClass="form-control" id="nmConfiguracao" placeholder="Nome da configuração"/>
                                                </div>

                                                <label for="idIdentificador" class="col-sm-2 control-label">Identificador</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="configuracao.idIdentificador" cssClass="form-control" id="idIdentificador" placeholder="Identificador interno"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Tabelas</label>
                                                <div class="col-sm-10" style="padding-top: 7px;">
                                                    <s:iterator value="regraTabelaList" status="st">
                                                        <s:hidden name="regraTabelaList[%{#st.index}].id"/>
                                                        <s:hidden name="regraTabelaList[%{#st.index}].tabela.id"/>
                                                        <s:hidden name="regraTabelaList[%{#st.index}].sgTipoInsercao"/>
                                                        <s:hidden name="regraTabelaList[%{#st.index}].sgTipoRemocao"/>
                                                        <s:checkbox name="regraTabelaList[%{#st.index}].fgImportar"/> <s:property value="tabela.nmTabela"/>&nbsp;&nbsp;
                                                    </s:iterator>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <h4><i class="glyphicon glyphicon-resize-small"></i> Conexão</h4> <hr>
                                            </div>

                                            <div class="form-group">
                                                <label for="sgTipoImportacao" class="col-sm-2 control-label">Tipo</label>
                                                <div class="col-sm-4">
                                                    <s:select list="tipoImportacaoList"
                                                              name="configuracao.sgTipoImportacao"
                                                              id="sgTipoImportacao"
                                                              cssClass="form-control"/>
                                                </div>

                                                <div class="configuracao-bd">
                                                    <label for="sgTipoBd" class="col-sm-2 control-label">Tipo BD</label>
                                                    <div class="col-sm-4">
                                                        <s:select list="tipoBdList"
                                                                  name="configuracao.sgTipoBd"
                                                                  id="sgTipoBd"
                                                                  cssClass="form-control"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="configuracao-bd">
                                                <div class="form-group">
                                                    <label for="nrIpHost" class="col-sm-2 control-label">Host IP</label>
                                                    <div class="col-sm-4">
                                                        <s:textfield id="nrIp" name="configuracao.nrIpHost" cssClass="form-control" placeholder="Ex: 127.0.0.1"/>
                                                    </div>
                                                    <label for="nrPort" class="col-sm-2 control-label">Porta</label>
                                                    <div class="col-sm-4">
                                                        <s:textfield id="nrPort" name="configuracao.nrPort" cssClass="form-control" placeholder="Ex: 5432"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="nmSchema" class="col-sm-2 control-label">Esquema</label>
                                                    <div class="col-sm-4">
                                                        <s:textfield id="nmSchema" name="configuracao.nmSchema" cssClass="form-control" placeholder="Ex: public"/>
                                                    </div>
                                                    <label for="nmDatabase" class="col-sm-2 control-label">Nome BD</label>
                                                    <div class="col-sm-4">
                                                        <s:textfield  id="nmDatabase" name="configuracao.nmDatabase" cssClass="form-control" placeholder="Nome do banco de dados"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="nmUser" class="col-sm-2 control-label">Usuário</label>
                                                    <div class="col-sm-4">
                                                        <s:textfield id="nmUser" name="configuracao.nmUser" cssClass="form-control" placeholder="Ex: postgres"/>
                                                    </div>
                                                    <label for="cdPass" class="col-sm-2 control-label">Senha</label>
                                                    <div class="col-sm-4">
                                                        <s:password id="cdPass" name="configuracao.cdPass" cssClass="form-control"/>
                                                    </div>
                                                </div>

                                            </div>

                                            <div id="configuracao-csv" style="display: none;">
                                                <div class="form-group">
                                                    <label for="dsDiretorioArquivos" class="col-sm-2 control-label">Diretório</label>
                                                    <div class="col-sm-10">
                                                        <s:textfield type="text" id="dsDiretorioArquivos" cssClass="form-control" name="configuracao.dsDiretorioArquivos" placeholder="Diretório dos arquivos csv"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <h4><i class="glyphicon glyphicon-repeat"></i> Automação</h4> <hr>
                                            </div>
                                            <div class="form-group">                                                    
                                                <label class="col-lg-2 control-label">Dia e hora</label>
                                                <s:iterator value="automacaoList" status="st">
                                                    <div class="col-lg-1">
                                                        <s:hidden name="automacaoList[%{#st.index}].id"/>
                                                        <s:hidden name="automacaoList[%{#st.index}].nrDiaSemana"/>
                                                        <s:property value="nmDiaSemana"/>
                                                        <input type="text" class="form-control" style="text-align: center;" name="automacaoList[<s:property value="#st.index"/>].hrAutomacaoString" value="<s:date name="hrAutomacao" format="HH:mm"/>" placeholder="  :  "/>
                                                    </div>
                                                </s:iterator>
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
                                                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Salvar e Configurar Regras</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                        <!-- ---------------- FIM COFIGURAÇÕES GERAIS ---------------- -->

                        <s:if test="configuracao.id != null">
                            <!-- ---------------- COFIGURAÇÕES EXTRAÇÃO ---------------- -->
                            <div class="col-sm-12" >
                                <s:form id="regraExtracaoForm" namespace="/" action="salvaRegraExtracaoList" method="post" theme="simple">
                                    <s:hidden name="configuracao.id"/>
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="padding: 2px 30px;">
                                            <h4 class="panel-title">
                                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#accordionRegrasExtracao" aria-expanded="true" aria-controls="collapseTwo">
                                                    <label style='margin-bottom: 0px; padding-top: 8px; padding-bottom: 7px; cursor: pointer; width: 100%'>
                                                        <span class="glyphicon glyphicon-export"></span> Regras de Extração
                                                    </label>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="accordionRegrasExtracao" class="panel-collapse collapse in" role="tabpane2" aria-labelledby="headingTwo">
                                            <div class="panel-body">
                                                <div class="col-sm-2"> <!-- required for floating -->
                                                    <!-- Nav tabs -->
                                                    <ul class="nav nav-tabs tabs-left"><!-- 'tabs-right' for right tabs -->
                                                        <s:iterator value="regraExtracaoList" status="st">
                                                            <s:if test="%{#st.first == true}">
                                                                <li class="active"><a href="#view<s:property value="id"/>" data-toggle="tab" class="truncate" title="<s:property value="nmView"/>"><s:property value="nmView"/></a></li>
                                                                </s:if>
                                                                <s:else>
                                                                <li><a href="#view<s:property value="id"/>" data-toggle="tab" class="truncate" title="<s:property value="nmView"/>"><s:property value="nmView"/></a></li>
                                                                </s:else>
                                                            </s:iterator>
                                                    </ul>
                                                </div>
                                                <div class="col-sm-10">
                                                    <!-- Tab panes -->
                                                    <div class="tab-content">
                                                        <s:iterator value="regraExtracaoList" status="st">
                                                            <s:hidden name="regraExtracaoList[%{#st.index}].id"/>
                                                            <s:hidden name="regraExtracaoList[%{#st.index}].nmView"/>
                                                            <s:hidden name="regraExtracaoList[%{#st.index}].configuracao.id"/>
                                                            <div class="tab-pane" id="view<s:property value="id"/>">
                                                                <div class="form-group">
                                                                    <label class="col-sm-12 control-label" for="sqlView<s:property value="id"/>"><s:property value="nmView"/>  <a class="btn btn-default btn-sm" href="javascript:alert('<s:property value="dsInfo"/>')" title="<s:property value="dsInfo"/>"><i class="glyphicon glyphicon-info-sign"></i> Informações</a></label>
                                                                    <div class="col-sm-12">
                                                                        <s:textarea id="sqlView%{id}"
                                                                                    name="regraExtracaoList[%{#st.index}].sqlView" 
                                                                                    cssClass="form-control"
                                                                                    rows="10"
                                                                                    placeholder="Select interno da view aqui"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-footer" style="height: 55px;">
                                                <div class="col-sm-12 text-right">
                                                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Salvar Regras de Extração</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </s:form>
                            </div>
                            <!-- ---------------- FIM COFIGURAÇÕES EXTRAÇÃO ---------------- -->

                            <!-- ---------------- COFIGURAÇÕES TABELAS ---------------- -->
                            <div class="col-sm-12" >
                                <s:form id="regraTabelaForm" namespace="/" action="salvaRegraTabelaList" cssClass="form-horizontal" method="post" theme="simple">
                                    <s:hidden name="configuracao.id"/>
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="padding: 2px 30px;">
                                            <h4 class="panel-title">
                                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#accordionRegrasImportacao" aria-expanded="true" aria-controls="collapseTwo">
                                                    <label style='margin-bottom: 0px; padding-top: 8px; padding-bottom: 7px; cursor: pointer; width: 100%'>
                                                        <span class="glyphicon glyphicon-import"></span> Regras de Importação
                                                    </label>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="accordionRegrasImportacao" class="panel-collapse collapse in" role="tabpane2" aria-labelledby="headingTwo">
                                            <div class="panel-body">
                                                <div class="col-sm-2"> <!-- required for floating -->
                                                    <!-- Nav tabs -->
                                                    <ul class="nav nav-tabs tabs-left"><!-- 'tabs-right' for right tabs -->
                                                        <s:iterator value="regraTabelaList" status="st">
                                                            <s:if test="id != null">
                                                                <s:if test="%{#st.first == true}">
                                                                    <li class="active"><a href="#<s:property value="id"/>" data-toggle="tab" class="truncate" title="<s:property value="tabela.nmTabela"/>"><s:property value="tabela.nmTabela"/></a></li>
                                                                    </s:if>
                                                                    <s:else>
                                                                    <li><a href="#<s:property value="id"/>" data-toggle="tab" class="truncate" title="<s:property value="tabela.nmTabela"/>"><s:property value="tabela.nmTabela"/></a></li>
                                                                    </s:else>
                                                                </s:if>
                                                            </s:iterator>
                                                    </ul>
                                                </div>
                                                <div class="col-sm-10">
                                                    <!-- Tab panes -->
                                                    <div class="tab-content">
                                                        <s:iterator value="regraTabelaList" status="st">
                                                            <s:if test="id != null">
                                                                <s:hidden name="regraTabelaList[%{#st.index}].id"/>
                                                                <s:hidden name="regraTabelaList[%{#st.index}].fgImportar"/>
                                                                <s:hidden name="regraTabelaList[%{#st.index}].tabela.id"/>
                                                                <s:hidden name="regraTabelaList[%{#st.index}].configuracao.id"/>
                                                                <s:if test="%{#st.first == true}">
                                                                    <div class="tab-pane active" id="<s:property value="id"/>">
                                                                    </s:if><s:else>
                                                                        <div class="tab-pane" id="<s:property value="id"/>">
                                                                        </s:else>
                                                                        <h4><i class="glyphicon glyphicon-th-list"></i> Tabela: <s:property value="tabela.nmTabela"/></h4><hr style="margin-top: 0px;">

                                                                        <!-- REGRAS ESPECIFICAS PARA TABELA DESAFIO -->
                                                                        <s:if test="tabela.nmTabela=='desafio'">
                                                                            <div class="col-sm-12">
                                                                                <div class="panel panel-default">
                                                                                    <div class="panel-body painel-regras">
                                                                                        <div class="painel-regras-titulo">
                                                                                            Importar Desafios
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <s:iterator value="sysRegraList" status="st1">
                                                                                                <s:if test="sgTipoRegra == 1">
                                                                                                    <div style="padding-bottom: 10px; padding-top: 10px;" class="div-regra" regra-id="<s:property value="id"/>">
                                                                                                        <div class="col-md-9">
                                                                                                            <i class="glyphicon glyphicon-asterisk"></i> <strong>Quando</strong> <s:property value="atributo.nmAtributo"/> 
                                                                                                            <strong>for</strong> <s:property value="operacao.nmOperacao"/> 
                                                                                                            <strong>a/que/de</strong> <s:property value="vlRegra"/>
                                                                                                        </div>
                                                                                                        <div class="col-md-3">
                                                                                                            <a class="btn btn-xs btn-default" href="javascript:excluiRegra(<s:property value="id"/>);"><i class="glyphicon glyphicon-trash"></i> Remover</a>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </s:if>
                                                                                            </s:iterator>
                                                                                            <s:action var="atributos%{id}" name="carregaNovaRegra" namespace="/" executeResult="true">
                                                                                                <s:param name="configuracao.id" value="%{configuracao.id}"/>
                                                                                                <s:param name="tabela.id" value="%{id}"/>
                                                                                                <s:param name="index" value="%{#st.index}"/>
                                                                                                <s:param name="regra.sgTipoRegra" value="1"/>
                                                                                            </s:action>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <div class="form-group" style="height: 34px;">
                                                                                <label class="col-sm-2 control-label text-right">Persistência</label>
                                                                                <div class="col-sm-10">
                                                                                    <s:radio name="regraTabelaList[%{#st.index}].sgTipoInsercao" list="#{'1':'Apenas Inserir','2':'Inserir e Alterar'}" value="sgTipoInsercao" />
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group" style="height: 34px;">
                                                                                <label class="col-sm-2 control-label text-right">Remoção</label>
                                                                                <div class="col-sm-10" tabela-id="<s:property value="id"/>" index-id="<s:property value="#st.index"/>">
                                                                                    <s:radio name="regraTabelaList[%{#st.index}].sgTipoRemocao" cssClass="sgTipoRemocao" list="#{'1':'Registros não Sincronizados','2':'Nunca Remover','3':'Configurar Regra'}" value="sgTipoRemocao" />
                                                                                </div>
                                                                            </div>

                                                                            <div class="col-sm-10 col-sm-offset-2">
                                                                                <div class="div-regras-remocao"  tabela-id="<s:property value="tabela.id"/>" index-id="<s:property value="#st.index"/>" style="display: none;">
                                                                                    <div class="panel panel-default">
                                                                                        <div class="panel-body painel-regras">
                                                                                            <div class="painel-regras-titulo">
                                                                                                Regras de Remoção
                                                                                            </div>
                                                                                            <div class="row">
                                                                                                <s:iterator value="sysRegraList" status="st1">
                                                                                                    <s:if test="sgTipoRegra == 2">
                                                                                                        <div style="padding-bottom: 10px; padding-top: 10px;" class="div-regra" regra-id="<s:property value="id"/>">
                                                                                                            <div class="col-md-9">
                                                                                                                <i class="glyphicon glyphicon-asterisk"></i> <strong>Quando</strong> <s:property value="atributo.nmAtributo"/> 
                                                                                                                <strong>for</strong> <s:property value="operacao.nmOperacao"/> 
                                                                                                                <strong>a/que/de</strong> <s:property value="vlRegra"/>
                                                                                                            </div>
                                                                                                            <div class="col-md-3">
                                                                                                                <a class="btn btn-xs btn-default" href="javascript:excluiRegra(<s:property value="id"/>);"><i class="glyphicon glyphicon-trash"></i> Remover</a>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </s:if>
                                                                                                </s:iterator>
                                                                                                <s:action var="atributos%{id}" name="carregaNovaRegra" namespace="/" executeResult="true">
                                                                                                    <s:param name="configuracao.id" value="%{configuracao.id}"/>
                                                                                                    <s:param name="tabela.id" value="%{id}"/>
                                                                                                    <s:param name="index" value="%{#st.index}"/>
                                                                                                    <s:param name="regra.sgTipoRegra" value="2"/>
                                                                                                </s:action>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="col-sm-12">
                                                                                <h4><i class="glyphicon glyphicon-filter"></i> Filtro</h4><hr style="margin-top: 0px;">
                                                                            </div>
                                                                            <div class="col-sm-10 col-sm-offset-2">
                                                                                <div class="panel panel-default">
                                                                                    <div class="panel-body painel-regras">
                                                                                        <div class="painel-regras-titulo">
                                                                                            Regras de Filtro
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <s:iterator value="sysRegraList" status="st1">
                                                                                                <s:if test="sgTipoRegra == 1">
                                                                                                    <div style="padding-bottom: 10px; padding-top: 10px;" class="div-regra" regra-id="<s:property value="id"/>">
                                                                                                        <div class="col-md-9">
                                                                                                            <i class="glyphicon glyphicon-asterisk"></i> <strong>Quando</strong> <s:property value="atributo.nmAtributo"/> 
                                                                                                            <strong>for</strong> <s:property value="operacao.nmOperacao"/> 
                                                                                                            <strong>a/que/de</strong> <s:property value="vlRegra"/>
                                                                                                        </div>
                                                                                                        <div class="col-md-3">
                                                                                                            <a class="btn btn-xs btn-default" href="javascript:excluiRegra(<s:property value="id"/>);"><i class="glyphicon glyphicon-trash"></i> Remover</a>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </s:if>
                                                                                            </s:iterator>
                                                                                            <s:action var="atributos%{id}" name="carregaNovaRegra" namespace="/" executeResult="true">
                                                                                                <s:param name="configuracao.id" value="%{configuracao.id}"/>
                                                                                                <s:param name="tabela.id" value="%{id}"/>
                                                                                                <s:param name="index" value="%{#st.index}"/>
                                                                                                <s:param name="regra.sgTipoRegra" value="1"/>
                                                                                            </s:action>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </s:else>
                                                                    </div>
                                                                </s:if>
                                                            </s:iterator>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="panel-footer" style="height: 55px;">
                                                    <div class="col-sm-12 text-right">
                                                        <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> Salvar Regras de Importação</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </s:form>
                                </div>
                                <!-- ---------------- FIM COFIGURAÇÕES TABELAS ---------------- -->
                            </s:if>
                        </div>
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
