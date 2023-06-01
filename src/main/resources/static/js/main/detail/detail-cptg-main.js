$('[data-target="#moreDetailModal"]').click(function () {
    var target_name = $(this).attr("data-name");
    var target_name_uppercase = target_name.substring(0, 3).toUpperCase()
    console.log(target_name + " - " + target_name.substring(0, 3) + " - " + target_name.substring(3, target_name.length));

    $('#moreDetailModalLabel').text("Xem thêm " + target_name.substring(0, 3).toUpperCase());

    // Ajax get data for project's selection
    $.ajax({
        url: "/api/report/getDetailMore?id=" + target_name.substring(3, target_name.length) + "&target=" + target_name.substring(0, 3),
        success: function (result) {
            console.log(result);
            if (result.data != null) {
                var data = result.data;
                var html = '<tr>'
                    + '<td class="bg-primary text-center text-white font-weight-bold p-0">' + target_name_uppercase + '</td>'
                    + '<td class="text-center">' + (data.soTien != null ? data.soTien : '...') + '</td>'
                    + '<td class="text-center">' + (data.hopDong != null ? data.hopDong : '...') + '</td>'
                    + '<td class="text-center">' + (data.mucTieu != null ? data.mucTieu : '...') + '</td>'
                    + '<td class="text-center">' + (data.thucTe != null ? data.thucTe : '...') + '</td>'
                    + '<td class="text-center">' + (data.chenhLech != null ? data.chenhLech : '...') + '</td>'
                    + '<td class="text-center">' + (data.note != null ? data.note : '...') + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<td class="bg-primary text-center text-white font-weight-bold p-0">' + target_name_uppercase + ' 2</td>'
                    + '<td class="text-center">' + (data.soTien2 != null ? data.soTien2 : '...') + '</td>'
                    + '<td class="text-center">' + (data.hopDong2 != null ? data.hopDong2 : '...') + '</td>'
                    + '<td class="text-center">' + (data.mucTieu2 != null ? data.mucTieu2 : '...') + '</td>'
                    + '<td class="text-center">' + (data.thucTe2 != null ? data.thucTe2 : '...') + '</td>'
                    + '<td class="text-center">' + (data.chenhLech2 != null ? data.chenhLech2 : '...') + '</td>'
                    + '<td class="text-center">' + (data.note2 != null ? data.note2 : '...') + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<td class="bg-primary text-center text-white font-weight-bold p-0">' + target_name_uppercase + ' 3</td>'
                    + '<td class="text-center">' + (data.soTien3 != null ? data.soTien3 : '...') + '</td>'
                    + '<td class="text-center">' + (data.hopDong3 != null ? data.hopDong3 : '...') + '</td>'
                    + '<td class="text-center">' + (data.mucTieu3 != null ? data.mucTieu3 : '...') + '</td>'
                    + '<td class="text-center">' + (data.thucTe3 != null ? data.thucTe3 : '...') + '</td>'
                    + '<td class="text-center">' + (data.chenhLech3 != null ? data.chenhLech3 : '...') + '</td>'
                    + '<td class="text-center">' + (data.note3 != null ? data.note3 : '...') + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<td class="bg-primary text-center text-white font-weight-bold p-0">' + target_name_uppercase + ' 4</td>'
                    + '<td class="text-center">' + (data.soTien4 != null ? data.soTien4 : '...') + '</td>'
                    + '<td class="text-center">' + (data.hopDong4 != null ? data.hopDong4 : '...') + '</td>'
                    + '<td class="text-center">' + (data.mucTieu4 != null ? data.mucTieu4 : '...') + '</td>'
                    + '<td class="text-center">' + (data.thucTe4 != null ? data.thucTe4 : '...') + '</td>'
                    + '<td class="text-center">' + (data.chenhLech4 != null ? data.chenhLech4 : '...') + '</td>'
                    + '<td class="text-center">' + (data.note4 != null ? data.note4 : '...') + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<td class="bg-primary text-center text-white font-weight-bold p-0">' + target_name_uppercase + ' 5</td>'
                    + '<td class="text-center">' + (data.soTien5 != null ? data.soTien5 : '...') + '</td>'
                    + '<td class="text-center">' + (data.hopDong5 != null ? data.hopDong5 : '...') + '</td>'
                    + '<td class="text-center">' + (data.mucTieu5 != null ? data.mucTieu5 : '...') + '</td>'
                    + '<td class="text-center">' + (data.thucTe5 != null ? data.thucTe5 : '...') + '</td>'
                    + '<td class="text-center">' + (data.chenhLech5 != null ? data.chenhLech5 : '...') + '</td>'
                    + '<td class="text-center">' + (data.note5 != null ? data.note5 : '...') + '</td>'
                    + '</tr>';
                    
                $('#moreDetailBody').html(html);
            }
        }
    });
});

