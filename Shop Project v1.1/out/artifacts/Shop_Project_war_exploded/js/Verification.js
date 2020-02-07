// http://localhost:8099/verification.jsp?code=1
$(document).ready(function () {
    var code = document.location.search.slice(6);
    var str ={code:code};
    $.ajax({
        url: "Verification",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
               // alert(data);
                if(data != 0) {
                    $("#verification_message").addClass("reg_message_good").html("Почта подтверждена!");
                }
                else {
                    $("#verification_message").addClass("reg_message_good").html("Код не действителен");
                }
                
        }
    });
});