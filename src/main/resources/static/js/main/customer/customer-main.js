$(document).ready(function () {
    let params = new URLSearchParams(location.search);
    uploadStatus = params.get('uploadStatus');
    if (uploadStatus == 1) {
        // location.reload();
        // window.location.href = '/danh-sach/khach-hang';
    }
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
        frameAddNew.src = "/images/logo/image_undefined_2.jpg";
    });

    $("#btn-submit-add-new-customer").click(function () {
        // Call API upload file
        var form = document.getElementById('form-add-new-customer');
        // var url = form.action;
        var data = new FormData(form);

        $("#modal-add-new-customer-footer").html(
            '<button type="button" class="btn btn-secondary pt-1 pb-1" data-dismiss="modal">Đóng</button>'
            + '<button type="button" class="btn btn-primary pt-1 pb-1" disabled>'
            + '<span class="spinner-border spinner-border-sm" role="status"></span>'
            + '<span class="pl-1"> Đang xử lý...</span></button>'
        );

        $.ajax({
            url: "/customer/uploadAvatar",
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result, textStatus, jqXHR) {
                if (result.status == "success") {
                    $("#avatarName").val(result.data);
                    console.log($("#avatarName").val());
                    setTimeout(function () {
                        $("#form-add-new-customer").submit();
                    }, 1000);
                }

                if (result.status == "failed") {
                    alertify.warning(result.message).delay(2.5);
                    $("#modal-add-new-customer-footer").html(
                        '<button type="button" class="btn btn-secondary pt-1 pb-1" data-dismiss="modal">Đóng</button>' +
                        '<button type="button" class="btn btn-primary pt-1 pb-1" id="btn-submit-add-new-customer">Thêm mới</button>'
                    );
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alertify.error("Thất bại! Vui lòng thử lại.").delay(3);
                // alert('ERRORS: ' + textStatus);
            }
        });
    });
});

$('.customerAvatar').change(function (event) {
    if (event.target.files[0] == undefined) {
        return;
    }
    if (event.target.files[0].length != 0) {
        if (event.target.files[0].name.match(/\.(jpg|jpeg|png|gif)$/)) {
            var frame = document.getElementById(this.dataset.target);
            frame.src = URL.createObjectURL(event.target.files[0]);
            var spaceWidth = document.getElementById("avatarDisplay").offsetWidth - 30; // 15px padding-left & 15px padding-right
            frame.width = frame.width * (spaceWidth / frame.width);
            frame.height = frame.height * (spaceWidth / frame.width);
            if (event.target.files[0].name != "image_undefined_2.jpg") {
                document.getElementById(this.dataset.delete).disabled = false;
            } else {
                document.getElementById(this.dataset.delete).disabled = true;
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
});

$(".btnDeleteImg").click(function () {
    document.getElementById(this.dataset.input).value = null;
    document.getElementById(this.dataset.target).src = "/images/logo/image_undefined_2.jpg";
    this.disabled = true;
});

$(".customerNameList").click(function () {
    var dataCustomerDetail;
    // Ajax get data for project's selection
    $.ajax({
        url: "/api/getCustomerDetail/" + $(this).data("id"),
        success: function (result) {
            if (result.status == "success") {
                dataCustomerDetail = result.data;
                console.log(dataCustomerDetail);
                document.getElementById("customerNameUpdate").value = dataCustomerDetail.customerName;
                document.getElementById("frameUpdate").src = "/images/logo/" + (dataCustomerDetail.avatar != null ? dataCustomerDetail.avatar : "image_undefined_2.jpg");
                document.getElementById("createdByUpdate").value = dataCustomerDetail.createdBy;
                if (dataCustomerDetail.avatar != null) {
                    document.getElementById("btnDeleteImgUpdate").disabled = false;
                } else {
                    document.getElementById("btnDeleteImgUpdate").disabled = true;
                }
                return;
            }
            if (result.status == "failed") {
                alertify.error(result.message).delay(2.5);
            }
        }
    });
});

// function preview() {
//     if (event.target.files[0].length != 0) {
//         if (event.target.files[0].name.match(/\.(jpg|jpeg|png|gif)$/)) {
//             frame.src = URL.createObjectURL(event.target.files[0]);
//             var spaceWidth = document.getElementById("avatarDisplay").offsetWidth - 30; // 15px padding-left & 15px padding-right
//             frame.width = frame.width * (spaceWidth / frame.width);
//             frame.height = frame.height * (spaceWidth / frame.width);
//             if (event.target.files[0].name != "image_undefined_2.jpg") {
//                 document.getElementById("btn-delete-img").disabled = false;
//             } else {
//                 document.getElementById("btn-delete-img").disabled = true;
//             }
//         } else {
//             document.getElementById('customerAvatar').value = null;
//             frame.src = "/images/logo/image_undefined_2.jpg";
//             alertify.warning('Mời chọn đúng file định dạng ảnh').delay(2.5);
//         }
//     }

//     // console.log(event.target.files[0].name);
//     // console.log(frame.width / spaceWidth + " - " + spaceWidth / frame.width);
//     // console.log(frame.width + " - " + frame.height);
//     // console.log(spaceWidth);
// }
// function clearImage() {
//     document.getElementById('customerAvatar').value = null;
//     frame.src = "/images/logo/image_undefined_2.jpg";
//     document.getElementById("btn-delete-img").disabled = true;
// }