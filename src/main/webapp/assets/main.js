$(function ($) {
    setUp();
});

function setUp() {
    $.blockUI.defaults.message = '<h3><i class="glyphicon glyphicon-refresh glyphicon-spin"></i> Processando..</h3>';
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
    alteraDivTipoImportacao();

    $('input[type=radio].sgTipoRemocao').change(function () {
        var tabela = $(this).parent().attr('tabela-id');
        var index = $(this).parent().attr('index-id');
        if (this.value == '3') {
            $(".div-regras-remocao[tabela-id='" + tabela + "'][index-id='" + index + "']").show();
        }
        else {
            $(".div-regras-remocao[tabela-id='" + tabela + "'][index-id='" + index + "']").hide();
        }
    });

    $($('.tab-pane')[0]).addClass('active');
}

function clearForm(formId) {
    if (confirm("Are you sure?")) {
        $("#" + formId).find("input").val("");
    }
}

function alteraDivTipoImportacao() {
    if ($("#sgTipoImportacao").val() == "1") {
        $(".configuracao-bd").show();
        $("#configuracao-csv").hide();
    } else if ($("#sgTipoImportacao").val() == "2") {
        $(".configuracao-bd").hide();
        $("#configuracao-csv").show();
    }
}

function carregaConfiguracao() {
    window.location.href = $("#url").val() + "/main?id=" + $("#configuracaoCombo").val();
}

function carregaTipoOperacao(combo, tabela, index) {
    $.ajax({
        url: $("#url").val() + "/carregaOperacaoByTipoAtributo",
        data: {
            'tipoAtributo.id': $(combo).val()
        }
    }).done(function (data) {
        $("[tabela-id='" + tabela + "'][index-id='" + index + "'] .tipo-operacao").empty();
        if (data.dsMessage != null && data.dsMessage != "") {
            $("#message-div").empty().append(data.dsMessage);
        }
        else {
            $.each(data.tipoAtributoOperacaoList, function (key, obj) {
                $("[tabela-id='" + tabela + "'][index-id='" + index + "'] .tipo-operacao")
                        .append("<option value=" + obj.operacao.id + "> " +
                                obj.operacao.nmOperacao + " </option>")
            });

        }
    });
}

function adicionarRegra(tabela, index, sgTipoRegra, idRegraTabela) {
    var vlRegra = $("[tabela-id='" + tabela + "'][index-id='" + index + "'][tipo-regra='" + sgTipoRegra + "'] .vl-regra").val();
    var idOperacao = $("[tabela-id='" + tabela + "'][index-id='" + index + "'][tipo-regra='" + sgTipoRegra + "'] .tipo-operacao").val();
    var idAtributo = $("[tabela-id='" + tabela + "'][index-id='" + index + "'][tipo-regra='" + sgTipoRegra + "'] .tipo-atributo").val();
    var vlRegraNovo = $("[tabela-id='" + tabela + "'][index-id='" + index + "'][tipo-regra='" + sgTipoRegra + "'] .vl-regra-novo").val();
    $.ajax({
        url: $("#url").val() + "/salvaRegra",
        data: {
            'regra.vlRegra': vlRegra,
            'regra.vlRegraNovo': vlRegraNovo,
            'regra.sgTipoRegra': sgTipoRegra,
            'regra.regraTabela.id': idRegraTabela,
            'regra.operacao.id': idOperacao,
            'regra.atributo.id': idAtributo
        }
    }).done(function (data) {
        if (data.dsMessage != null && data.dsMessage != "") {
            $("#message-div").empty().append(data.dsMessage);
        }
        else {
            $(data.regraHtml).insertBefore($("[tabela-id='" + tabela + "'][index-id='" + index + "'][tipo-regra='" + sgTipoRegra + "'].form-group"));
        }
    });
}

function excluiRegra(idRegra) {
    $.ajax({
        url: $("#url").val() + "/excluiRegra",
        data: {
            'regra.id': idRegra
        }
    }).done(function (data) {
        if (data.dsMessage != null && data.dsMessage != "") {
            $("#message-div").empty().append(data.dsMessage);
        }
        else {
            $(".div-regra[regra-id='" + idRegra + "']").remove();
        }
    });
}

function processar(configuracao) {
    $.ajax({
        url: $("#url").val() + "/processarImportacao",
        data: {
            'id': configuracao
        }
    }).done(function (data) {
        if (data.dsMessage != null && data.dsMessage != "") {
            $("#message-div").empty().append(data.dsMessage);
        }
        if (data.dsInfo != null && data.dsInfo != "") {
            $("#message-div").empty().append(data.dsInfo);
        }
    });
}