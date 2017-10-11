
/*---------------左侧导航----------------*/
$(function () {
    $(".products_list li").hover(function () {
        $(this).addClass("current");
        $(this).find(".word").css("bottom", "-22px");
        $(this).find(".zi").css("height", "44px");
    }, function () {
        $(this).removeClass("current");
        $(this).find(".word").css("bottom", "-2px");
        $(this).find(".zi").css("height", "22px");
    });
    $(".sousuo .pick .down").hide();
    $(".sousuo .pick p").click(function () {
        if (!$(this).next().is(":visible")) {
            $(this).next().show();
        } else {
            $(this).next().hide();
        }
    });
    $(".sousuo .pick .down li").click(function () {
        $(this).parent().parent().hide();
    });
    //导航 begin
    $('.cont > .item').hover(function () {
        //alert(1);
        var eq = $('.cont > .item').index(this),				//获取当前滑过是第几个元素
            h = $('.cont').offset().top,						//获取当前下拉菜单距离窗口多少像素
            s = $(window).scrollTop(),									//获取游览器滚动了多少高度
            i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
            item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
            sort = $('.cont').height();						//父类分类列表容器的高度

        if (item < sort) {												//如果子类的高度小于父类的高度
            if (eq == 0) {
                $(this).children('.item-list').css('top', (i - h));
            } else {
                $(this).children('.item-list').css('top', (i - h) + 1);
            }
        } else {
            if (s > h) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
                if (i - s > 0) {											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
                    $(this).children('.item-list').css('top', (s - h) + 2);
                } else {
                    $(this).children('.item-list').css('top', (s - h) - (-(i - s)) + 2);
                }
            } else {
                $(this).children('.item-list').css('top', 0);
            }
        }

        $(this).addClass('hover');
        $(this).find("i").css("margin-left", 16);
        $(this).find("span").html("&#xe8c7;");
        $(this).children('.item-list').css('display', 'block');

        if (item < sort) {
            if ((item + $(this).children('.item-list').offset().top) > (h + sort)) {
                $(this).children('.item-list').css("top", (sort - item));
            }
        }

    }, function () {
        $(this).removeClass('hover');
        $(this).find("i").css("margin-left", 10);
        $(this).find("span").html("&#xe8c4;");
        $(this).children('.item-list').css('display', 'none');
    });
    //导航 end
});