var $arrayDAC = [1], $arrayPAC = [1], $arrayFAC = [1];
$(".addMoreDate").click(function () {
    console.log("ABCXYZ");
    var target = $(this).attr("data-target");
    var count = $('.deleteDate[data-from="' + target + '"]').length;

    if (count >= 4) {
        alertify.warning("Số lượng đã đạt tối đa!").delay(1.5);
        return;
    }

    var stt = count + 2;
    if (count != 0) {
        for (let i = 1; i < window["$array" + target].length; i++) {
            if (window["$array" + target][i] != (i + 1)) {
                stt = i + 1;
                break;
            }
        }
    }
    // console.log(count + " - " + stt);
    // console.log($(this).attr("data-target"));
    // console.log('#edit' + target + '' + stt);
    var lowAllLetter = target.toLowerCase();
    var upFisrtLetter = lowAllLetter.charAt(0).toUpperCase() + lowAllLetter.slice(1);
    var html = '<tr class="border-top border-white" id="edit' + target + stt + '">'
        + '<td class="bg-primary align-middle text-center text-white font-weight-bold p-0">'
        + '<button type="button" class="btn btn-primary deleteDate" data-from="' + target + '" data-target="' + target + stt + '" data-toggle="tooltip" data-placement="top" title="Xóa ' + target + ' ' + stt + '">'
        + target + ' ' + stt + ' <i class="fas fa-trash pl-2 text-warning"></i>'
        + '</button>'
        + '</td>'
        + '<td class="p-2">'
        + '<input type="text" name="soTien' + upFisrtLetter + stt + '" id="so_tien_' + lowAllLetter + '_' + stt + '" class="form-control text-center table-bg-form-input" placeholder="Nhập số tiền ' + target + ' ' + stt + '...">'
        + '</td>'
        + '<td class="p-2">'
        + '<input type="text" name="hopDong' + upFisrtLetter + stt + '" id="hop_dong_' + lowAllLetter + '_' + stt + '" class="form-control text-center input-datepicker float-left" placeholder="dd / mm / yyyy" data-allow-change-status="0" readonly="">'
        + '<button type="button" class="btn-delete-input btn btn-outline-primary float-right pt-2 pl-1 pr-1" data-target="hop_dong_' + lowAllLetter + '_' + stt + '">'
        + '<i class="feather icon-x"></i>'
        + '</button>'
        + '</td>'
        + '<td class="p-2">'
        + '<input type="text" name="mucTieu' + upFisrtLetter + stt + '" id="muc_tieu_' + lowAllLetter + '_' + stt + '" class="form-control text-center input-datepicker float-left" placeholder="dd / mm / yyyy" data-related-id="thuc_te_' + lowAllLetter + '_' + stt + '" data-calculate-result="chenh_lech_' + lowAllLetter + '_' + stt + '" data-allow-change-status="0" readonly="">'
        + '<button type="button" class="btn-delete-input btn btn-outline-primary float-right pt-2 pl-1 pr-1" data-target="muc_tieu_' + lowAllLetter + '_' + stt + '">'
        + '<i class="feather icon-x"></i>'
        + '</button>'
        + '</td>'
        + '<td class="p-2">'
        + '<input type="text" name="thucTe' + upFisrtLetter + stt + '" id="thuc_te_' + lowAllLetter + '_' + stt + '" class="form-control text-center input-datepicker float-left" placeholder="dd / mm / yyyy" data-related-id="muc_tieu_' + lowAllLetter + '_' + stt + '" data-calculate-result="chenh_lech_' + lowAllLetter + '_' + stt + '" data-allow-change-status="1" readonly="">'
        + '<button type="button" class="btn-delete-input btn btn-outline-primary float-right pt-2 pl-1 pr-1" data-target="thuc_te_' + lowAllLetter + '_' + stt + '">'
        + '<i class="feather icon-x"></i>'
        + '</button>'
        + '</td>'
        + '<td class="align-middle text-center">'
        + '<i class="fa-solid fa-square" data-status-for="thuc_te_' + lowAllLetter + '_' + stt + '" style="font-size: 20px;"></i>'
        + '<span class="pl-1" id="chenh_lech_' + lowAllLetter + '_' + stt + '"></span>'
        + '</td>'
        + '<td class="p-2">'
        + '<input type="text" name="note' + upFisrtLetter + stt + '" id="note_' + lowAllLetter + '_' + stt + '" class="form-control text-center table-bg-form-input" placeholder="Nhập ghi chú ' + target + ' ' + stt + '...">'
        + '</td>'
        + '</tr>';

    if ((stt - 2) == 0) {
        $('#edit' + target).after(html);
    } else {
        $('#edit' + target + (stt - 1)).after(html);
    }

    if (count == 0) {
        $(this).html(target + ' 1 <i class="fas fa-plus pl-2 text-warning"></i>');
    }

    if (!window["$array" + target].includes(stt)) {
        // window["$array" + target].push(stt); // Add stt into $arrayDAC / $arrayPAC / $arrayFAC
        window["$array" + target].splice((stt - 1), 0, stt);
    }
    setDatePicker(lowAllLetter, stt);
    $('[data-toggle="tooltip"]').tooltip();
    alertify.success("Thêm trường " + target + " " + (stt - 1)).delay(1.5);
});

