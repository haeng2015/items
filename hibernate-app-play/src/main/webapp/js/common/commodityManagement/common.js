$(function(){
	
	var hdw = $('.left_nav h3');
	$(".left_nav ul").eq(1).hide();

	
	hdw.click(function(){
			
		$(this).addClass('current')
				.siblings(hdw).removeClass();
					
	var hdw_index = hdw	.index(this);
	
	$('.left_nav ul').eq(hdw.index(this)).show()
			.siblings('.left_nav ul').hide();
	});
	
	
	/*$(".left_nav h3").css("cursor", "pointer");
    $(".left_nav h3").click(function () {
        if ($(this).next("ul").is(":hidden")) {
            $(this).next("ul").show();
            $(this).find("i.icon").html("&#xe8c4;");
        } else {
            $(this).next("ul").hide();
            $(this).find("i.icon").html("&#xe8c7;");
        }
    });*/

});