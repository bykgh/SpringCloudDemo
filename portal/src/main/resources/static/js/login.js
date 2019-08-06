$(document).ready(function(){
    $("#cerCodeImg").click(function () {
        $("#cerCodeImg").attr('src',"/portal/kaptcha/verCode?random="+Math.random());
    });
});