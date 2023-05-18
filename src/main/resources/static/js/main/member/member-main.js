var dataSet = [
    {
        fullname: "abcxyz",
        username: "abcxyz@ansv.vn"
    }
];

$(document).ready(function () {
    $('#tableMemberLD').DataTable({
        data: dataSet,
        lengthMenu: [[5, 10, 25, 50, 100, -1], [5, 10, 25, 50, 100, "All"]],
        columnDefs: [
            {
                targets: 0,
                className: 'dt-body-center',
                render: function (data, type, row, meta) {
                    return meta.row + 1;
                }
            },
            {
                targets: 1,
                render: function (data, type, row) {
                    var html = '<div class="d-inline-block align-middle">' +
                        '<div class="d-inline-block">' +
                        '<h6>' + data.fullname + '</h6>' +
                        '<p class="text-muted m-b-0">' + data.username + '</p>' +
                        '</div>' +
                        '</div>';
                    return html;
                }
            },
            {
                orderable: false,
                targets: 2,
                render: function (data, type, row) {
                    if (data.used == 0) {
                        return '<input type="checkbox" data-toggle="toggle" data-size="sm">';
                    } else {
                        return '<input type="checkbox" checked data-toggle="toggle" data-size="sm">';
                    }
                }
            },
        ],
        columns: [
            { data: null },
            { data: null },
            { data: null },
        ],
        drawCallback: function (settings) {
            $("[data-toggle='toggle']").bootstrapToggle('destroy')
            $("[data-toggle='toggle']").bootstrapToggle();
        }
    });

    let groupMember = null;

    if (location.pathname.substring(1) == "thanh-vien/bcsa") {
        groupMember = "bcsa";
    }

    if (location.pathname.substring(1) == "thanh-vien/bdc") {
        groupMember = "bdc";
    }

    if (location.pathname.substring(1) == "thanh-vien/do") {
        groupMember = "do";
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

            dataSet = result.data;
            $('#tableMemberLD').DataTable().clear();
            $('#tableMemberLD').DataTable().rows.add(dataSet).draw();
            $("[data-toggle='toggle']").bootstrapToggle('destroy')
            $("[data-toggle='toggle']").bootstrapToggle();
            return 1;
        }
    });

    return 0;
}