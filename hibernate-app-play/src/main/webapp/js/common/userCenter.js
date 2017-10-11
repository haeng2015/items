/**
 * 提供一个启动滚动条的方法。
 */
function openProgressBar(){
	$('#progressBarWindow').panel('open',true);
}

/**
 * 提供一个关闭滚动条的方法。
 */
function closeProgressBar(){
	$('#progressBarWindow').panel('close');
}
/**
 * 提供一个提示条（alert）的方法。
 */
function alertMsg(title,msg,icon){
	$.messager.alert({title:title, msg:msg, icon:icon});
}
/**
 * 提供一个提示条（show）的方法。
 */
function showMsg(title,msg){
	$.messager.show({title:title,msg:'<h4>'+msg+'</h4>',width:300,height:125,showType:'show',timeout:2000});
}

/**
 * 提供一个框架对话框弹出互动操作。
 */
function confirmMsg(title, msg, fn){
	$.messager.confirm(title, msg, function(r){
		if (r){
			fn();
		}
	});
}

/**
 * 定义一个对话框输入
 */
function openDialog(id,width,height,titile,draggable,href,closed){
	openOneDialog(id,width,height,titile,draggable,href,closed);
}

$(document).ready(function () {
	var erji_name="";
	//二级导航
	$(".nav li").hover(function () {
		$(this).find(".block_a p.bor").stop().animate({ 'bottom': '1px' }, 250);
		$(this).find(".block_div").stop(true, true).slideDown(300);
		erji_name = $(this).find(".block_a").find("#menu_header").text();
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
        $('#location_erji').html(erji_name);
        $('#location_sanji').html("&nbsp;>&nbsp;"+$(this).text());
        $(this).parents("li").find(".block_a").addClass("current");
        $(this).parents("li").find(".block_a p.bor").stop().animate({ 'bottom': '0' }, 250);
    });
    $(".nav li .block_a").click(function () {
        $(".nav li .block_a").removeClass("current");
        $(".block_a p.bor").stop().animate({ 'bottom': '-10px' }, 250);
        $(this).parents("li").find(".block_a").addClass("current");
        $(this).parents("li").find(".block_a p.bor").stop().animate({ 'bottom': '0' }, 250);
    });

	/*-----------------滚动进度条滚动效果--------------------*/
	window.setInterval("progressBarRun('progressBar')", 100);
	
	/*--------------初始化完毕后关闭进度条----------------*/
	$('#progressBarWindow').panel('close');
});

