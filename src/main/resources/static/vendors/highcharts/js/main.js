// Highcharts.chart('container-highchart', {
//     chart: {
//         type: 'column'
//     },
//     title: {
//         text: 'Kinh doanh Chuyển đổi số'
//     },
//     subtitle: {
//         text: 'Source: ' +
//             '<a href="https://www.ssb.no/en/statbank/table/08940/" ' +
//             'target="_blank">SSB</a>'
//     },
//     xAxis: {
//         categories: [
//             'Ưu tiên 1',
//             'Ưu tiên 2',
//             'Ưu tiên 3'
//         ],
//         crosshair: true
//     },
//     yAxis: {
//         title: {
//             useHTML: true,
//             text: 'Số lượng'
//         }
//     },
//     tooltip: {
//         headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//         pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//             '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
//         footerFormat: '</table>',
//         shared: true,
//         useHTML: true
//     },
//     plotOptions: {
//         column: {
//             pointPadding: 0.2,
//             borderWidth: 0
//         }
//     },
//     colors: ['#FF0000', '#ffaa31', '#8ac248'],
//     series: [{
//         name: 'Danger',
//         data: [13.93, 13.63, 13.73]

//     }, {
//         name: 'Warning',
//         data: [12.24, 12.24, 11.95]

//     }, {
//         name: 'Ongoing',
//         data: [10.00, 9.93, 9.97]

//     }]
// });

$(document).ready(function () {
    $(".link-none").on("click", function (event) {
        event.preventDefault();
        alertify.confirm('Thông báo', 'Chức năng đang trong quá trình phát triển! Mời bạn quay trở lại sau.'
            , function () { }, function () { });
    });
});

$.ajax({
    url: "/api/getDashboardChart/1",
    success: function (result) {
        // console.log("============================ Vien Thong ===========================");
        // console.log(result);

        Highcharts.chart('container-highchart-1', {
            chart: { type: 'column' },
            title: { text: 'Kinh doanh Viễn thông' },
            xAxis: {
                categories: ['Ưu tiên 1', 'Ưu tiên 2', 'Ưu tiên 3'],
                crosshair: true
            },
            yAxis: {
                title: { useHTML: true, text: 'Số lượng' }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: { pointPadding: 0.2, borderWidth: 0 }
            },
            colors: ['#FF0000', '#ffaa31', '#8ac248'],
            series: [{
                name: 'Danger',
                data: [result.data.dangerPriority1, result.data.dangerPriority2, result.data.dangerPriority3]
            }, {
                name: 'Warning',
                data: [result.data.warningPriority1, result.data.warningPriority2, result.data.warningPriority3]
            }, {
                name: 'Ongoing',
                data: [result.data.ongoingPriority1, result.data.ongoingPriority2, result.data.ongoingPriority3]
            }]
        });
    }
});

$.ajax({
    url: "/api/getDashboardChart/2",
    success: function (result) {
        // console.log("============================ Chuyen doi so ===========================");
        // console.log(result);

        Highcharts.chart('container-highchart-2', {
            chart: { type: 'column' },
            title: { text: 'Kinh doanh Chuyển đổi số' },
            xAxis: {
                categories: ['Ưu tiên 1', 'Ưu tiên 2', 'Ưu tiên 3'],
                crosshair: true
            },
            yAxis: {
                title: { useHTML: true, text: 'Số lượng' }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: { pointPadding: 0.2, borderWidth: 0 }
            },
            colors: ['#FF0000', '#ffaa31', '#8ac248'],
            series: [{
                name: 'Danger',
                data: [result.data.dangerPriority1, result.data.dangerPriority2, result.data.dangerPriority3]
            }, {
                name: 'Warning',
                data: [result.data.warningPriority1, result.data.warningPriority2, result.data.warningPriority3]
            }, {
                name: 'Ongoing',
                data: [result.data.ongoingPriority1, result.data.ongoingPriority2, result.data.ongoingPriority3]
            }]
        });
    }
});

$.ajax({
    url: "/api/getDashboardChart/3",
    success: function (result) {
        // console.log("============================ Trien khai ===========================");
        // console.log(result);

        Highcharts.chart('container-highchart-3', {
            chart: { type: 'column' },
            title: { text: 'Triển khai' },
            xAxis: {
                categories: [
                    'Ưu tiên 1',
                    'Ưu tiên 2',
                    'Ưu tiên 3'
                ],
                crosshair: true
            },
            yAxis: {
                title: { useHTML: true, text: 'Số lượng' }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: { pointPadding: 0.2, borderWidth: 0 }
            },
            colors: ['#FF0000', '#ffaa31', '#8ac248'],
            series: [{
                name: 'Danger',
                data: [result.data.dangerPriority1, result.data.dangerPriority2, result.data.dangerPriority3]
            }, {
                name: 'Warning',
                data: [result.data.warningPriority1, result.data.warningPriority2, result.data.warningPriority3]
            }, {
                name: 'Ongoing',
                data: [result.data.ongoingPriority1, result.data.ongoingPriority2, result.data.ongoingPriority3]
            }]
        });
    }
});