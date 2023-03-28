$(document).ready(function () {

    var owl = $('.owl-carousel');
    owl.owlCarousel({
        items: 1,
        loop: true,
        nav: true,
        margin: 10,
        autoplay: true,
        autoplayTimeout: 8500,
        responsiveClass: true,
        autoplayHoverPause: true
    });


    $('#myModal').modal('show');

    let params = new URLSearchParams(location.search);
    week_from_url = params.get('week');
    year_from_url =params.get('year');
    $('#select_week').select2();
    $('#select_year').select2();

    var week_value = "";
    var year_value = "";

    for(let i = 2020; i <= 2030; i++) {
        if (i == year_from_url) {
            year_value += '<option value="' + i + '" selected>' + i + '</option>';
        } else {
            year_value += '<option value="' + i + '">' + i + '</option>';
        }
    }
    document.getElementById("select_year").innerHTML = year_value; // Nhúng HTML cho dữ liệu Select2 (year)

    var value_option_week = "";
    for (let i = 1; i <= 53; i++) {
        if (i < 10) {
            value_option_week = value_option_week + "0" + i;
        } else {
            value_option_week = i;
        }
        
        if (i == week_from_url) {
            week_value += '<option value="' + value_option_week + '" selected>' + i + '</option>';
        } else {
            week_value += '<option value="' + value_option_week + '">' + i + '</option>';
        }
        value_option_week = "";
    }
    document.getElementById("select_week").innerHTML = week_value; // Nhúng HTML cho dữ liệu Select2 (week)

    $('#select_week').on('select2:select', function (e) {
        week_link = $("#select_week").val();
        year_link = $("#select_year").val();
        // console.log(year_link);
        window.location.href = '/dashboard?week=' + week_link + '&year=' + year_link;
    });
});