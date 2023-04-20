$("#user-profile-btn").click(function () {
    $.ajax({
        url: "/api/getUserProfile/" + $(this).data("id"),
        success: function (result) {
            console.log(result);
        }
    });

    $("#userProfileEditModalContent").hide();
    $("#userProfileModalContent").show();
    $('#userProfileModal').modal('show');

    $("#update-btn").click(function () {
        $("#userProfileModalContent").hide();
        $("#userProfileEditModalContent").show();
    });

    $("#update-cancel-btn").click(function () {
        $("#userProfileEditModalContent").hide();
        $("#userProfileModalContent").show();
    });
});