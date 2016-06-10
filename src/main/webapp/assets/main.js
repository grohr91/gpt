$(function ($) {
    setUp();
});

function setUp() {
    $.blockUI.defaults.message = '<h3><i class="glyphicon glyphicon-refresh glyphicon-spin"></i> Processing</h3>';
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
    alteraDivTipoImportacao()
//    $('[href="#table0"]').click();
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

//function process() {
//    $.ajax({
//        url: $("#url").val() + "/processGamification",
//        data: $("#connectionForm").serialize(),
//    }).done(function (data) {
//        if (data.dsMessage != null && data.dsMessage != "") {
//            $("#message-div").empty().append(data.dsMessage);
//        }
//        if (data.dsInfo != null && data.dsInfo != "") {
//            $("#message-div").empty().append(data.dsInfo);
//        }
//    });
//}