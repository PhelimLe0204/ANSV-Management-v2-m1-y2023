$(document).ready(function () {
    $('.checkEnabled').change(function () {
        var thisId = $(this).attr("id");
        let enabled = 0;
        if ($(this).attr("data-value") == 0) {
            enabled = 1;
        }
        let id = ($(this).attr("data-id"));
        $.ajax({
            url: "/api/customer/updateEnabled/" + id + "/" + enabled,
            success: function (result) {
                $('#' + thisId).attr("data-value", enabled);
                alertify.success(result.message).delay(1);
            }
        });
    });

    $("#btn-open-add-new-customer-modal").click(function () {
        frame.src = "/images/logo/image_undefined_2.jpg";
    });

    $("#btn-submit-add-new-customer").click(function () {
        $("#form-add-new-customer").submit();
    });
});

function preview() {
    if (event.target.files[0].length != 0) {
        if (event.target.files[0].name.match(/\.(jpg|jpeg|png|gif)$/)) {
            frame.src = URL.createObjectURL(event.target.files[0]);
            var spaceWidth = document.getElementById("avatarDisplay").offsetWidth - 30; // 15px padding-left & 15px padding-right
            frame.width = frame.width * (spaceWidth / frame.width);
            frame.height = frame.height * (spaceWidth / frame.width);
            if (event.target.files[0].name != "image_undefined_2.jpg") {
                document.getElementById("btn-delete-img").disabled = false;
            } else {
                document.getElementById("btn-delete-img").disabled = true;
            }
        } else {
            document.getElementById('customerAvatar').value = null;
            frame.src = "/images/logo/image_undefined_2.jpg";
            alertify.warning('Mời chọn đúng file định dạng ảnh').delay(2.5);
        }
    }

    // console.log(event.target.files[0].name);
    // console.log(frame.width / spaceWidth + " - " + spaceWidth / frame.width);
    // console.log(frame.width + " - " + frame.height);
    // console.log(spaceWidth);
}
function clearImage() {
    document.getElementById('customerAvatar').value = null;
    frame.src = "/images/logo/image_undefined_2.jpg";
    document.getElementById("btn-delete-img").disabled = true;
}