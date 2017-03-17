
$(function () {
	
	//通过遍历给菜单项加上data-index属性
	$(".CB_menuItem").each(function (index) {
	    if (!$(this).attr('data-index')) {
	        $(this).attr('data-index', index);
	    }
	});
	
	$('.CB_menuItem').on('click', menuItem);
	
	$('.CB_menuTabs').on('click', '.CB_menuTab i', closeTab);
	
	$('.CB_tabCloseOther').on('click', closeOtherTabs);
	
	$('.CB_tabShowActive').on('click', showActiveTab);
	
	$('.CB_menuTabs').on('click', '.CB_menuTab', activeTab);

	$('.CB_menuTabs').on('dblclick', '.CB_menuTab', refreshTab);

	// 左移按扭
	$('.CB_tabLeft').on('click', scrollTabLeft);

	// 右移按扭
	$('.CB_tabRight').on('click', scrollTabRight);
	
	// 关闭全部
	$('.CB_tabCloseAll').on('click', function () {
	    $('.page-tabs-content').children("[data-id]").not(":first").each(function () {
	        $('.CB_iframe[data-id="' + $(this).data('id') + '"]').remove();
	        $(this).remove();
	    });
	    $('.page-tabs-content').children("[data-id]:first").each(function () {
	        $('.CB_iframe[data-id="' + $(this).data('id') + '"]').show();
	        $(this).addClass("active");
	    });
	    $('.page-tabs-content').css("margin-left", "0");
	});

});



//计算元素集合的总宽度
function calSumWidth(elements) {
    var width = 0;
    $(elements).each(function () {
        width += $(this).outerWidth(true);
    });
    return width;
}
//滚动到指定选项卡
function scrollToTab(element) {
    var marginLeftVal = calSumWidth($(element).prevAll()), marginRightVal = calSumWidth($(element).nextAll());
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".CB_menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").outerWidth() < visibleWidth) {
        scrollVal = 0;
    } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
        if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
            scrollVal = marginLeftVal;
            var tabElement = element;
            while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                scrollVal -= $(tabElement).prev().outerWidth();
                tabElement = $(tabElement).prev();
            }
        }
    } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
        scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
}
//查看左侧隐藏的选项卡
function scrollTabLeft() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".CB_menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".CB_menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).prev();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
        }
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    }, "fast");
}
//查看右侧隐藏的选项卡
function scrollTabRight() {
    var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".CB_menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    if ($(".page-tabs-content").width() < visibleWidth) {
        return false;
    } else {
        var tabElement = $(".CB_menuTab:first");
        var offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {//找到离当前tab最近的元素
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        offsetVal = 0;
        while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
            offsetVal += $(tabElement).outerWidth(true);
            tabElement = $(tabElement).next();
        }
        scrollVal = calSumWidth($(tabElement).prevAll());
        if (scrollVal > 0) {
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        }
    }
}



function menuItem() {
    // 获取标识数据
    var dataUrl = $(this).attr('href'),
        dataIndex = $(this).data('index'),
        menuName = $.trim($(this).text()),
        flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;

    // 选项卡菜单已存在
    $('.CB_menuTab').each(function () {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.CB_menuTab').removeClass('active');
                scrollToTab(this);
                // 显示tab对应的内容区
                $('.CB_mainContent .CB_iframe').each(function () {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.CB_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });

    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active CB_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.CB_menuTab').removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="CB_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.CB_mainContent').find('iframe.CB_iframe').hide().parents('.CB_mainContent').append(str1);

        //显示loading提示
        var loading = layer.load(2);

        $('.CB_mainContent iframe:visible').load(function () {
            //iframe加载完成后隐藏loading提示
            layer.close(loading);
        });
        // 添加选项卡
        $('.CB_menuTabs .page-tabs-content').append(str);
        scrollToTab($('.CB_menuTab.active'));
    }
    return false;
}




