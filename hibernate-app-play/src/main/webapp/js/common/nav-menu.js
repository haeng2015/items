$(document).ready(function(){   
  
    //$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)   
	var erji_name="";
    $("ul.topnav li a").mouseover(function() { //When trigger is clicked...   
  
        //Following events are applied to the subnav itself (moving subnav up and down)   
        $(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click   
  
        $(this).parent().hover(function() {   
        }, function(){   
            $(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up   
        });   
  
        //Following events are applied to the trigger (Hover events for the trigger)   
        }).hover(function() {   
            $(this).addClass("subhover"); //On hover over, add class "subhover"
            
            // 是否包含子元素 
            if($(this).parent().find("ul.subnav").length==1){
            	erji_name=$(this).parent().find("#menu_header").text();
            	$("ul.topnav li a").click(function (e) {
            		$('#location_sanji').html('');
                	$('#location_erji').html($(this).parent().find("#menu_header").text());
            		return ;
            	});
            	
            }else if($(this).parent().find("ul.subnav").length==0){
            	$("ul.topnav li a").click(function (e) {
            		$('#location_erji').html(erji_name);
            		$('#location_sanji').html($(this).parent().text());
            		return ;
            	});
            	
            }
        }, function(){  //On Hover Out   
            $(this).removeClass("subhover"); //On hover out, remove class "subhover"   
    });   
//    var iframepageH = $(window).height() - ($(".header_bg").height() + $(".logo_div").height() + $(".nav_bg").height() + $(".foot_bg").height()) - 40;
//    $("#iframepage").height(iframepageH);
});  

$("#login-out").mouseover(function(){
	  $("#login-out").css("text-decoration","none");
	  $("#login-out").css("color","#337ab7");
	  
});
$("#login-out").mouseout(function(){
	  $("#login-out").css("color","#b9b9b9");
});