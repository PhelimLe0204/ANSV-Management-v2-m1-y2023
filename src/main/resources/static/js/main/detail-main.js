var $week = null;
var $current_year = null;
var $first_project_id = null;
var $first_project_type_id = null;
var $first_project_priority_id = null;
var $first_project_status_id = null;

$(document).ready(function () {
    alertify.confirm().set({
        onshow: function () {
            $(".ajs-dialog").addClass("ajs-dialog-custom");
        }
    });
    var urlParams = new URLSearchParams(window.location.search);
    var updateSuccess = urlParams.get('updateSuccess');
    var tab = urlParams.get('tab');
    var message = detectMessage(tab);

    if (updateSuccess && !message) {
        alertify.warning('Hệ thống không nhận diện được hành động của bạn!');
    }
    if (updateSuccess == 'true' && message) {
        alertify.success('Cập nhật mục "' + message + '" thành công!').delay(1.5);
    }
    if (updateSuccess == 'false' && message) {
        alertify.error('Cập nhật mục "' + message + '" không thành công!').delay(1.5);
    }

    var currentYear = new Date().getFullYear();
    $current_year = currentYear;
    theLastDayOfYear = new Date(currentYear, 11, 31);
    var year = new Date(theLastDayOfYear.getFullYear(), 0, 1);
    var days = Math.floor((theLastDayOfYear - year) / (24 * 60 * 60 * 1000));
    var week = Math.ceil((theLastDayOfYear.getDay() + 1 + days) / 7);
    console.log("The current year (" + currentYear + ") has " + (week + 1) + " week.");
    $week = week + 1;
});

