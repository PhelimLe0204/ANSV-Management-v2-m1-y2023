$(document).ready(function () {
    $(".link-none").on("click", function (event) {
        event.preventDefault();
        alertify.confirm('Thông báo', 'Chức năng đang trong quá trình phát triển! Mời bạn quay trở lại sau.'
            , function () { }, function () { });
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

