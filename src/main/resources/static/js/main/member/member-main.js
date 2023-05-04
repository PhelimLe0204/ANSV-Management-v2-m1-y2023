$(document).ready(function () {
    let groupMember = null;

    if (location.pathname.substring(1) == "thanh-vien/bdc") {
        groupMember = "am";
    }

    if (location.pathname.substring(1) == "thanh-vien/do") {
        groupMember = "pm";
    }

    if (groupMember != null) {
        console.log("groupMember: " + groupMember);
        getMemberLdap(groupMember);
    }

    $('.checkEnabled').change(function () {
        var thisId = $(this).attr("id");
        let enabled = 0;
        if ($(this).attr("data-value") == 0) {
            enabled = 1;
        }
        let id = ($(this).attr("data-id"));
        $.ajax({
            url: "/api/member/updateEnabled/" + id + "/" + enabled,
            success: function (result) {
                $('#' + thisId).attr("data-value", enabled);
                alertify.success(result.message).delay(1);
            }
        });
    });

    $(".card-option .reload-card-member-ldap").on('click', function () {
        var $this = $(this);
        $this.parents('.card').addClass("card-load");
        $this.parents('.card').append('<div class="card-loader"><i class="pct-loader1 anim-rotate"></div>');
        getMemberLdap(groupMember);
        setTimeout(function () {
            $this.parents('.card').children(".card-loader").remove();
            $this.parents('.card').removeClass("card-load");
        }, 1000);
    });
});

function getMemberLdap(groupMember) {
    $.ajax({
        url: "/ldap/user/" + groupMember,
        success: function (result) {
            console.log('----- LDAP Get all users of group "' + groupMember + '"');
            console.log(result);

            var memberLdapHtml;
            for (let i = 0; i < result.data.length; i++) {
                memberLdapHtml += ('<tr>' + '<td>' +
                    '<div class="d-inline-block align-middle">' +
                    '<div class="d-inline-block">' +
                    '<h6>' + result.data[i].fullname + '</h6>' +
                    '<p class="text-muted m-b-0">' + result.data[i].username + '</p>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '<td class="align-middle text-center" style="width: 120px;">' +
                    (result.data[i].used == 0
                        ? '<input type="checkbox" data-toggle="toggle" data-size="sm">'
                        : '<input type="checkbox" checked data-toggle="toggle" data-size="sm">') +
                    '</td>' +
                    '</tr>');
            }
            $("#body-list-member-ldap").html(memberLdapHtml);
            $("[data-toggle='toggle']").bootstrapToggle('destroy')
            $("[data-toggle='toggle']").bootstrapToggle();
            return 1;
        }
    });

    return 0;
}