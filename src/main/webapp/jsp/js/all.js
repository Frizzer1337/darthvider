$(".reg_btn").click(function(){$("#login").hide(),$("#reg").show(),$(".reg_btn").addClass("active_bttn"),
$(".login_btn").removeClass("active_bttn")}),$(".login_btn").click(function(){$("#login").show(),$("#reg").hide(),
$(".login_btn").addClass("active_bttn"),$(".reg_btn").removeClass("active_bttn")});
$(window).scroll(function() {
  sessionStorage.scrollTop = $(this).scrollTop();
});

$(document).ready(function() {
  if (sessionStorage.scrollTop != "undefined") {
    $(window).scrollTop(sessionStorage.scrollTop);
  }
});