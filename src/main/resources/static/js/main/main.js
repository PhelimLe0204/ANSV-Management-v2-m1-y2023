$("#user-profile-btn").click(function () {
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