var $week = null;
var $current_year = null;

var $first_project_id = null;
var $first_project_type_id = null;
var $first_project_priority_id = null;
var $first_project_status_id = null;

var $first_customer_id = null;

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



/* ===== Start: Tab phân loại ===== */
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
    var $formDataOrigin = getFormData($("#form-tab-phan-loai-edit")); // old form data

    var data_project_select_option;

    // Bắt sự kiện SUBMIT form tab "Phân loại"
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

    // Bắt sự kiện RESET form tab "Phân loại"
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
                        + (data.customerAvatar ? data.customerAvatar : 'image_undefined.jpg')
                        + '" class="img-flag" style="width: 45px; height: 45px; margin-left: 10px;" />'
                        + '</div>'
                        + '<div class="col-md-10">'
                        + '<span class="font-weight-bold">'
                        + (data.projectName ? data.projectName : '. . . . .')
                        + '</span><br>'
                        + '<span>'
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
                        + '<span class="font-weight-bold">Mời chọn dự án...</span>'
                        + '</div>'
                        + '</div>'
                    );

                    if (data.id && data.projectName) {
                        var $state = $(
                            '<div class="row">'
                            + '<div class="col-md-2 pb-1">'
                            + '<img src="/images/logo/'
                            + (data.customerAvatar ? data.customerAvatar : 'image_undefined.jpg')
                            + '" class="img-flag" style="width: 54px; height: 54px; margin-left: 5px; padding-top: 5px;" />'
                            + '</div>'
                            + '<div class="col-md-10">'
                            + '<span class="font-weight-bold">' + data.projectName + '</span><br>'
                            + '<span>' + (data.customerName ? data.customerName : '. . . . .') + '</span>'
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
                        + '" class="pt-2 pb-2 text-white bg-secondary font-weight-bold" selected>'
                        + result.data[i].currencyUnit + ' | ' + result.data[i].description + '</option>';
                } else {
                    htmlSelectCurrencyUnit += '<option value="' + result.data[i].id + '" class="pt-2 pb-2">'
                        + result.data[i].currencyUnit + ' | ' + result.data[i].description + '</option>';
                }
            }
            $("#currency_unit_id").html(htmlSelectCurrencyUnit);
        }
    });

    // CLOSE Modal update tab "Phân loại"
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
/* ===== End: Tab phân loại ===== */



