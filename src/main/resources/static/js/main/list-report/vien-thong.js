$(document).ready(function() {
    
});
$(".pagination").on("click", ".page-link", function () {
    let data_size = $(this).attr("data-size");
    let data_page = $(this).attr("data-page");
    let data_target = $(this).attr("data-target");
    let data_paging_content = $(this).attr("data-paging");
    if (data_size != undefined && data_page != undefined) {
        let url = "/api/danh-sach/kd-vien-thong?card=" + $(this).attr("data-card") + "&size=" + data_size + "&page=" + data_page;
        $.ajax({
            url: url,
            success: function (result) {
                let htmlBody = '';
                let htmlPaging = '';
                if (result.data.data != null) {
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
                            + '<td class="text-center">' + dataArray[i].tongGiaTriThucTe + '</td>'
                            + '<td class="text-center">' + dataArray[i].customerName.substring(0, 15) + '...</td>'
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
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber - 1) + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Previous">'
                            + '<span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span>'
                            + '</a>'
                            + '</li>';
                    }

                    for (let i = 0; i < dataPaging.listPageNumbers.length; i++) {
                        htmlPaging += '<li class="page-item' + (dataPaging.listPageNumbers[i] == dataPaging.pageNumber ? ' active' : '') + '">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + dataPaging.listPageNumbers[i] + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek">' + dataPaging.listPageNumbers[i] + '</a>'
                            + '</li>';
                    }

                    if (dataPaging.pageNumber + 1 <= dataPaging.totalPage) {
                        htmlPaging += '<li class="page-item">'
                            + '<a href="javascript:void(0)" class="page-link" data-card="1" data-size="' + dataPaging.pageSize + '" data-page="' + (dataPaging.pageNumber + 1) + '" data-target="bodyListReportWeek" data-paging="pagingListReportWeek" aria-label="Next">'
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
                } else {
                    htmlBody = '<tr><td colspan="5" class="text-center">Dữ liệu trống!</td></tr>';
                }
                $("#" + data_target).html(htmlBody);
                $("[data-toggle=popover]").popover();

                // var find_data = thisContent.parent().html();
                // console.log(find_data);
                // find_data.removeClass("active");
                // thisContent.closest("li").addClass("active"); // add class active
                // console.log(result);
            }
        });
    }
});