$(document).ready(function () {
    // $('.delete').click(function () {
        
    // });

    $(document).on("click", ".delete", function(){
        let name = $(this).attr("data-name");
        let id = $(this).attr("data-id");
        // console.log(name, id);
        alertify.confirm('Xoá dự án', 'Bạn có muốn xoá dự án ' + name, function () {
            // $('#'+id).parent("tr").remove();
            // alertify.success('Đã xoá thành công')
            $.ajax({
                url: "/api/deleteProject/" + id + "/",
                success: function (result) {
                    if(result.status == "success") {
                        $('#'+id).parent("tr").remove();
                        alertify.success(result.message).delay(2);
                    }
                    if (result.status == "failed") {
                        alertify.error(result.message).delay(1.5);
                        return;
                    }
                }
            })
        }, function () {
            alertify.error('Huỷ lệnh xoá').delay(2);
        });
    });


});