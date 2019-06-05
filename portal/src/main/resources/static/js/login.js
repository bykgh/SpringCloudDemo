$(document).ready(function(){
    $("#submitButton").click(function(){

        var username = $("#username").text;
        var password = $("#password").text;

        $.post("/try/ajax/demo_test_post.php",
        {
            grant_type:"password",
            username:username,
            password:password,
            url:"http://localhost:8164/account/oauth/token"
        },
        function(data,status){
            alert("数据: \n" + data + "\n状态: " + status);
            var datajson = jQuery.parseJSON(data);
            var access_token = datajson.get("access_token");
            //var token_type = datajson.get("token_type");
            //var refresh_token = datajson.get("refresh_token");
            //var expires_in = datajson.get("expires_in");
            //var scope = datajson.get("scope");
            $("#access_token").val(access_token);
            $("#principal").submit();
        });

    });
});