function setDatePicker(target, stt) {
    $("#hop_dong_" + target + "_" + stt).datepicker({
        dateFormat: 'dd / mm / yy'
    });

    $("#muc_tieu_" + target + "_" + stt).datepicker({
        dateFormat: 'dd / mm / yy',
        onSelect: function (dateMucTieu) {
            var calculate_result_id = $(this).attr("data-calculate-result");
            var dateThucTe = $("#" + $(this).attr("data-related-id")).val();

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

    $("#thuc_te_" + target + "_" + stt).datepicker({
        dateFormat: 'dd / mm / yy',
        onSelect: function (dateThucTe) {
            var this_id = $(this).attr("id");
            var calculate_result_id = $(this).attr("data-calculate-result");
            var dateMucTieu = $("#" + $(this).attr("data-related-id")).val();

            if (dateMucTieu != "") {
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
                // console.log("Có class fa-square");
                $("[data-status-for=" + this_id + "]").removeAttr('class');
                $("[data-status-for=" + this_id + "]").attr('class', 'fa-sharp fa-solid fa-square-check text-primary');
            }
        }
    });

    $(".btn-delete-input").click(function () {
        var target_id = $(this).attr("data-target");
        $("#" + target_id).val("");

        // Trường hợp xóa ngày mục tiêu => Không còn ghi chú
        if (target_id == ("muc_tieu_dac_" + stt) || target_id == ("muc_tieu_pac_" + stt) || target_id == ("muc_tieu_fac_" + stt)) {
            $("#chenh_lech_" + target_id.slice(9)).html((""));
        }

        if (target_id == ("thuc_te_dac_" + stt) || target_id == ("thuc_te_pac_" + stt) || target_id == ("thuc_te_fac_" + stt)) {
            // Tính toán lại số ngày chênh lệch giữa ngày mục tiêu và hiện tại
            var dateMucTieuValue = $("#muc_tieu_" + target_id.slice(8)).val();

            if (dateMucTieuValue != "") {
                var dateMucTieu = new Date(dateMucTieuValue.slice(5, 7) + "/" + dateMucTieuValue.slice(0, 2) + "/" + dateMucTieuValue.slice(10, 15));
                var dateNow = new Date();
                dateNowFormat = new Date((dateNow.getUTCMonth() + 1) + "/" + (dateNow.getUTCDate()) + "/" + (dateNow.getUTCFullYear()));
                var diffTime = dateNowFormat - dateMucTieu; // Calculate the time difference of two dates
                var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); // To calculate the no. of days between two dates

                if (diffDays == 0) {
                    $("#chenh_lech_" + target_id.slice(8)).html(("Deadline"));
                } else if (diffDays < 0) {
                    $("#chenh_lech_" + target_id.slice(8)).html(("Còn " + Math.abs(diffDays) + " ngày"));
                } else {
                    $("#chenh_lech_" + target_id.slice(8)).html(("Quá " + diffDays + " ngày"));
                }
            } else {
                $("#chenh_lech_" + target_id.slice(8)).html((""));
            }
        }

        if ($("#" + target_id).attr("data-allow-change-status") == "1") {
            if ($("[data-status-for=" + target_id + "]").hasClass("fa-square-check")) {
                $("[data-status-for=" + target_id + "]").removeAttr('class');
                $("[data-status-for=" + target_id + "]").attr('class', 'fa-solid fa-square text-secondary');
            }
        }
    });
}

$("#bodyEditDetailCptg").on("click", "tr .deleteDate", function () {
    var target = $(this).attr("data-target");
    console.log(target.substring(0, 3));
    var count = $('.deleteDate[data-from="' + target.substring(0, 3) + '"]').length;
    console.log(count + " - " + target);
    if (count != 0) {
        $("#edit" + target).remove();
        if (count == 1) {
            $('.addMoreDate[data-target="' + target.substring(0, 3) + '"]').html(target.substring(0, 3) + ' <i class="fas fa-plus pl-2 text-warning"></i>');
        }

        const index = window["$array" + target.substring(0, 3)].indexOf(parseInt(target.substring(3)));
        if (index > -1) { // only splice array when item is found
            window["$array" + target.substring(0, 3)].splice(index, 1); // 2nd parameter means remove one item only
            console.log(window["$array" + target.substring(0, 3)]);
        }
        alertify.success("Đã xóa trường DAC " + target.substring(3)).delay(1.5);
    } else {
        alertify.error("Không thể xóa thêm " + target.substring(0, 3)).delay(1.5);
    }
});