$(document).ready(function () {
    // $('.delete').click(function () {
        
    // });

    $(document).on("click", ".delete", function(){
        let name = $(this).attr("data-name");
        let id = $(this).attr("data-id");
        
        // console.log(name, id);
        alertify.confirm('Xoá dự án', 'Bạn có muốn xoá dự án ' + name, function () {
            $(this).parents("tr").remove();
        }, function () {
            alertify.error('Huỷ lệnh xoá')
        });
    });

});