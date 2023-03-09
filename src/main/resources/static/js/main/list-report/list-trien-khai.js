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
        console.log("Form input project's type.");
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
});