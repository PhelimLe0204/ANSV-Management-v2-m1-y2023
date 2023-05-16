var $week = null;
var $current_year = null;

$(document).ready(function () {
    var this_week = $("#weekList").attr("data-first");
    var this_year = $("#yearList").attr("data-first");
    selectWeekAndYear(this_week, this_year);
});

$(".listWeekYear").on("change", function () {
    let data_type = $(this).attr("data-type");
    let data_week = $("#weekList").val();
    let data_year = $("#yearList").val();
    let data_size = $(this).attr("data-size");
    let data_page = $(this).attr("data-page");
    let data_target = $(this).attr("data-target");
    let data_paging_content = $(this).attr("data-paging");

    if (data_size != undefined && data_page != undefined) {
        if (data_type == 3) {
            getDataListReport3("WeekAndYear", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else if (data_type == 2) {
            getDataListReport2("WeekAndYear", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else if (data_type == 1) {
            getDataListReport1("WeekAndYear", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else {
            alertify.error('Có lỗi! Vui lòng thử lại sau.').delay(1.5);
        }
    }
});

function selectWeekAndYear(this_week, this_year) {
    var currentYear = new Date().getFullYear();
    $current_year = currentYear;
    theLastDayOfYear = new Date(currentYear, 11, 31);
    var year = new Date(theLastDayOfYear.getFullYear(), 0, 1);
    var days = Math.floor((theLastDayOfYear - year) / (24 * 60 * 60 * 1000));
    var week = Math.ceil((theLastDayOfYear.getDay() + 1 + days) / 7);
    console.log("The current year (" + currentYear + ") has " + (week + 1) + " week.");
    $week = week + 1;

    var htmlSelectWeek = null;
    for (let i = 1; i <= $week; i++) {
        if (i == this_week) {
            htmlSelectWeek += '<option value="' + i + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
        } else {
            htmlSelectWeek += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#weekList").html(htmlSelectWeek);

    var htmlSelectYear = null;
    for (let i = 2021; i <= $current_year; i++) {
        if (i == this_year) {
            htmlSelectYear += '<option value="' + i + '" class="text-white bg-secondary font-weight-bold" selected>' + i + '</option>';
        } else {
            htmlSelectYear += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#yearList").html(htmlSelectYear);
}

function getDataListReport3(action_type, data_week, data_year, data_size, data_page, data_target, data_paging_content) {
    if (data_size != undefined && data_page != undefined) {
        let url = "/api/danh-sach/trien-khai?week=" + data_week + "&year=" + data_year + "&size=" + data_size + "&page=" + data_page;

        $.ajax({
            url: url,
            success: function (result) {
                let htmlBody = '';
                let htmlPaging = '';
                if (result.data != null && result.data.data != null) {
                    let dataArray = result.data.data;
                    let dataPaging = result.data.paging;
                    let classSub = "'d-flex justify-content-center'";
                    for (let i = 0; i < dataArray.length; i++) {
                        let htmlBodySub = '<tr>'
                            + '<td class="text-center" style="width: 50px;">' + (i + 1 + (result.data.paging.pageNumber - 1) * result.data.paging.pageSize) + '</td>'
                            + '<td class="d-inline-block text-truncate" style="max-width: 220px;">'
                            + '<a href="/chi-tiet?id=' + dataArray[i].id + '" data-toggle="tooltip" title="' + dataArray[i].jobName + '">' + dataArray[i].jobName + '</a>'
                            + '<p class="text-muted m-b-0">Tuần ' + dataArray[i].week + ' năm ' + dataArray[i].year + '</p>'
                            + '</td>'
                            + '<td class="text-center">' + (dataArray[i].tongGiaTriThucTe == null ? '' : dataArray[i].tongGiaTriThucTe) + '</td>'
                            + '<td class="text-center"><span class="d-inline-block text-truncate" style="max-width: 150px;" data-toggle="tooltip" title="' + dataArray[i].customerName + '">' + dataArray[i].customerName + '</span></td>'
                            + '<td class="text-center">' + (dataArray[i].picName == null ? '' : dataArray[i].picName) + '</td>'
                            + '<td class="text-center">'
                            + '<button type="button" class="btn btn-' + dataArray[i].statusColor + ' rounded-pill pt-0 btn-status" data-toggle="popover" data-html="true" data-placement="right" title="" data-content="' + (dataArray[i].tinhTrangDuAn != null ? dataArray[i].tinhTrangDuAn : '...') + '" data-original-title="<span class=' + classSub + '>Tình trạng dự án</span>">' + dataArray[i].statusDisplay + '</button>'
                            + '</td>'
                            + '<td class="text-center" id="' + dataArray[i].id + '" style="width: 100px;">'
                            + '<a href="javascript:void(0)" class="btn btn-hover-shine btn-outline-danger border-0 btn-sm delete" title="Delete" data-id="' + dataArray[i].id + '" data-name="' + dataArray[i].jobName + '">'
                            + '<span class="btn-icon-wrapper opacity-8"><i class="fa fa-trash fa-w-20"></i></span>'
                            + '</a>'
                            + '</td>'
                            + '</tr>';
                        htmlBody += htmlBodySub;
                    }

                    if (dataPaging.pageNumber - 1 <= 0) {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber - 1) + '" data-type="3" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    }

                    for (let i = 0; i < dataPaging.listPageNumbers.length; i++) {
                        htmlPaging += '<li class="page-item' + (dataPaging.listPageNumbers[i] == dataPaging.pageNumber ? ' active' : '') + '">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + dataPaging.listPageNumbers[i] + '" data-type="3" data-target="bodyListReportWeek" data-paging="pagingListReportWeek">' + dataPaging.listPageNumbers[i] + '</a>'
                            + '</li>';
                    }

                    if (dataPaging.pageNumber + 1 <= dataPaging.totalPage) {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber + 1) + '" data-type="3" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    }
                    $("#" + data_paging_content).html(htmlPaging);
                    if (action_type.includes("WeekAndYear")) {
                        alertify.success('Triển khai: tuần ' + data_week + ' năm ' + data_year).delay(1.5);
                    }
                } else {
                    htmlBody = '<tr><td colspan="7" class="text-center">Dữ liệu trống!</td></tr>';
                    $("#" + data_paging_content).html("");
                    if (result.message.includes("WeekAndYear")) {
                        alertify.success('Dữ liệu trống!').delay(1.5);
                    }
                }
                $("#" + data_target).html(htmlBody);
                $("[data-toggle=popover]").popover();
                $("[data-toggle=tooltip]").tooltip();
                selectWeekAndYear(data_week, data_year);
            }
        });
    }
}

function getDataListReport2(action_type, data_week, data_year, data_size, data_page, data_target, data_paging_content) {
    if (data_size != undefined && data_page != undefined) {
        let url = "/api/danh-sach/kd-chuyen-doi-so?week=" + data_week + "&year=" + data_year + "&size=" + data_size + "&page=" + data_page;

        $.ajax({
            url: url,
            success: function (result) {
                let htmlBody = '';
                let htmlPaging = '';
                if (result.data != null && result.data.data != null) {
                    let dataArray = result.data.data;
                    let dataPaging = result.data.paging;
                    let classSub = "'d-flex justify-content-center'";
                    for (let i = 0; i < dataArray.length; i++) {
                        let htmlBodySub = '<tr>'
                            + '<td class="text-center" style="width: 50px;">' + (i + 1 + (result.data.paging.pageNumber - 1) * result.data.paging.pageSize) + '</td>'
                            + '<td class="d-inline-block text-truncate" style="max-width: 220px;">'
                            + '<a href="/chi-tiet?id=' + dataArray[i].id + '" data-toggle="tooltip" title="' + dataArray[i].jobName + '">' + dataArray[i].jobName + '</a>'
                            + '<p class="text-muted m-b-0">Tuần ' + dataArray[i].week + ' năm ' + dataArray[i].year + '</p>'
                            + '</td>'
                            + '<td class="text-center">' + (dataArray[i].mucDoKhaThi == null ? '' : dataArray[i].mucDoKhaThi + '%') + '</td>'
                            + '<td class="text-center">' + (dataArray[i].tongMucDauTuDuKien == null ? '' : dataArray[i].tongMucDauTuDuKien) + '</td>'
                            + '<td class="text-center"><span class="d-inline-block text-truncate" style="max-width: 150px;" data-toggle="tooltip" title="' + dataArray[i].customerName + '">' + dataArray[i].customerName + '</span></td>'
                            + '<td class="text-center">' + dataArray[i].picName + '</td>'
                            + '<td class="text-center">'
                            + '<button type="button" class="btn btn-' + dataArray[i].statusColor + ' rounded-pill pt-0 btn-status" data-toggle="popover" data-html="true" data-placement="right" title="" data-content="' + (dataArray[i].tinhTrangDuAn != null ? dataArray[i].tinhTrangDuAn : '...') + '" data-original-title="<span class=' + classSub + '>Tình trạng dự án</span>">' + dataArray[i].statusDisplay + '</button>'
                            + '</td>'
                            + '<td class="text-center" id="' + dataArray[i].id + '" style="width: 100px;">'
                            + '<a href="javascript:void(0)" class="btn btn-hover-shine btn-outline-danger border-0 btn-sm delete" title="Delete" data-id="' + dataArray[i].id + '" data-name="' + dataArray[i].jobName + '">'
                            + '<span class="btn-icon-wrapper opacity-8"><i class="fa fa-trash fa-w-20"></i></span>'
                            + '</a>'
                            + '</td>'
                            + '</tr>';
                        htmlBody += htmlBodySub;
                    }

                    if (dataPaging.pageNumber - 1 <= 0) {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber - 1) + '"data-type="2" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    }

                    for (let i = 0; i < dataPaging.listPageNumbers.length; i++) {
                        htmlPaging += '<li class="page-item' + (dataPaging.listPageNumbers[i] == dataPaging.pageNumber ? ' active' : '') + '">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + dataPaging.listPageNumbers[i] + '"data-type="2" data-target="bodyListReportWeek" data-paging="pagingListReportWeek">' + dataPaging.listPageNumbers[i] + '</a>'
                            + '</li>';
                    }

                    if (dataPaging.pageNumber + 1 <= dataPaging.totalPage) {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber + 1) + '"data-type="2" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    }
                    $("#" + data_paging_content).html(htmlPaging);
                    if (action_type.includes("WeekAndYear")) {
                        alertify.success('Triển khai: tuần ' + data_week + ' năm ' + data_year).delay(1.5);
                    }
                } else {
                    htmlBody = '<tr><td colspan="8" class="text-center">Dữ liệu trống!</td></tr>';
                    $("#" + data_paging_content).html("");
                    if (result.message.includes("WeekAndYear")) {
                        alertify.success('Dữ liệu trống!').delay(1.5);
                    }
                }
                $("#" + data_target).html(htmlBody);
                $("[data-toggle=popover]").popover();
                $("[data-toggle=tooltip]").tooltip();
                selectWeekAndYear(data_week, data_year);
            }
        });
    }
}

function getDataListReport1(action_type, data_week, data_year, data_size, data_page, data_target, data_paging_content) {
    if (data_size != undefined && data_page != undefined) {
        let url = "/api/danh-sach/kd-vien-thong?week=" + data_week + "&year=" + data_year + "&size=" + data_size + "&page=" + data_page;

        $.ajax({
            url: url,
            success: function (result) {
                let htmlBody = '';
                let htmlPaging = '';
                if (result.data != null && result.data.data != null) {
                    let dataArray = result.data.data;
                    let dataPaging = result.data.paging;
                    let classSub = "'d-flex justify-content-center'";
                    for (let i = 0; i < dataArray.length; i++) {
                        let htmlBodySub = '<tr>'
                            + '<td class="text-center" style="width: 50px;">' + (i + 1 + (result.data.paging.pageNumber - 1) * result.data.paging.pageSize) + '</td>'
                            + '<td class="d-inline-block text-truncate" style="max-width: 220px;">'
                            + '<a href="/chi-tiet?id=' + dataArray[i].id + '" data-toggle="tooltip" title="' + dataArray[i].jobName + '">' + dataArray[i].jobName + '</a>'
                            + '<p class="text-muted m-b-0">Tuần ' + dataArray[i].week + ' năm ' + dataArray[i].year + '</p>'
                            + '</td>'
                            + '<td class="text-center">' + (dataArray[i].mucDoKhaThi == null ? '' : dataArray[i].mucDoKhaThi + '%') + '</td>'
                            + '<td class="text-center">' + (dataArray[i].tongMucDauTuDuKien == null ? '' : dataArray[i].tongMucDauTuDuKien) + '</td>'
                            + '<td class="text-center"><span class="d-inline-block text-truncate" style="max-width: 150px;" data-toggle="tooltip" title="' + dataArray[i].customerName + '">' + dataArray[i].customerName + '</span></td>'
                            + '<td class="text-center">' + dataArray[i].picName + '</td>'
                            + '<td class="text-center">'
                            + '<button type="button" class="btn btn-' + dataArray[i].statusColor + ' rounded-pill pt-0 btn-status" data-toggle="popover" data-html="true" data-placement="right" title="" data-content="' + (dataArray[i].tinhTrangDuAn != null ? dataArray[i].tinhTrangDuAn : '...') + '" data-original-title="<span class=' + classSub + '>Tình trạng dự án</span>">' + dataArray[i].statusDisplay + '</button>'
                            + '</td>'
                            + '<td class="text-center" id="' + dataArray[i].id + '" style="width: 100px;">'
                            + '<a href="javascript:void(0)" class="btn btn-hover-shine btn-outline-danger border-0 btn-sm delete" title="Delete" data-id="' + dataArray[i].id + '" data-name="' + dataArray[i].jobName + '">'
                            + '<span class="btn-icon-wrapper opacity-8"><i class="fa fa-trash fa-w-20"></i></span>'
                            + '</a>'
                            + '</td>'
                            + '</tr>';
                        htmlBody += htmlBodySub;
                    }

                    if (dataPaging.pageNumber - 1 <= 0) {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber - 1) + '"data-type="1" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    }

                    for (let i = 0; i < dataPaging.listPageNumbers.length; i++) {
                        htmlPaging += '<li class="page-item' + (dataPaging.listPageNumbers[i] == dataPaging.pageNumber ? ' active' : '') + '">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + dataPaging.listPageNumbers[i] + '"data-type="1" data-target="bodyListReportWeek" data-paging="pagingListReportWeek">' + dataPaging.listPageNumbers[i] + '</a>'
                            + '</li>';
                    }

                    if (dataPaging.pageNumber + 1 <= dataPaging.totalPage) {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber + 1) + '"data-type="1" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    } else {
                        htmlPaging += '<li class="page-item disabled">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" aria-label="Next">'
                            + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
                            + '</a>'
                            + '</li>';
                    }
                    $("#" + data_paging_content).html(htmlPaging);
                    if (action_type.includes("WeekAndYear")) {
                        alertify.success('Triển khai: tuần ' + data_week + ' năm ' + data_year).delay(1.5);
                    }
                } else {
                    htmlBody = '<tr><td colspan="8" class="text-center">Dữ liệu trống!</td></tr>';
                    $("#" + data_paging_content).html("");
                    if (result.message.includes("WeekAndYear")) {
                        alertify.success('Dữ liệu trống!').delay(1.5);
                    }
                }
                $("#" + data_target).html(htmlBody);
                $("[data-toggle=popover]").popover();
                $("[data-toggle=tooltip]").tooltip();
                selectWeekAndYear(data_week, data_year);
            }
        });
    }
}

$(".pagination").on("click", ".page-link", function () {
    let data_type = $(this).attr("data-type");
    let data_week = $("#weekList").val();
    let data_year = $("#yearList").val();
    let data_size = $(this).attr("data-size");
    let data_page = $(this).attr("data-page");
    let data_target = $(this).attr("data-target");
    let data_paging_content = $(this).attr("data-paging");

    if (data_size != undefined && data_page != undefined) {
        if (data_type == 3) {
            getDataListReport3("paging", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else if (data_type == 2) {
            getDataListReport2("paging", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else if (data_type == 1) {
            getDataListReport1("paging", data_week, data_year, data_size, data_page, data_target, data_paging_content);
        } else {
            alertify.error('Có lỗi! Vui lòng thử lại sau.').delay(1.5);
        }
    }
    // let data_size = $(this).attr("data-size");
    // let data_page = $(this).attr("data-page");
    // let data_target = $(this).attr("data-target");
    // let data_paging_content = $(this).attr("data-paging");
    // if (data_size != undefined && data_page != undefined) {
    //     let url = "/api/danh-sach/trien-khai?card=" + $(this).attr("data-card") + "&week=" + $("#weekList").val() + "&year=" + $("#yearList").val() + "&size=" + data_size + "&page=" + data_page;
    //     $.ajax({
    //         url: url,
    //         success: function (result) {
    //             let htmlBody = '';
    //             let htmlPaging = '';
    //             if (result.data.data != null) {
    //                 let dataArray = result.data.data;
    //                 let dataPaging = result.data.paging;
    //                 let classSub = "'d-flex justify-content-center'";
    //                 for (let i = 0; i < dataArray.length; i++) {
    //                     let htmlBodySub = '<tr>'
    //                         + '<td class="text-center" style="width: 50px;">' + (i + 1 + (result.data.paging.pageNumber - 1) * result.data.paging.pageSize) + '</td>'
    //                         + '<td class="d-inline-block text-truncate" style="max-width: 220px;">'
    //                         + '<a href="/chi-tiet?id=' + dataArray[i].id + '" data-toggle="tooltip" title="' + dataArray[i].jobName + '">' + dataArray[i].jobName + '</a>'
    //                         + '<p class="text-muted m-b-0">Tuần ' + dataArray[i].week + ' năm ' + dataArray[i].year + '</p>'
    //                         + '</td>'
    //                         + '<td class="text-center">' + dataArray[i].tongGiaTriThucTe + '</td>'
    //                         + '<td class="text-center">' + dataArray[i].customerName.substring(0, 15) + '...</td>'
    //                         + '<td class="text-center">' + dataArray[i].picName + '</td>'
    //                         + '<td class="text-center">'
    //                         + '<button type="button" class="btn btn-' + dataArray[i].statusColor + ' rounded-pill pt-0 btn-status" data-toggle="popover" data-html="true" data-placement="right" title="" data-content="' + (dataArray[i].tinhTrangDuAn != null ? dataArray[i].tinhTrangDuAn : '...') + '" data-original-title="<span class=' + classSub + '>Tình trạng dự án</span>">' + dataArray[i].statusDisplay + '</button>'
    //                         + '</td>'
    //                         + '<td class="text-center" id="' + dataArray[i].id + '" style="width: 100px;">'
    //                         + '<a href="javascript:void(0)" class="btn btn-hover-shine btn-outline-danger border-0 btn-sm delete" title="Delete" data-id="' + dataArray[i].id + '" data-name="' + dataArray[i].jobName + '">'
    //                         + '<span class="btn-icon-wrapper opacity-8"><i class="fa fa-trash fa-w-20"></i></span>'
    //                         + '</a>'
    //                         + '</td>'
    //                         + '</tr>';
    //                     htmlBody += htmlBodySub;
    //                 }

    //                 if (dataPaging.pageNumber - 1 <= 0) {
    //                     htmlPaging += '<li class="page-item disabled">'
    //                         + '<a href="javascript:void(0)" class="page-link" aria-label="Previous">'
    //                         + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
    //                         + '</a>'
    //                         + '</li>';
    //                 } else {
    //                     htmlPaging += '<li class="page-item">'
    //                         + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber - 1) + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Previous">'
    //                         + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
    //                         + '</a>'
    //                         + '</li>';
    //                 }

    //                 for (let i = 0; i < dataPaging.listPageNumbers.length; i++) {
    //                     htmlPaging += '<li class="page-item' + (dataPaging.listPageNumbers[i] == dataPaging.pageNumber ? ' active' : '') + '">'
    //                         + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + dataPaging.listPageNumbers[i] + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek">' + dataPaging.listPageNumbers[i] + '</a>'
    //                         + '</li>';
    //                 }

    //                 if (dataPaging.pageNumber + 1 <= dataPaging.totalPage) {
    //                     htmlPaging += '<li class="page-item">'
    //                         + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber + 1) + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Next">'
    //                         + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
    //                         + '</a>'
    //                         + '</li>';
    //                 } else {
    //                     htmlPaging += '<li class="page-item disabled">'
    //                         + '<a href="javascript:void(0)" class="page-link" data-card="1" aria-label="Next">'
    //                         + '<span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span>'
    //                         + '</a>'
    //                         + '</li>';
    //                 }
    //                 $("#" + data_paging_content).html(htmlPaging);
    //             } else {
    //                 htmlBody = '<tr><td colspan="7" class="text-center">Dữ liệu trống!</td></tr>';
    //             }
    //             $("#" + data_target).html(htmlBody);
    //             $("[data-toggle=popover]").popover();

    //             // var find_data = thisContent.parent().html();
    //             // console.log(find_data);
    //             // find_data.removeClass("active");
    //             // thisContent.closest("li").addClass("active"); // add class active
    //             // console.log(result);
    //         }
    //     });
    // }
});

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

    $("#hop_dong_giao_hang, #hop_dong_dac, #hop_dong_pac, #hop_dong_fac, #ke_hoach_tam_ung").datepicker({
        dateFormat: 'dd / mm / yy'
    });

    $("#muc_tieu_giao_hang, #muc_tieu_dac, #muc_tieu_pac, #muc_tieu_fac").datepicker({
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

    $("#thuc_te_giao_hang, #thuc_te_dac, #thuc_te_pac, #thuc_te_fac").datepicker({
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

$("#btn-open-import-report-modal").click(function () {
    $('#fileImport').change(function () {
        $("#formImportSubmit").html(
            '<button type="submit" class="btn btn-primary pt-1 pb-1 float-right" '
            + 'id="btn-submit-import-report">Import</button>'
        );
        $("#importError").html("");
    });

    $("#formImportReport").submit(function (event) {
        event.preventDefault();

        $("#formImportSubmit").html(
            '<button class="btn btn-primary pt-1 pb-1 float-right" type="button" disabled>'
            + '<span class="spinner-border spinner-border-sm" role="status"></span>'
            + '<span class="pl-1"> Đang xử lý...</span></button>'
        );
        // $('#fileImport').prop('disabled', true);

        var form = document.getElementById('formImportReport');
        var url = form.action;
        var data = new FormData(form);

        $.ajax({
            url: url,
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result, textStatus, jqXHR) {
                if (result.status == "failed") {
                    $("#formImportSubmit").html(
                        '<button type="button" class="btn btn-primary pt-1 pb-1 float-right" '
                        + 'id="btn-reset-import-report">Thực hiện lại</button>'
                    );

                    $("#btn-reset-import-report").click(function () {
                        $("#fileImport").val("");
                        $("#formImportSubmit").html(
                            '<button type="submit" class="btn btn-primary pt-1 pb-1 float-right" '
                            + 'id="btn-submit-import-report" disabled>Import</button>'
                        );
                        $("#importError").html("");
                    });

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
                        + '<tbody style="max-height: 380px;">' + dataError + '</tbody>'
                        + '</table>'
                        + '</div>'
                        + '</div>'
                    );
                    alertify.error(result.message).delay(2.5);
                }

                if (result.status == "success") {
                    alertify.success(result.message).delay(2.5);
                    $("#fileImport").val("");

                    $("#formImportSubmit").html(
                        '<button type="submit" class="btn btn-primary pt-1 pb-1 float-right" '
                        + 'id="btn-submit-import-report" disabled>Import</button>'
                    );
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alertify.error("File không xác định! Vui lòng thử lại.").delay(3);
                // alert('ERRORS: ' + textStatus);
            }
        });
    });
});

$("#btn-open-modal-export-report").click(function () {
    var currentYear = new Date().getFullYear();
    var theLastDayOfYear = new Date(currentYear, 11, 31);
    var year = new Date(theLastDayOfYear.getFullYear(), 0, 1);
    var days = Math.floor((theLastDayOfYear - year) / (24 * 60 * 60 * 1000));
    var week = Math.ceil((theLastDayOfYear.getDay() + 1 + days) / 7);
    console.log("The current year (" + currentYear + ") has " + (week + 1) + " week.");
    var $week = week + 1;
    var $first_week = $("#weekExport").attr("data-first");
    var $first_year = $("#yearExport").attr("data-first");

    var htmlSelectWeek = '<option value="">Chọn tuần báo cáo...</option>';
    for (let i = 1; i <= $week; i++) {
        if (i == $first_week) {
            htmlSelectWeek += '<option value="' + i
                + '" class="text-white bg-secondary font-weight-bold" selected>Tuần ' + i + '</option>';
        } else {
            htmlSelectWeek += '<option value="' + i + '">Tuần ' + i + '</option>';
        }
    }
    $("#weekExport").html(htmlSelectWeek);

    var currentYear = new Date().getFullYear();
    var htmlSelectYear = '<option value="">Chọn năm báo cáo...</option>';
    for (let i = 2021; i <= currentYear; i++) {
        if (i == $first_year) {
            htmlSelectYear += '<option value="' + i
                + '" class="text-white bg-secondary font-weight-bold" selected>Năm ' + i + '</option>';
        } else {
            htmlSelectYear += '<option value="' + i + '">Năm ' + i + '</option>';
        }
    }
    $("#yearExport").html(htmlSelectYear);

    $("#btn-do-export-report").click(function () {
        $("#exportButtons").html(
            '<button class="btn btn-primary pt-1 pb-1 float-right" type="button" disabled>'
            + '<span class="spinner-border spinner-border-sm" role="status"></span>'
            + '<span class="pl-1"> Đang xử lý...</span></button>'
            + '<button type="button" class="btn btn-secondary pt-1 pb-1" data-dismiss="modal">Đóng</button>'
        );

        $("#formExportReport").submit();

        setTimeout(function () {
            $("#exportButtons").html(
                '<button type="button" class="btn btn-primary pt-1 pb-1 float-right" '
                + 'id="btn-do-export-report">Export</button>'
                + '<button type="button" class="btn btn-secondary pt-1 pb-1 btn-close-import-report" '
                + 'data-dismiss="modal">Đóng</button>'
            );
        }, 2000);
    });
});