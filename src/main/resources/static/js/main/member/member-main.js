$(document).ready(function () {
    $('.checkEnabled').change(function () {
        var thisId = $(this).attr("id");
        let enabled = 0;
        if ($(this).attr("data-value") == 0) {
            enabled = 1;
        }
        let id = ($(this).attr("data-id"));
        $.ajax({
            url: "/api/updateEnabled/"+ id + "/" + enabled,
            success: function (result) {
                $('#'+thisId).attr("data-value",enabled);
                alertify.success(result.message).delay(1);
            }
        });
    })


});