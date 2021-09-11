$(".reg_btn").click(function(){$("#login").hide(),$("#reg").show(),$(".reg_btn").addClass("active_bttn"),
$(".login_btn").removeClass("active_bttn")}),$(".login_btn").click(function(){$("#login").show(),$("#reg").hide(),
$(".login_btn").addClass("active_bttn"),$(".reg_btn").removeClass("active_bttn")});
