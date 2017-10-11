// 抽离tab页切换功能
$(function () {
    for (var i = 0; i < $(".tab_tit span").length ; i++) {
        $('.tab_box .tab_tit span:eq(' + i + ')').bind("click", { x: i }, function (event) {
            $(this).addClass('current').siblings().removeClass('current');
            $('.block_box .block_div').hide();
            $($('.block_box .block_div')[event.data.x]).show();
            
            showTag( event.data.x , $(".tab_tit span").length);
        });
    }
});