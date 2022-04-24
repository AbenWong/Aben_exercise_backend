//login页面绑定事件
$(function () {
    $("#login_button").click(function () {
        loginCheck();
    })
});
function loginCheck() {
    var username = $("#username").val();
    var password = $("#password").val();
    if(username==null||username==""){
        layer.msg("用户名不为空");
    }else {
        if (password==null||password==""){
            layer.msg('密码不能为空');
        }else {
            $("#login_form").submit();
        }
    }


}