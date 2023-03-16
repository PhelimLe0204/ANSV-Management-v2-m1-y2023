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

    tinymce.init({
        selector:'textarea.tinymce-content',
        promotion: false,
        ui_container: '#tinymce-group',
            height: 300,
            plugins: [
                'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                'insertdatetime', 'media', 'table', 'help', 'wordcount'
            ],
            toolbar: 'undo redo | blocks | ' +
                'bold italic backcolor | alignleft aligncenter ' +
                'alignright alignjustify | bullist numlist outdent indent | ' +
                'removeformat | help',
            content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }',
      });


});