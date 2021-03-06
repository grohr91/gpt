<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="form-group" style="margin-right: 0px; margin-left: 0px; padding-top: 20px;" tipo-regra="<s:property value="regra.sgTipoRegra"/>" tabela-id="<s:property value="tabela.id"/>" index-id="<s:property value="index"/>">
    <label class="col-md-1 control-label" style="padding-top: 3px;">Quando</label>
    <div class="col-md-3">
        <s:select list="atributoList"
                  listKey="id"
                  listValue="nmAtributo"
                  cssClass="form-control input-sm tipo-atributo"
                  onchange="carregaTipoOperacao(this, %{tabela.id}, %{index});"
                  emptyOption="true"
                  />
    </div>

    <label class="col-md-1 control-label" style="padding-top: 3px;">é</label>
    <div class="col-md-2">
        <select class="form-control input-sm tipo-operacao"></select>
    </div>

    <div class="col-md-2">
        <s:if test="tabela.nmTabela == 'desafio'">
            <s:select list="desafioList"
                      listKey="nmDesafio"
                      listValue="nmDesafio"
                      cssClass="form-control input-sm vl-regra"
                      emptyOption="true"
                      />
        </s:if><s:else>
            <input type="text" class="form-control input-sm vl-regra"/>
        </s:else>
    </div>

    <s:if test="regra.sgTipoRegra == 3">
        <label class="col-md-1 control-label" style="padding-top: 3px;">atribui-se</label>
        <div class="col-md-2">
            <input type="text" class="form-control input-sm vl-regra-novo"/>
        </div>
    </s:if>
    <div class="col-md-3">
        <a class="btn btn-sm btn-default" href="javascript:adicionarRegra(<s:property value="tabela.id"/>, <s:property value="index"/>, <s:property value="regra.sgTipoRegra"/>, <s:property value="regraTabela.id"/>, this);"><i class="glyphicon glyphicon-plus"></i> Adicionar Regra</a>
    </div>
</div>