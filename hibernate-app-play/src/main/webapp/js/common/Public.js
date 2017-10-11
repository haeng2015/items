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
});