/* ===== Start: Tab dự thầu ===== */
$("#du-thau-open-modal-edit").click(function () {
    var data_customer_select_option;
    var $formDataOrigin = getFormData($("#form-tab-du-thau-edit")); // old form data

    $first_customer_id = $("#customer_id").attr("data-first");

    $.ajax({
        url: "/api/getCustomerSelectOption",
        success: function (result) {
            console.log(result.data);
            data_customer_select_option = result.data;

            $("#customer_id").select2({
                dropdownParent: $('#customer-selection'),
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

            $("#customer_id").select2("val", $first_customer_id);
        }
    });

    $("#muc_do_kha_thi").ionRangeSlider({
        min: 0,
        max: 100
        // from: 550
    });

    // CLOSE Modal update tab "Dự thầu"
    $(".tab-du-thau-edit-modal-close").click(function () {
        var dataCompare = getFormData($("#form-tab-du-thau-edit"));
        if (dataCompare == $formDataOrigin) {
            $('#tabDuThauEditModal').modal('hide');
            return;
        }

        alertify.confirm(
            'Xác nhận hủy',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Dữ liệu hiện tại sẽ hoàn tác<br><span class="text-primary font-weight-bold">DỰ THẦU</span><br>'
            + 'Bạn chắc chắn muốn hủy?'
            + '</p>',
            function () {
                // Ok => Reset modal update, then close modal
                resetModalUpdate(2);
                $('#tabDuThauEditModal').modal('hide');
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });

    // Bắt sự kiện SUBMIT form tab "Dự thầu"
    $("#form-tab-du-thau-edit").submit(function (e) {
        e.preventDefault();

        var dataCompare = getFormData($(this));
        if (dataCompare == $formDataOrigin) {
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }

        alertify.confirm(
            'Xác nhận cập nhật',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Cập nhật mục "<span class="text-primary font-weight-bold">DỰ THẦU</span>"<br>'
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

    // Bắt sự kiện RESET form tab "Dự thầu"
    $("#btn-tab-du-thau-edit-reset").click(function () {
        var dataCompare = getFormData($("#form-tab-du-thau-edit"));
        if (dataCompare == $formDataOrigin) {
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }

        alertify.confirm(
            'Xác nhận hoàn tác',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Hoàn tác dữ liệu đã thay đổi<br><span class="text-primary font-weight-bold">DỰ THẦU</span><br>'
            + 'Bạn chắc chứ?'
            + '</p>',
            function () {
                // Ok => Reset modal update
                resetModalUpdate(2);
                alertify.success('Dữ liệu hoàn tác!').delay(1);
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });
});
/* ===== End: Tab dự thầu ===== */



/* ===== Start: Tab thời gian & chi phí ===== */
$("#chi-phi-thoi-gian-open-modal-edit").click(function () {
    var $formDataOrigin = getFormData($("#form-tab-chi-phi-thoi-gian-edit")); // old form data

    $("#hop_dong_dac, #hop_dong_pac, #hop_dong_fac, #ke_hoach_tam_ung").datepicker({
        dateFormat: 'dd / mm / yy'
    });

    $("#muc_tieu_dac, #muc_tieu_pac, #muc_tieu_fac").datepicker({
        dateFormat: 'dd / mm / yy',
        onSelect: function (dateMucTieu) {
            // var related_id = $(this).attr("data-related-id"); // ID ngày thực tế tương ứng
            var calculate_result_id = $(this).attr("data-calculate-result");
            var dateThucTe = $("#" + $(this).attr("data-related-id")).val();
            // console.log("========================================================================");
            // console.log("Ngày mục tiêu: " + dateMucTieu);
            // console.log("ID ngày thực tế tương ứng: " + related_id);
            // console.log("Ngày thực tế: " + dateThucTe);
            // console.log("Selected date: " + dateMucTieu + "; input's current value: " + this.value);

            // To set two dates to two variables
            var date1 = new Date(dateMucTieu.slice(5, 7) + "/" + dateMucTieu.slice(0, 2) + "/" + dateMucTieu.slice(10, 15));
            var date2 = new Date(dateThucTe.slice(5, 7) + "/" + dateThucTe.slice(0, 2) + "/" + dateThucTe.slice(10, 15));
            if (dateThucTe == "") {
                var dateNow = new Date();
                var c_m = dateNow.getUTCMonth() + 1; //months from 1-12
                var c_d = dateNow.getUTCDate();
                var c_y = dateNow.getUTCFullYear();
                date2 = new Date(c_m + "/" + c_d + "/" + c_y);
            }

            var diffTime = date2 - date1; // To calculate the time difference of two dates
            var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // To calculate the no. of days between two dates

            if (diffDays == 0) {
                $("#" + calculate_result_id).html(("Đúng tiến độ"));
            } else if (diffDays < 0) {
                $("#" + calculate_result_id).html(("Sớm " + Math.abs(diffDays) + " ngày"));
            } else {
                $("#" + calculate_result_id).html(("Chậm " + diffDays + " ngày"));
            }
        }
    });

    $("#thuc_te_dac, #thuc_te_pac, #thuc_te_fac").datepicker({
        dateFormat: 'dd / mm / yy',
        onSelect: function (dateThucTe) {
            var this_id = $(this).attr("id");
            // var related_id = $(this).attr("data-related-id"); // ID ngày mục tiêu tương ứng
            var calculate_result_id = $(this).attr("data-calculate-result");
            var dateMucTieu = $("#" + $(this).attr("data-related-id")).val();
            // console.log("========================================================================");
            // console.log("Ngày thực tế: " + dateThucTe);
            // console.log("ID ngày mục tiêu tương ứng: " + related_id);
            // console.log("Ngày mục tiêu: " + dateMucTieu);
            // console.log("Selected date: " + dateThucTe + "; input's current value: " + this.value);

            if (dateMucTieu != "") {
                // To set two dates to two variables
                var date1 = new Date(dateThucTe.slice(5, 7) + "/" + dateThucTe.slice(0, 2) + "/" + dateThucTe.slice(10, 15));
                var date2 = new Date(dateMucTieu.slice(5, 7) + "/" + dateMucTieu.slice(0, 2) + "/" + dateMucTieu.slice(10, 15));

                var diffTime = date2 - date1; // Calculate the time difference of two dates
                var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // To calculate the no. of days between two dates

                if (diffDays == 0) {
                    $("#" + calculate_result_id).html(("Đúng tiến độ"));
                } else if (diffDays < 0) {
                    $("#" + calculate_result_id).html(("Chậm " + Math.abs(diffDays) + " ngày"));
                } else {
                    $("#" + calculate_result_id).html(("Sớm " + diffDays + " ngày"));
                }
            } else {
                $("#" + calculate_result_id).html((""));
            }

            if ($("[data-status-for=" + this_id + "]").hasClass("fa-square")) {
                console.log("Có class fa-square");
                $("[data-status-for=" + this_id + "]").removeAttr('class');
                $("[data-status-for=" + this_id + "]").attr('class', 'fa-sharp fa-solid fa-square-check text-primary');
            }
        }
    });

    $(".btn-delete-input").click(function () {
        var target_id = $(this).attr("data-target");
        $("#" + target_id).val("");

        // Trường hợp xóa ngày mục tiêu => Không còn ghi chú
        if (target_id == "muc_tieu_dac" || target_id == "muc_tieu_pac" || target_id == "muc_tieu_fac") {
            $("#chenh_lech_" + target_id.slice(9, 12)).html((""));
        }

        if (target_id == "thuc_te_dac" || target_id == "thuc_te_pac" || target_id == "thuc_te_fac") {
            // Tính toán lại số ngày chênh lệch giữa ngày mục tiêu và hiện tại
            var dateMucTieuValue = $("#muc_tieu_" + target_id.slice(8, 11)).val();

            if (dateMucTieuValue != "") {
                var dateMucTieu = new Date(dateMucTieuValue.slice(5, 7) + "/" + dateMucTieuValue.slice(0, 2) + "/" + dateMucTieuValue.slice(10, 15));
                var dateNow = new Date();
                dateNowFormat = new Date((dateNow.getUTCMonth() + 1) + "/" + (dateNow.getUTCDate()) + "/" + (dateNow.getUTCFullYear()));
                var diffTime = dateNowFormat - dateMucTieu; // Calculate the time difference of two dates
                var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // To calculate the no. of days between two dates

                if (diffDays == 0) {
                    $("#chenh_lech_" + target_id.slice(8, 11)).html(("Deadline"));
                } else if (diffDays < 0) {
                    $("#chenh_lech_" + target_id.slice(8, 11)).html(("Còn " + Math.abs(diffDays) + " ngày"));
                } else {
                    $("#chenh_lech_" + target_id.slice(8, 11)).html(("Quá " + diffDays + " ngày"));
                }
            } else {
                $("#chenh_lech_" + target_id.slice(8, 11)).html((""));
            }
        }

        if ($("#" + target_id).attr("data-allow-change-status") == "1") {
            if ($("[data-status-for=" + target_id + "]").hasClass("fa-square-check")) {
                $("[data-status-for=" + target_id + "]").removeAttr('class');
                $("[data-status-for=" + target_id + "]").attr('class', 'fa-solid fa-square text-secondary');
            }
        }
    });

    // Bắt sự kiện SUBMIT form tab "Chi phí & thời gian"
    $("#form-tab-chi-phi-thoi-gian-edit").submit(function (e) {
        e.preventDefault();

        var dataCompare = getFormData($(this));
        if (dataCompare == $formDataOrigin) {
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }

        alertify.confirm(
            'Xác nhận cập nhật',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Cập nhật mục "<span class="text-primary font-weight-bold">CHI PHÍ & THỜI GIAN</span>"<br>'
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

    // Bắt sự kiện RESET form tab "Chi phí & thời gian"
    $("#btn-tab-chi-phi-thoi-gian-edit-reset").click(function () {
        var dataCompare = getFormData($("#form-tab-chi-phi-thoi-gian-edit"));
        if (dataCompare == $formDataOrigin) {
            alertify.warning('Bạn chưa thay đổi dữ liệu!').delay(1.5);
            return;
        }

        alertify.confirm(
            'Xác nhận hoàn tác',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Hoàn tác dữ liệu đã thay đổi<br>'
            + '<span class="text-primary font-weight-bold">CHI PHÍ & THỜI GIAN</span><br>'
            + 'Bạn chắc chứ?'
            + '</p>',
            function () {
                // Ok => Reset modal update
                resetModalUpdate(3);
                alertify.success('Dữ liệu hoàn tác!').delay(1);
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });

    // CLOSE Modal update tab "Chi phí & thời gian"
    $(".tab-chi-phi-thoi-gian-edit-modal-close").click(function () {
        var dataCompare = getFormData($("#form-tab-chi-phi-thoi-gian-edit"));
        if (dataCompare == $formDataOrigin) {
            $('#tabChiPhiThoiGianEditModal').modal('hide');
            return;
        }

        alertify.confirm(
            'Xác nhận hủy',
            '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
            + '<p class="text-center">'
            + 'Dữ liệu hiện tại sẽ hoàn tác<br>'
            + '<span class="text-primary font-weight-bold">CHI PHÍ & THỜI GIAN</span><br>'
            + 'Bạn chắc chắn muốn hủy?'
            + '</p>',
            function () {
                // Ok => Reset modal update, then close modal
                resetModalUpdate(3);
                $('#tabChiPhiThoiGianEditModal').modal('hide');
            },
            function () {
                // Cancel => Do nothing
            }
        );
    });
});
/* ===== End: Tab thời gian & chi phí ===== */



function dateDifferences(tab) {

}



$(".card-1-2, .card-2-2").hide();
/* ===== Start: Tab quá trình ===== */
$(".btn-change-data-view").click(function () {
    // var data_origin = $(this).attr("data-origin");
    // var data_change = $(this).attr("data-change");
    $("." + $(this).attr("data-origin")).hide();
    $("." + $(this).attr("data-change")).show();
    // $(this).attr('data-origin', data_change);
    // $(this).attr('data-change', data_origin);
});
/* ===== End: Tab quá trình ===== */



/* ===== Start: Tab thành viên ===== */
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
/* ===== End: Tab thành viên ===== */



function detectMessage(data) {
    if (data == 1) {
        return 'PHÂN LOẠI';
    }
    if (data == 2) {
        return 'DỰ THẦU';
    }
    if (data == 3) {
        return 'CHI PHÍ & THỜI GIAN';
    }
    if (data == 4) {
        return 'QUÁ TRÌNH';
    }
    if (data == 5) {
        return 'THÀNH VIÊN';
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
        /* Tab "Phân loại" */
        $("#project_id").select2("val", $first_project_id); // Reset dự án

        // Reset giai đoạn
        var current_project_type_id = $('.project-step').closest('.step-class').find('div[data-status="active"]').attr('id');
        if (current_project_type_id != $first_project_type_id) {
            // Reset template
            $('#' + current_project_type_id).removeClass("btn-primary").addClass("disabled btn-outline-secondary");
            $('#' + current_project_type_id).attr('data-status', 'notActive');
            $('#project-type-' + $first_project_type_id).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
            $('#project-type-' + $first_project_type_id).attr('data-status', 'active');
        }

        /* === Reset mức độ ưu tiên === */
        var current_project_priority_id = $('.project-priority').closest('.priority-class').find('div[data-status="active"]').attr('id');
        if (current_project_priority_id != $first_project_priority_id) {
            // Reset template
            $('#' + current_project_priority_id).removeClass("btn-primary").addClass("disabled btn-outline-secondary");
            $('#' + current_project_priority_id).attr('data-status', 'notActive');
            $('#project-priority-' + $first_project_priority_id).removeClass("disabled btn-outline-secondary").addClass("btn-primary");
            $('#project-priority-' + $first_project_priority_id).attr('data-status', 'active');
        }

        /* === Reset trạng thái === */
        var current_project_status_id = $('.project-status').closest('.status-class').find('div[data-status="active"]').attr('id');
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
        return;
    }

    if (tab == 2) {
        /* Tab "Dự thầu" */
        $("#customer_id").select2("val", $first_customer_id); // Reset khách hàng

        // Reset mức độ khả thi
        $("#muc_do_kha_thi").data("ionRangeSlider").update({
            from: $("#muc_do_kha_thi").attr("data-first")
        });

        $("#form-tab-du-thau-edit")[0].reset(); // Hoàn tác dữ liệu form
        return;
    }

    if (tab == 3) {
        /* Tab "Chi phí & thời gian" */
        $("#form-tab-chi-phi-thoi-gian-edit")[0].reset(); // Hoàn tác dữ liệu form
        return;
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