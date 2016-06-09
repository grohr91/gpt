$(function ($) {
    setUp();
});

function setUp() {
    $.blockUI.defaults.message = '<h3><i class="glyphicon glyphicon-refresh glyphicon-spin"></i> Processing</h3>';
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
    $('[href="#table0"]').click();
}

function clearForm(formId) {
    if (confirm("Are you sure?")) {
        $("#" + formId).find("input").val("");
    }
}

function process() {
    $.ajax({
        url: $("#url").val() + "/processGamification",
        data: $("#connectionForm").serialize(),
    }).done(function (data) {
        if (data.dsMessage != null && data.dsMessage != "") {
            $("#message-div").empty().append(data.dsMessage);
        }
        if (data.dsInfo != null && data.dsInfo != "") {
            $("#message-div").empty().append(data.dsInfo);
        }
    });
}