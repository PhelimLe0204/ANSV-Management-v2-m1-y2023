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
});