// 关闭选项卡菜单
function closeTab() {
    var closeTabId = $(this).parents('.CB_menuTab').data('id');
    var currentWidth = $(this).parents('.CB_menuTab').width();

    // 当前元素处于活动状态
    if ($(this).parents('.CB_menuTab').hasClass('active')) {

        // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
        if ($(this).parents('.CB_menuTab').next('.CB_menuTab').size()) {

            var activeId = $(this).parents('.CB_menuTab').next('.CB_menuTab:eq(0)').data('id');
            $(this).parents('.CB_menuTab').next('.CB_menuTab:eq(0)').addClass('active');

            $('.CB_mainContent .CB_iframe').each(function () {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('.CB_iframe').hide();
                    return false;
                }
            });

            var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
            if (marginLeftVal < 0) {
                $('.page-tabs-content').animate({
                    marginLeft: (marginLeftVal + currentWidth) + 'px'
                }, "fast");
            }

            //  移除当前选项卡
            $(this).parents('.CB_menuTab').remove();

            // 移除tab对应的内容区
            $('.CB_mainContent .CB_iframe').each(function () {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
        }

        // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
        if ($(this).parents('.CB_menuTab').prev('.CB_menuTab').size()) {
            var activeId = $(this).parents('.CB_menuTab').prev('.CB_menuTab:last').data('id');
            $(this).parents('.CB_menuTab').prev('.CB_menuTab:last').addClass('active');
            $('.CB_mainContent .CB_iframe').each(function () {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('.CB_iframe').hide();
                    return false;
                }
            });

            //  移除当前选项卡
            $(this).parents('.CB_menuTab').remove();

            // 移除tab对应的内容区
            $('.CB_mainContent .CB_iframe').each(function () {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
        }
    }
    // 当前元素不处于活动状态
    else {
        //  移除当前选项卡
        $(this).parents('.CB_menuTab').remove();

        // 移除相应tab对应的内容区
        $('.CB_mainContent .CB_iframe').each(function () {
            if ($(this).data('id') == closeTabId) {
                $(this).remove();
                return false;
            }
        });
        scrollToTab($('.CB_menuTab.active'));
    }
    return false;
}


//关闭其他选项卡
function closeOtherTabs(){
    $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function () {
        $('.CB_iframe[data-id="' + $(this).data('id') + '"]').remove();
        $(this).remove();
    });
    $('.page-tabs-content').css("margin-left", "0");
}


//滚动到已激活的选项卡
function showActiveTab(){
    scrollToTab($('.CB_menuTab.active'));
}


// 点击选项卡菜单
function activeTab() {
    if (!$(this).hasClass('active')) {
        var currentId = $(this).data('id');
        // 显示tab对应的内容区
        $('.CB_mainContent .CB_iframe').each(function () {
            if ($(this).data('id') == currentId) {
                $(this).show().siblings('.CB_iframe').hide();
                return false;
            }
        });
        $(this).addClass('active').siblings('.CB_menuTab').removeClass('active');
        scrollToTab(this);
    }
}

//刷新iframe
function refreshTab() {
    var target = $('.CB_iframe[data-id="' + $(this).data('id') + '"]');
    var url = target.attr('src');
    //显示loading提示
    var loading = layer.load(2);
    target.attr('src', url).load(function () {
        //关闭loading提示
        layer.close(loading);
    });
}


function getActiveTab(){
	return $(".CB_iframe:visible");
}


//打开选项卡菜单
function openTab(url,title, isNew){//isNew 为true时，打开一个新的选项卡；为false时，如果选项卡不存在，打开一个新的选项卡，如果已经存在，使已经存在的选项卡变为活跃状态。
	
	 // 获取标识数据
    var dataUrl = url,
        dataIndex ,
        menuName = title,
        flag = true;
    if (dataUrl == undefined || top.$.trim(dataUrl).length == 0)return false;
//    //设置dataIndex
//    $(".CB_menuItem").each(function (index) {
//        if (!$(this).attr('data-index')) {
//            $(this).attr('data-index', index);
//        }
//    });
    
    if(!isNew){
		    top.$('.CB_menuTab').each(function () {
		        if (top.$(this).data('id') == dataUrl) {// 选项卡已存在，激活。
		            if (!top.$(this).hasClass('active')) {
		            	top.$(this).addClass('active').siblings('.CB_menuTab').removeClass('active');
		                scrollToTab(top.$(this));
		                // 显示tab对应的内容区
		                top.$('.CB_mainContent .CB_iframe').each(function () {
		                    if (top.$(this).data('id') == dataUrl) {
		                    	top.$(this).show().siblings('.CB_iframe').hide();
		                        return false;
		                    }
		                });
		            }
		            flag = false;
		            return false;
		        }
		    });
    }
    
    if(isNew || flag){//isNew为true，打开一个新的选项卡； flag为true，选项卡不存在，打开一个新的选项卡。
	        var str = '<a href="javascript:;" class="active CB_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
	        top.$('.CB_menuTab').removeClass('active');
	
	        // 添加选项卡对应的iframe
	        var str1 = '<iframe class="CB_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
	        top.$('.CB_mainContent').find('iframe.CB_iframe').hide().parents('.CB_mainContent').append(str1);
	
	        //显示loading提示
	        var loading = layer.load(2);
	
	        top.$('.CB_mainContent iframe:visible').load(function () {
	            //iframe加载完成后隐藏loading提示
	            layer.close(loading);
	        });
	        // 添加选项卡
	        top.$('.CB_menuTabs .page-tabs-content').append(str);
	        scrollToTab(top.$('.CB_menuTab.active'));
    	
    }
    return false;
	
}


