$(".disabled").attr('onclick', '').unbind('click');
$("input#search").focus(function() {
	$(".nav-wrapper .search-wrapper").addClass("focused");
}).blur(function() {
	$(".nav-wrapper .search-wrapper").removeClass("focused");
});
slide1Open = false;
slide2Open = false;
$(document).ready(function() {
	$(".button-collapse").sideNav();
	$("a[data-activates=slide-out1]").sideNav({
		onOpen : function(el) {
			slide1Open = true;
			if (slide2Open)
				$("a[data-activates=slide-out2]").sideNav('hide');
		},
		onClose : function(el) {
			slide1Open = false;
		}
	});
	$("a[data-activates=slide-out2]").sideNav({
		onOpen : function(el) {
			slide2Open = true;
			if (slide1Open)
				$("a[data-activates=slide-out1]").sideNav('hide');
		},
		onClose : function(el) {
			slide2Open = false;
		}
	});
	$("#chartdiv").click(function(e) {
		$(".chat_win").removeClass('active');
	});
	$(".chat_win").click(function(e) {
		$(".chat_win").addClass('active');
	});
});