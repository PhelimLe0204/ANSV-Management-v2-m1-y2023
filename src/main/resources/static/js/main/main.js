$(document).ready(function () {
    $(".link-none").on("click", function (event) {
        event.preventDefault();
        alertify.confirm('Thông báo', 'Chức năng đang trong quá trình phát triển! Mời bạn quay trở lại sau.'
            , function () { }, function () { });
    });
});

$("#addNewProjectModalOpen").click(function () {
    $.ajax({
        url: "/api/getCustomerSelectOption",
        success: function (result) {
            // console.log(result);
            data_customer_select_option = result.data;

            $("#projectCustomer").select2({
                dropdownParent: $('#project-customer-selection'),
                placeholder: 'Khách hàng...',
                data: data_customer_select_option,
                allowClear: true,
                selectOnClose: true,
                templateResult: function (data, container) {
                    var $state = $(
                        '<div class="row">'
                        + '<div class="col-md-2">'
                        + '<img src="/images/logo/'
                        + (data.avatar ? data.avatar : 'image_undefined.jpg')
                        + '" class="img-flag" style="width: 40px; height: 40px; margin-left: 10px;" />'
                        + '</div>'
                        + '<div class="col-md-10 pt-1">'
                        + '<span class="font-weight-bold" style="font-size: 20px;">'
                        + (data.customerName ? data.customerName : '. . . . .')
                        + '</span>'
                        + '</div>'
                        + '</div>'
                    );

                    return $state;
                },
                templateSelection: function (data, container) {
                    var $state = $(
                        '<div class="row">'
                        + '<div class="col-md-2 pb-1">'
                        + '<img src="/images/logo/image_undefined.jpg" class="img-flag" style="width: 54px; height: 54px; margin-left: 5px; padding-top: 5px;" />'
                        + '</div>'
                        + '<div class="col-md-10 pt-3">'
                        + '<span class="font-weight-bold">Mời chọn khách hàng...</span>'
                        + '</div>'
                        + '</div>'
                    );

                    if (data.id && data.customerName) {
                        var $state = $(
                            '<div class="row">'
                            + '<div class="col-md-2 pb-1">'
                            + '<img src="/images/logo/'
                            + (data.avatar ? data.avatar : 'image_undefined.jpg')
                            + '" class="img-flag" style="width: 50px; height: 50px; margin-left: 5px; padding-top: 5px;" />'
                            + '</div>'
                            + '<div class="col-md-10 pt-3">'
                            + '<span class="font-weight-bold" style="font-size: 25px;">'
                            + (data.customerName ? data.customerName : '. . . . .')
                            + '</span>'
                            + '</div>'
                            + '</div>'
                        );
                        return $state;
                    }

                    return $state;
                },
            }).on("select2:selecting", (e) => { }).on("select2:unselecting", (e) => { });
            // console.log(data_customer_select_option[0].id);
            $('#projectCustomer').val(data_customer_select_option[0].id).trigger('change');
        }
    });

    $("#addNewProjectSubmit").one('click', function () {
        // console.log("AJAX ongoing!!!");
        var form = document.getElementById('form-insert-project');
        var data = new FormData(form);

        $.ajax({
            url: "/api/project/insert",
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result, textStatus, jqXHR) {
                // console.log(result);
                if (result.status == "success") {
                    alertify.success(result.message).delay(3);
                }
                if (result.status == "failed") {
                    alertify.warning(result.message).delay(3);
                }
                $('#addNewProjectModal').modal('hide');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alertify.error("Thất bại! Vui lòng thử lại.").delay(3);
            }
        });
    });
});

$("#user-profile-btn").click(function () {
    $.ajax({
        url: "/api/getUserProfile/" + $(this).data("id"),
        success: function (result) {
            if (result.status == "success") {
                dataUserProfile = result.data;
                console.log(dataUserProfile);
                document.getElementById("userIdUpdate").value = dataUserProfile.id;
                document.getElementById("userNameUpdate").value = dataUserProfile.fullname;
                document.getElementById("userEmailUpdate").value = dataUserProfile.username;
                document.getElementById("userWorkCenterUpdate").value = dataUserProfile.workCenter;
                document.getElementById("userEmployeeCodeUpdate").value = dataUserProfile.employeeCode;
                document.getElementById("userRoleUpdate").value = dataUserProfile.userRole;
                document.getElementById("frameUserUpdate").src = "/images/user/" + (dataUserProfile.avatar != null ? dataUserProfile.avatar : "_default-user.png");
                if (dataUserProfile.avatar != null) {
                    document.getElementById("userAvatarNameUpdate").value = dataUserProfile.avatar;
                    document.getElementById("btnDeleteImgUserUpdate").disabled = false;
                } else {
                    document.getElementById("btnDeleteImgUserUpdate").disabled = true;
                }
                return;
            }
            if (result.status == "failed") {
                alertify.error(result.message).delay(2.5);
            }
        }
    });

    $("#userProfileEditModalContent").hide();
    $("#userProfileModalContent").show();
    $('#userProfileModal').modal('show');

    // $("#update-btn").click(function () {
    //     $("#userProfileModalContent").hide();
    //     $("#userProfileEditModalContent").show();
    // });

    // $("#update-cancel-btn").click(function () {
    //     $("#userProfileEditModalContent").hide();
    //     $("#userProfileModalContent").show();
    // });
});

$(".btnDeleteImg").click(function () {
    document.getElementById(this.dataset.input).value = null;
    document.getElementById(this.dataset.target).src = "/images/user/_default-user.png";
    this.disabled = true;
});

$('.customerAvatar').change(function (event) {
    if (event.target.files[0] == undefined) {
        var frame = document.getElementById(this.dataset.target);
        this.value = null;
        frame.src = "/images/user/_default-user.png";
        return;
    }
    if (event.target.files[0].length != 0) {
        if (event.target.files[0].name.match(/\.(jpg|jpeg|png|gif)$/)) {
            var frame = document.getElementById(this.dataset.target);
            frame.src = URL.createObjectURL(event.target.files[0]);
            var spaceWidth = document.getElementById("avatarUserDisplay").offsetWidth - 30; // 15px padding-left & 15px padding-right
            frame.width = frame.width * (spaceWidth / frame.width);
            frame.height = frame.height * (spaceWidth / frame.width);
            if (event.target.files[0].name != "_default-user.png") {
                document.getElementById(this.dataset.delete).disabled = false;
            } else {
                document.getElementById(this.dataset.delete).disabled = true;
            }
        } else {
            document.getElementById('customerAvatar').value = null;
            frame.src = "/images/user/_default-user.png";
            alertify.warning('Mời chọn đúng file định dạng ảnh').delay(2.5);
        }
    }

    // console.log(event.target.files[0].name);
    // console.log(frame.width / spaceWidth + " - " + spaceWidth / frame.width);
    // console.log(frame.width + " - " + frame.height);
    // console.log(spaceWidth);
});