/* ===== Start: Tab 1 ===== */
$("#phan-loai-open-modal-edit").click(function () {
    $first_project_id = $("#project_id").attr("data-first");
    $first_project_type_id = $("#project_type_id").attr("data-first");
    $first_project_priority_id = $("#project_priority_id").attr("data-first");
    $first_project_status_id = $("#project_status_id").attr("data-first");
    var $first_week = $("#week").attr("data-first");
    var $first_year = $("#year").attr("data-first");
    // var $first_ma_hop_dong = $("#ma_hop_dong").attr("data-first");
    // var $first_ma_ke_toan = $("#ma_ke_toan").attr("data-first");
    var $first_currency_unit_id = $("#currency_unit_id").attr("data-first");
    var $formDataOrigin = getFormData($("#form-tab-phan-loai-edit"));

    var data_project_select_option;

    // Bắt sự kiện submit form
    $("#form-tab-phan-loai-edit").submit(function (e) {
        e.preventDefault();

        var dataCompare = getFormData($(this));
        if (dataCompare == $formDataOrigin) {
            // alertify.notify('Bạn chưa thay đổi dữ liệu!', 'custom', 1.5);
            // alertify.notify('Bạn chưa thay đổi dữ liệu!', 'custom', 5, function () { console.log('dismissed'); });
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }

        alertify.confirm(
            'Xác nhận cập nhật',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Cập nhật mục "<span class="text-primary font-weight-bold">PHÂN LOẠI</span>"<br>'
            + 'Bạn chắc chứ?'
            + '</p>',
            function () {
                e.currentTarget.submit(); // OK => Allow form submit
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });

    // Bắt sự kiện reset form
    $("#btn-tab-phan-loai-edit-reset").click(function () {
        var dataCompare = getFormData($("#form-tab-phan-loai-edit"));
        if (dataCompare == $formDataOrigin) {
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }


        alertify.confirm(
            'Xác nhận hoàn tác',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Hoàn tác dữ liệu đã thay đổi<br><span class="text-primary font-weight-bold">PHÂN LOẠI</span><br>'
            + 'Bạn chắc chứ?'
            + '</p>',
            function () {
                // Ok => Reset modal update
                resetModalUpdate(1);
                alertify.success('Dữ liệu hoàn tác!').delay(1);
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });

    // Ajax get data for project's selection
    $.ajax({
        url: "/api/getProjectSelectOption",
        success: function (result) {
            data_project_select_option = result.data;

            $("#project_id").select2({
                dropdownParent: $('#project-name-selection'),
                placeholder: 'Dự án chính thức...',
                data: data_project_select_option,
                allowClear: true,
                selectOnClose: true,
                templateResult: function (data, container) {
                    var $state = $(
                        '<div class="row">'
                        + '<div class="col-md-2">'
                        + '<img src="/images/logo/'
                        + (data.customer_avatar ? data.customer_avatar : 'image_undefined.jpg')
                        + '" class="img-flag" style="width: 45px; height: 45px; margin-left: 10px;" />'
                        + '</div>'
                        + '<div class="col-md-10">'
                        + '<span class="font-weight-bold">'
                        + (data.project_name ? data.project_name : '. . . . .')
                        + '</span><br>'
                        + '<span>'
                        + (data.customer_name ? data.customer_name : '. . . . .')
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
                        + '<span class="font-weight-bold">Mời chọn dự án...</span>'
                        + '</div>'
                        + '</div>'
                    );

                    if (data.id && data.project_name) {
                        var $state = $(
                            '<div class="row">'
                            + '<div class="col-md-2 pb-1">'
                            + '<img src="/images/logo/'
                            + (data.customer_avatar ? data.customer_avatar : 'image_undefined.jpg')
                            + '" class="img-flag" style="width: 54px; height: 54px; margin-left: 5px; padding-top: 5px;" />'
                            + '</div>'
                            + '<div class="col-md-10">'
                            + '<span class="font-weight-bold">' + data.project_name + '</span><br>'
                            + '<span>' + (data.customer_name ? data.customer_name : '. . . . .') + '</span>'
                            + '</div>'
                            + '</div>'
                        );
                        return $state;
                    }

                    return $state;
                },
            }).on("select2:selecting", (e) => { }).on("select2:unselecting", (e) => { });

            $("#project_id").select2("val", $first_project_id);
        }
    });

    // Form input: project_type_id
    $(".project-step").click(function () {
        var onFocus = $(this).hasClass("btn-primary");

        if (onFocus == true) {
            return;
        }

        var find_data = $(this).closest('.step-class').find('div[data-status="active"]');
        find_data.removeClass("btn-primary").addClass("disabled btn-outline-secondary");
        find_data.attr('data-status', 'notActive');
        $(this).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
        $(this).attr('data-status', 'active');
        // $("#project_type_id").val($(this).attr("id"));
        $("#project_type_id").val($(this).attr("data-input"));
    });

    // Form input: project_priority_id
    $(".project-priority").click(function () {
        var onFocus = $(this).hasClass("btn-primary");

        if (onFocus == true) {
            return;
        }

        var find_data = $(this).closest('.priority-class').find('div[data-status="active"]');
        find_data.removeClass("btn-primary").addClass("disabled btn-outline-secondary");
        find_data.attr('data-status', 'notActive');
        $(this).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
        $(this).attr('data-status', 'active');
        $("#project_priority_id").val($(this).attr("data-input"));
    });

    // Form input: project_status_id
    $(".project-status").click(function () {
        var onFocus = $(this).hasClass("disabled");

        if (onFocus != true) {
            return;
        }

        var find_data = $(this).closest('.status-class').find('div[data-status="active"]');
        var old_color = find_data.attr("data-color");
        var old_color_class_active = "btn-" + old_color;
        var old_color_class_not_active = "disabled btn-outline-" + old_color;
        find_data.removeClass(old_color_class_active).addClass(old_color_class_not_active);
        find_data.attr('data-status', 'notActive');
        $(this).removeClass("disabled btn-outline-" + $(this).attr("data-color")).addClass("btn-" + $(this).attr("data-color"));
        $(this).attr('data-status', 'active');
        $("#project_status_id").val($(this).attr("data-input"));
    });

    var htmlSelectWeek = null;
    for (let i = 1; i <= $week; i++) {
        if (i == $first_week) {
            htmlSelectWeek += '<option value="' + i + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
        } else {
            htmlSelectWeek += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#week").html(htmlSelectWeek);

    var htmlSelectYear = null;
    for (let i = 2021; i <= $current_year; i++) {
        if (i == $first_year) {
            htmlSelectYear += '<option value="' + i + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
        } else {
            htmlSelectYear += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#year").html(htmlSelectYear);

    // Form input: currency_unit_id
    $.ajax({
        url: "/api/getCurrencyUnitSelectOption",
        success: function (result) {
            var htmlSelectCurrencyUnit = null;
            for (let i = 0; i < result.data.length; i++) {
                if (result.data[i].id == $first_currency_unit_id) {
                    htmlSelectCurrencyUnit += '<option value="' + result.data[i].id
                        + '" class="text-white bg-secondary font-weight-bold" selected>'
                        + result.data[i].currency_unit + '</option>';
                } else {
                    htmlSelectCurrencyUnit += '<option value="' + result.data[i].id + '">'
                        + result.data[i].currency_unit + '</option>';
                }
            }
            $("#currency_unit_id").html(htmlSelectCurrencyUnit);
        }
    });

    // Close Modal update tab 1
    $(".tab-phan-loai-edit-modal-close").click(function () {
        var dataCompare = getFormData($("#form-tab-phan-loai-edit"));
        if (dataCompare == $formDataOrigin) {
            $('#tabPhanLoaiEditModal').modal('hide');
            return;
        }

        alertify.confirm(
            'Xác nhận hủy',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Dữ liệu hiện tại sẽ hoàn tác<br><span class="text-primary font-weight-bold">PHÂN LOẠI</span><br>'
            + 'Bạn chắc chắn muốn hủy?'
            + '</p>',
            function () {
                // Ok => Reset modal update, then close modal
                resetModalUpdate(1);
                $('#tabPhanLoaiEditModal').modal('hide');
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });
});
/* ===== End: Tab 1 ===== */



/* ===== Start: Tab 2 ===== */
$("#thanh-vien-open-modal-edit").click(function () {
    console.log("thanh-vien-open-modal-edit");

    $first_am_manager_id = $("#am_manager").attr("data-first");
    var data_user_select_option;

    // Ajax get data for project's selection
    $.ajax({
        url: "/api/getUserSelectOption",
        success: function (result) {
            data_user_select_option = result.data;

            $("#am_manager").select2({
                dropdownParent: $('#manager-am-selection'),
                placeholder: 'Quản lý AM / Phó ban BDC...',
                data: data_user_select_option,
                allowClear: true,
                selectOnClose: true,
                templateResult: function (data, container) {
                    var $state = $(
                        '<div class="row">'
                        + '<div class="col-md-2">'
                        + '<img src="/images/user/'
                        + (data.avatar ? data.avatar : 'image_undefined.jpg')
                        + '" class="img-flag" style="width: 45px; height: 45px; margin-left: 10px;" />'
                        + '</div>'
                        + '<div class="col-md-10">'
                        + '<span class="font-weight-bold">'
                        + (data.fullname ? data.fullname : '. . . . .')
                        + '</span><br>'
                        + '<span>Mã: '
                        + (data.employee_code ? data.employee_code : '. . . . .')
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
                        + '<span class="font-weight-bold">Mời chọn người quản lý AM...</span>'
                        + '</div>'
                        + '</div>'
                    );

                    if (data.id && data.fullname) {
                        var $state = $(
                            '<div class="row">'
                            + '<div class="col-md-3 pb-1">'
                            + '<img src="/images/user/'
                            + (data.avatar ? data.avatar : 'image_undefined.jpg')
                            + '" class="img-flag" style="width: 54px; height: 54px; margin-left: 5px; padding-top: 5px;" />'
                            + '</div>'
                            + '<div class="col-md-9">'
                            + '<span class="font-weight-bold">' + data.fullname + '</span><br>'
                            + '<span>Mã: ' + (data.employee_code ? data.employee_code : '. . . . .') + '</span>'
                            + '</div>'
                            + '</div>'
                        );
                        return $state;
                    }

                    return $state;
                },
            }).on("select2:selecting", (e) => { }).on("select2:unselecting", (e) => { });

            $("#am_manager").select2("val", $first_am_manager_id);
        }
    });
});
/* ===== End: Tab 2 ===== */

function detectMessage(data) {
    if (data == 1) {
        return 'PHÂN LOẠI';
    }
    if (data == 2) {
        return 'THÀNH VIÊN';
    }
    if (data == 3) {
        return 'DỰ THẦU';
    }
    if (data == 4) {
        return 'CHI PHÍ & THỜI GIAN';
    }
    if (data == 5) {
        return 'QUA TRÌNH';
    }
    return null;
}

// Get Form data
function getFormData(form) {
    var data = JSON.stringify($(form).serializeArray());
    return data;
}

// Reset form data and modal template
function resetModalUpdate(tab) {
    if (tab == 1) {
        /* === Reset dự án === */
        $("#project_id").select2("val", $first_project_id); // Reset dự án

        /* === Reset giai đoạn === */
        var current_project_type_id = $('.project-step').closest('.step-class').find('div[data-status="active"]').attr('id');
        // console.log(current_project_type.attr('id') + " - " + current_project_priority.attr('id') + " - " + current_project_status.attr('id'));
        // console.log("Giai đoạn trước / sau chọn: " + $first_project_type_id + " / " + current_project_type_id);
        if (current_project_type_id != $first_project_type_id) {
            // Reset template
            $('#' + current_project_type_id).removeClass("btn-primary").addClass("disabled btn-outline-secondary");
            $('#' + current_project_type_id).attr('data-status', 'notActive');
            $('#project-type-' + $first_project_type_id).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
            $('#project-type-' + $first_project_type_id).attr('data-status', 'active');
        }

        /* === Reset mức độ ưu tiên === */
        var current_project_priority_id = $('.project-priority').closest('.priority-class').find('div[data-status="active"]').attr('id');
        // console.log("Ưu tiên trước / sau chọn: " + $first_project_priority_id + " / " + current_project_priority_id);
        if (current_project_priority_id != $first_project_priority_id) {
            // Reset template
            $('#' + current_project_priority_id).removeClass("btn-primary").addClass("disabled btn-outline-secondary");
            $('#' + current_project_priority_id).attr('data-status', 'notActive');
            $('#project-priority-' + $first_project_priority_id).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
            $('#project-priority-' + $first_project_priority_id).attr('data-status', 'active');
        }

        /* === Reset trạng thái === */
        var current_project_status_id = $('.project-status').closest('.status-class').find('div[data-status="active"]').attr('id');
        // console.log("Trạng thái trước / sau chọn: " + $first_project_status_id + " / " + current_project_status_id);
        if (current_project_status_id != $first_project_status_id) {
            // Reset template
            var current_color = $('#' + current_project_status_id).attr("data-color");
            var current_color_class_active = "btn-" + current_color;
            var current_color_class_not_active = "disabled btn-outline-" + current_color;
            $('#' + current_project_status_id).removeClass(current_color_class_active).addClass(current_color_class_not_active);
            $('#' + current_project_status_id).attr('data-status', 'notActive');

            var first_color = $('#project-status-' + $first_project_status_id).attr("data-color");
            var first_color_class_active = "btn-" + first_color;
            var first_color_class_not_active = "disabled btn-outline-" + first_color;
            $('#project-status-' + $first_project_status_id).removeClass(first_color_class_not_active).addClass(first_color_class_active);
            $('#project-status-' + $first_project_status_id).attr('data-status', 'active');
        }

        $("#form-tab-phan-loai-edit")[0].reset(); // Hoàn tác dữ liệu form

        // console.log("Dữ liệu form trước khi thay đổi: " + $formDataOrigin);
        // console.log("Dữ liệu form sau khi thay đổi: " + dataCompare);
        return;
    } else {
        alertify.warning('Không xác định đối tượng!').delay(1);
    }
}

// $("#project_name").select2({
//     dropdownParent: $('#project-name-selection'),
//     ajax: {
//         url: "/api/getProjectSelectOption",
//         dataType: "json",
//         type: "GET",
//         data: function (params) {
//             var queryParameters = {
//                 term: params.term
//             }
//             return queryParameters;
//         },
//         processResults: function (data) {
//             return {
//                 results: $.map(data, function (item) {
//                     return {
//                         text: item[0].project_name,
//                         id: item[0].id
//                     }
//                 })
//             };
//         }
//     }
// });