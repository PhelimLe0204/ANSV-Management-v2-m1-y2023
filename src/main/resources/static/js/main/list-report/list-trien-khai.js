$("#btn-open-add-new-report-modal").click(function () {
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

    let htmlSelectWeek = null;
    let first_week = $("#week").attr("data-first");
    for (let i = 1; i <= 53; i++) {
        if (i == first_week) {
            htmlSelectWeek += '<option value="' + i
                + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
        } else {
            htmlSelectWeek += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#week").html(htmlSelectWeek);

    let htmlSelectYear = null;
    let first_year = $("#year").attr("data-first");
    for (let i = 2021; i <= first_year; i++) {
        if (i == first_year) {
            htmlSelectYear += '<option value="' + i
                + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
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
                if (result.data[i].currencyUnit == "VND") {
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

    $("#btn-submit-add-new-report").click(function () {
        let project_id = $("#project_id").val();
        let project_type_id = $("#project_type_id").val();
        let project_priority_id = $("#project_priority_id").val();
        let project_status_id = $("#project_status_id").val();
        let week = $("#week").val();
        let year = $("#year").val();
        // let ma_hop_dong = $("#ma_hop_dong").val();
        // let ma_ke_toan = $("#ma_ke_toan").val();
        let currency_unit_id = $("#currency_unit_id").val();

        if (!project_id | !project_type_id | !project_priority_id | !project_status_id | !week | !year | !currency_unit_id) {
            console.log("Thiếu dữ liệu");
            return;
        }

        $("#form-add-new-report").submit();
    });

    var data_customer_select_option;
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
        }
    });

    $("#add-new-dt-tab").click(function () {
        // $("#project_id").val();
        $.ajax({
            url: "/api/getCustomerIdByProjectId/" + $("#project_id").val(),
            success: function (result) {
                $("#customer_id").select2("val", result.data + "");
            }
        });
    });

    $("#muc_do_kha_thi").ionRangeSlider({
        min: 0,
        max: 100
    });

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
});

$("#btn-open-import-trien-khai-modal").click(function () {
    $("#formImportReport").submit(function (event) {
        // console.log("ABC");
        event.preventDefault();

        var form = document.getElementById('formImportReport');
        var data = new FormData(form);

        $.ajax({
            url: '/import/1',
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result, textStatus, jqXHR) {
                if (result.status == "failed") {
                    let dataError = '';
                    for (let i = 0; i < result.data.length; i++) {
                        dataError += '<tr>';
                        dataError += '<td>' + (i + 1) + '</td>';
                        dataError += '<td>' + result.data[i].position + '</td>';
                        dataError += '<td>' + result.data[i].error + '</td>';
                        dataError += '</tr>';
                    }
                    $("#importError").html(
                        '<div class="col-md-12"><hr><h5 class="text-primary">Danh sách lỗi import:</h5>'
                        + '</div>'
                        + '<div class="col-md-12 text-center">'
                        + '<div class="table-responsive">'
                        + '<table class="table table-striped table-hover" id="table-trien-khai">'
                        + '<thead><tr><th>STT</th><th>Đối tượng</th><th>Nguyên nhân</th></tr></thead>'
                        + '<tbody style="height: 380px;">' + dataError + '</tbody>'
                        + '</table>'
                        + '</div>'
                        + '</div>'
                    );
                    alertify.error("Thất bại! Vui lòng chỉnh sửa file và thử lại.").delay(2.5);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('ERRORS: ' + textStatus);
            }
        });
    });
});