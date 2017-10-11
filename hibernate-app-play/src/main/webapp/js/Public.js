$(document).ready(function () {
//二级导航鼠标经过
	$(".nav li").hover(function () {
		$(this).find(".block_a p.bor").stop().animate({ 'bottom': '0' }, 250);
		$(this).find(".block_div").stop(true, true).slideDown(300);
	}, function () {
		$(this).find(".block_div").stop(true, true).slideUp(1);
		if ($(this).find(".block_a").hasClass("current")) {
			return;
		}
		$(this).find(".block_a p.bor").stop().animate({ 'bottom': '-10px' }, 250);
	});

	//二级导航鼠标点击  
	$(".nav li .block_div a").click(function () {
		$(".nav li .block_a").removeClass("current");
		$(".block_a p.bor").stop().animate({ 'bottom': '-10px' }, 250);
		$(this).parents("li").find(".block_a").addClass("current");
		$(this).parents("li").find(".block_a p.bor").stop().animate({ 'bottom': '0' }, 250);
	});
	$(".nav li .block_a").click(function () {
		$(".nav li .block_a").removeClass("current");
		$(".block_a p.bor").stop().animate({ 'bottom': '-10px' }, 250);
		$(this).parents("li").find(".block_a").addClass("current");
		$(this).parents("li").find(".block_a p.bor").stop().animate({ 'bottom': '0' }, 250);
	});
	
	$(".gjc").focus(function() {
	  var input =$(".gjc");
	  if (input.val() == input.attr('placeholder')) {
		input.val('');
		input.removeClass('placeholder');
	  }
	}).blur(function() {
	  var input = $(".gjc");
	  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		input.addClass('placeholder');
		input.val(input.attr('placeholder'));
	  }
	}).blur().parents('form').submit(function() {
		$(".gjc").find('[placeholder]').each(function() {
		var input = $(".gjc");
		if (input.val() == input.attr('placeholder')) {
		  input.val('');
		}
	  });
	});
	
	
});


//$("#iframepage").load(function () {
//    var mainheight = $(this).contents().find("body").height();
//    $(this).height(mainheight);
//    var footerFixed = function () {
//        var ourH = $(".header_bg").height() + $(".logo_div").height() + $(".nav_bg").height() + mainheight;
        
//        if ($(window).height() > ourH) {
//            $(".foot_bg").addClass("footer_fixed");
//        } else {
//            $(".foot_bg").removeClass("footer_fixed");
//        }
//    }
//    console.log(ourH);
//    $(window).scroll(function () { footerFixed(); });
//    $(window).resize(function () { footerFixed(); });
//    $(window).load(function () { footerFixed(); });
//});
    //var mainheight = $(this).contents().find("body").height();
    //$(this).height(mainheight);

    //底部
    //var footerFixed = function () {
    //    var ourH = $(".header_bg").height() + $(".logo_div").height() + $(".nav_bg").height() + mainheight;

    //    if ($(window).height() > ourH) {
    //        $(".foot_bg").addClass("footer_fixed");
    //    } else {
    //        $(".foot_bg").removeClass("footer_fixed");
    //    }
    //}
    //console.log($(document).height());
    //$(window).scroll(function () { footerFixed(); });
    //$(window).resize(function () { footerFixed(); });
    //$(window).load(function () { footerFixed(); });


////iframe
//function iFrameHeight() {
//    var ifm = document.getElementById("iframepage");
//    var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
//    if (ifm != null && subWeb != null) {
//        ifm.height = subWeb.body.scrollHeight;
//    }
//}

