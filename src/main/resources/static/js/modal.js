function readPreviewURL(input) {


    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {


            $('#previewImage')
                .attr('src', e.target.result);
//                .css('background-image', "url(" + e.target.result + ")");
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function readURL(input) {


    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {


            $('#previewImage')
                .css('background-image', "url(" + e.target.result + ")");
        };

        reader.readAsDataURL(input.files[0]);
    }
}

$(document).ready(function () {

    $("#loginModal").modal("show")
    $("#createAccountModal").modal("show")
    $("#uploadImgModal").modal("show")



//	$("#loginSubmit").click(function(event){
//        event.preventDefault();
//        
//        ajaxLogin();
//	})
//	
//	var url = window.location;
//    // DO POST
//    function ajaxLogin(){
//        $.ajax({
//            type : "POST",
//            url : url + "/login",
//            success: function(data){
//            	alert("success")
//            	ajaxRedirect()
//            },
//            error : function(e) {
//            	alert(e)
//            }
//        }); 
//    }
//	
//	function ajaxRedirect(){
//        $.ajax({
//            type : "GET",
//            url : url + "/home",
//            success: function(data){
//          
//            },
//            error : function(e) {
//            	alert(e);
//            }
//        }); 
//    }

})