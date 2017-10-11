/**
 * 获取项目根路径
 * @returns
 */
function getRootPath(){  
    //获取当前网址，如： http://localhost:8088/test/test.jsp  
    var curPath=window.document.location.href;  
    //获取主机地址之后的目录，如： test/test.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8088  
    var localhostPaht=curPath.substring(0,pos);  
    //获取带"/"的项目名，如：/test  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return(localhostPaht + projectName);  
}

/**
 * 返回云平台项目的跟目录（不带尾“/”）
 * @returns {String}
 */
function getPanasignYunWebRootPath(){
	return "http://localhost/panasignYunWeb";
}

/**
 * 给tabs面板组合生成新的tab面板
 * @param tabsId	要生成新标签页的面板组的id
 * @param windowTitle	生成新标签页名称
 * @param url	新标签页的url（内容引用其他页面的话）
 * @author wu.liang
 * @version	1.0
 */
function openOneTab(tabsId, windowTitle, url) {
	if ($("#" + tabsId).tabs('exists', windowTitle)) {
		// 判断要创建的标签面板是否存在，如果存在则选择显示它
		$("#" + tabsId).tabs('select', windowTitle);
		return;
	}
	$("#" + tabsId).tabs('add', {
		title : windowTitle,
		selected : true,
		closable : true,
		href : url,
		tools : [{
//			iconCls:'icon-mini-refresh',
			handler:function(){
				//点击刷新按钮触发方法
				$("#" + tabsId).tabs('getSelected').panel('refresh');
			}
		}],
	// some another options...
	});
}

/**
 * 带单参数跳转新页面
 * @param url	跳转地址（相对于调用此方法的页面相对url路径）
 * @param name	参数名称（post提交）
 * @param value	参数值
 */
function skipToNewPageById(url, name, value){
	//定义一个form提交表单
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	document.body.appendChild(form);
	
	//定义一个隐藏input存放id
	var input = document.createElement("input");
	input.type = "hidden";
	input.value = value;
	input.name = name;
	form.appendChild(input);
	
	form.submit();
}

/**
 * 带两个单参数跳转新页面
 * @param url
 * @param name01
 * @param value01
 * @param name02
 * @param value02
 */
function skipToNewPageByTwoParams(url, name01, value01, name02, value02){
	//定义一个form提交表单
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	document.body.appendChild(form);
	
	//定义一个隐藏input存放id
	var input01 = document.createElement("input");
	input01.type = "hidden";
	input01.name = name01;
	input01.value = value01;
	form.appendChild(input01);
	
	//定义一个隐藏input存放id
	var input02 = document.createElement("input");
	input02.type = "hidden";
	input02.name = name02;
	input02.value = value02;
	form.appendChild(input02);
	
	form.submit();
}

/**
 * 带参数跳转到下个页面，可有任意多的参数
 * @param url
 * @param array	['xxx=xxx','yyy=yyy','zzz=zzz'......]
 */
function skipToNewPageByParams(url, array){
	//定义一个form提交表单
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	document.body.appendChild(form);
	
	if(array){
		for(var i =0; i<array.length; i++){
			var param = array[i].split("=");
			if(param.length!=2)
				continue;
			//定义一个隐藏input存放id
			var input02 = document.createElement("input");
			input02.type = "hidden";
			input02.name = param[0];
			input02.value = param[1];
			form.appendChild(input02);
		}
	}
	
	form.submit();
}
/**
 * 新打开对话框功能，可加载其他页面
 * @param id
 * @param width
 * @param height
 * @param titile
 * @param draggable 是否可拖动：true,false
 * @param href 此对话框内容页面地址
 * @param closed 是否可关闭，true,false
 * @author Murongya
 * @version	1.0
 */
function openOneDialog(id, width, height, titile, canDraggable, href, closed){
	$('<div/>').dialog({
		title : titile,
	    width : width,
	    draggable : canDraggable,
	    height : height,
	    closable : true,
	    closed : closed,
//	    cache: false,
	    onClose : function(){
	    	//此句话在关闭对话框之后释放内存
	    	$("#"+id).panel('destroy',true);
	    	//关闭遮罩以及阴影部分组件
	    	clearMemory();
	    },
	    modal : true,
	    href : href,
	    id : id
	});
}
/**
 * progressbar滚动效果
 * @param barId progressBarId
 */
function progressBarRun(barId){
	var value = $("#"+barId).progressbar('getValue');
	if (value < 100){
	    value += Math.floor(Math.random() * 10);
	    if(value>95)value=10;
	    $("#"+barId).progressbar('setValue', value);
	}
}
/**
 *关闭遮罩阴影部分组件，释放内存，遮罩阴影组件中第一个元素保留（预留给滚动条使用）
 */
function clearMemory(){
	$(".window-shadow:gt(0)").remove();
	$(".window-mask:gt(0)").remove();
}

/**
 * input只允许输入数字或小数
 * 引用方法：onkeyup='clearNoNum(this);'
 * @param obj
 */
function clearNoNum(obj){
	//如果文本框为空，设置为0
	if(!obj.value)
		obj.value=0;
	
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}

/**
 * 从最顶层窗口的iframe中定位某个元素，注意：返回的是jquery对象
 * topIframeId：	最顶层iframe的Id，
 * elementId：	要从中定位的元素的Id
 */
function getElementFromTopIframe(topIframeId, elementId){
	var topDocument = top.window.document;
	var topIframe = $(topDocument).find("#"+topIframeId)[0];
	return $(topIframe.contentWindow.document).find("#"+elementId);
}

/**
 * 获取最顶层的iframe，注意：返回的是jquery对象
 * topIframeId：	最顶层iframe的Id
 */
function getTopIframe(topIframeId){
	var topDocument = top.window.document;
	return $(topDocument).find("#"+topIframeId);
}

/**
 * 渲染一个selector列表
 * @param selectorId 传入id
 * @param objs 数据库返回的 List<Object>形式数组元素列表
 * @param msg 如：<option value=''>msg</option> 列表提示信息，如：‘请选择产品类型’
 */
function renderSelector(selectorId, objs, msg){
	//清空选择列表
	$("#"+selectorId).empty();
	if(msg){
		$("#"+selectorId).append("<option value=''>"+msg+"</option>");
	}
	$.each(objs, function(i, n){
		  $("#"+selectorId).append("<option value='"+objs[i].boProvinceCityRegionId+"'>"+objs[i].name+"</option>");
	});
}


/**
 * 设为首页 www.ecmoban.com
 */
function SetHome(obj,url){
    try{
        obj.style.behavior='url(#default#homepage)';
       obj.setHomePage(url);
   }catch(e){
       if(window.netscape){
          try{
              netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
         }catch(e){
              alert("抱歉，此操作被浏览器拒绝！\n\n请在浏览器地址栏输入“about:config”并回车然后将[signed.applets.codebase_principal_support]设置为'true'");
          }
       }else{
    	   alert("抱歉，您所使用的浏览器无法完成此操作。\n\n您需要手动将【"+url+"】设置为首页。");
       }
  }
}
 
/**
 * 收藏本站 bbs.ecmoban.com
 */
function AddFavorite(title, url) {
  try {
      window.external.addFavorite(url, title);
  }
catch (e) {
     try {
       window.sidebar.addPanel(title, url, "");
    }
     catch (e) {
    	 alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
     }
  }
}

/*---------------------------datagrid扩展一个鼠标移上去显示所有内容的信息框 start-----------------------*/
/**  
 * 扩展两个方法  用于datagrid鼠标移动上去之后显示内容
 */  
$.extend($.fn.datagrid.methods, {   
    /**
     * 开打提示功能  
     * @param {} jq  
     * @param {} params 提示消息框的样式  
     * @return {}  
     */  
    doCellTip : function(jq, params) {   
        function showTip(data, td, e) {   
            if ($(td).text() == "")   
                return;   
            data.tooltip.text($(td).text()).css({   
                        top : (e.pageY + 10) + 'px',   
                        left : (e.pageX + 20) + 'px',   
                        'z-index' : $.fn.window.defaults.zIndex,   
                        display : 'block'   
                    });   
        };   
        return jq.each(function() {   
            var grid = $(this);   
            var options = $(this).data('datagrid');   
            if (!options.tooltip) {   
                var panel = grid.datagrid('getPanel').panel('panel');   
                var defaultCls = {   
                    'border' : '1px solid #333',   
                    'padding' : '2px',   
                    'color' : '#333',   
                    'background' : '#f7f5d1',   
                    'position' : 'absolute',   
                    'max-width' : '600px',  //提示框的最大宽度
                    'border-radius' : '3px',   
                    '-moz-border-radius' : '3px',   
                    '-webkit-border-radius' : '3px',   
                    'display' : 'none'   
                };   
                var tooltip = $("<div id='celltip'></div>").appendTo('body');   
                tooltip.css($.extend({}, defaultCls, params.cls));   
                options.tooltip = tooltip;   
                panel.find('.datagrid-body').each(function() {   
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length   
                            ? $(this).find('> div.datagrid-body-inner')[0]   
                            : this;   
                    $(delegateEle).undelegate('td', 'mouseover').undelegate(   
                            'td', 'mouseout').undelegate('td', 'mousemove')   
                            .delegate('td', {   
                                'mouseover' : function(e) {   
                                    if (params.delay) {   
                                        if (options.tipDelayTime)   
                                            clearTimeout(options.tipDelayTime);   
                                        var that = this;   
                                        options.tipDelayTime = setTimeout(   
                                                function() {   
                                                    showTip(options, that, e);   
                                                }, params.delay);   
                                    } else {   
                                        showTip(options, this, e);   
                                    }   
  
                                },   
                                'mouseout' : function(e) {   
                                    if (options.tipDelayTime)   
                                        clearTimeout(options.tipDelayTime);   
                                    options.tooltip.css({   
                                                'display' : 'none'   
                                            });   
                                },   
                                'mousemove' : function(e) {   
                                    var that = this;   
                                    if (options.tipDelayTime) {   
                                        clearTimeout(options.tipDelayTime);   
                                        options.tipDelayTime = setTimeout(   
                                                function() {   
                                                    showTip(options, that, e);   
                                                }, params.delay);   
                                    } else {   
                                        showTip(options, that, e);   
                                    }   
                                }   
                            });   
                });   
  
            }   
  
        });   
    },   
    /**
     * 关闭消息提示功能  
     * @param {} jq  
     * @return {}  
     */  
    cancelCellTip : function(jq) {   
        return jq.each(function() {   
                    var data = $(this).data('datagrid');   
                    if (data.tooltip) {   
                        data.tooltip.remove();   
                        data.tooltip = null;   
                        var panel = $(this).datagrid('getPanel').panel('panel');   
                        panel.find('.datagrid-body').undelegate('td',   
                                'mouseover').undelegate('td', 'mouseout')   
                                .undelegate('td', 'mousemove');   
                    }   
                    if (data.tipDelayTime) {   
                        clearTimeout(data.tipDelayTime);   
                        data.tipDelayTime = null;   
                    }   
                });   
    }   
});  
/*---------------------------datagrid扩展一个鼠标移上去显示所有内容的信息框 end-----------------------*/
/**
 * 扩展的基本校验规则，
 */
$.extend($.fn.validatebox.defaults.rules, { 
    minLength : { // 判断最小长度 
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            return value.length >= param[0]; 
        }, 
        message : "最少输入 {0} 个字符。"
    }, 
    length:{validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
            message:"长度必须在 {0} 到 {1} 之间"
        }, 
    phone : {// 验证电话号码 
        validator : function(value) { 
            return /^\d{3,4}-?\d{7,9}-?\d{2,8}$/.test(value); 
//            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value); 
        }, 
        message : '格式不正确,请使用下面格式:0571-8888888-001'
    }, 
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(13|15|18)\d{9}$/.test(value); 
        }, 
        message : '手机号码格式不正确'
    },
    url : {// 验证网址格式
        validator : function(value) { 
        	// 1、简单网站验证
//            return /^(http(s)?:\/\/)?(www\.)?[\w-]+\.\w{2,4}(\/)?$/.test(value);
        	// 2、ftp、rtsp等复杂网站验证
        	return /^((https|http|ftp|rtsp|mms)?:\/\/)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/.test(value); 
        }, 
        message : '网址格式不正确'
    },
    idcard : {// 验证身份证 
        validator : function(value) { 
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/.test(value); 
        }, 
        message : '身份证号码格式不正确'
    }, 
    intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/.test(value); 
        }, 
        message : '请输入正确的数字'
    }, 
    currency : {// 验证货币 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/.test(value); 
        }, 
        message : '货币格式不正确'
    }, 
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,13}$/.test(value); 
        }, 
        message : 'QQ号码格式不正确'
    }, 
    integer : {// 验证整数 /^[+]?[1-9]+\d*$/
        validator : function(value) { 
            return /^[0-9]*$/.test(value); 
        }, 
        message : '请输入整数'
    },     
    isPort : {// 验证整数 /^[+]?[1-9]+\d*$/
    	validator : function(value) { 
    		return /^[0-9]*$/.test(value) && value<65536; 
    	}, 
    	message : '端口号不正确'
    },     
    chinese : {// 验证中文 
        validator : function(value) { 
            return /^[\u0391-\uFFE5]+$/.test(value); 
        }, 
        message : '请输入中文'
    }, 
    english : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/.test(value); 
        }, 
        message : '请输入英文'
    }, 
    unnormal : {// 验证是否包含空格和非法字符 
        validator : function(value) { 
            return /.+/.test(value); 
        }, 
        message : '输入值不能为空和包含其他非法字符'
    }, 
    username : {// 验证用户名 
        validator : function(value) { 
            return /^[a-zA-Z0-9][a-zA-Z0-9_]+$/.test(value); 
        }, 
        message : '输入不合法（字母数字开头，允许字母数字下划线）'
    },
    password : {// 验证密码 
        validator : function(value) {
        	return /^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[_~!@#$%^&*])+$/.test(value);
//            return /^[a-zA-Z0-9][a-zA-Z0-9_~!@#$%^&*]+$/.test(value); 
        }, 
        message : '输入不合法（字母数字开头，允许字母数字和下列符号：_~!.@#$%^&*）'
    }, 
    faxno : {// 验证传真 
        validator : function(value) { 
//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/.test(value); 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value); 
        }, 
        message : '传真号码不正确'
    }, 
    zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/.test(value); 
        }, 
        message : '邮政编码格式不正确'
    }, 
    ip : {// 验证IP地址/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        validator : function(value) { 	
            return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value); 
        }, 
        message : 'IP地址格式不正确'
    }, 
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\u0391-\uFFE5]+$/.test(value)|/^\w+[\w\s]+\w+$/.test(value); 
            }, 
            message : '请输入姓名'
    }, 
    carNo:{ 
        validator : function(value){ 
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
        }, 
        message : '车牌号码无效（例：粤J12350）'
    }, 
    carenergin:{ 
        validator : function(value){ 
            return /^[a-zA-Z0-9]{16}$/.test(value); 
        }, 
        message : '发动机型号无效(例：FG6H012345654584)'
    }, 
    email:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的电子邮件账号(例：abc@126.com)'   
    }, 
    msn:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'   
    },
    warnmintime : { // 判断告警的值只能一级一级的增加，最小值
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            if( value !="")
            for(var i=0;i<param.length; i++){
                $(param[i]).val();
                if($(param[i]).combobox('getValue')){
                    var temp=$.trim($(param[i]).combobox('getValue'));
                    if(temp !="" && !isNaN(temp) && parseInt(value) <= parseInt(temp))
                        return false;
                   }
            }
            return true;
        }, 
        message : '不能小于当前告警的前一级的告警时间'
    },
    warnmaxtime : { // 判断告警的值只能一级一级的增加，最大值
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            if( value !="")
            for(var i=0;i<param.length; i++){
                $(param[i]).val();
                if($(param[i]).combobox('getValue')){
                    var temp=$.trim($(param[i]).combobox('getValue'));
                    if(temp !="" && !isNaN(temp) && parseInt(value) >= parseInt(temp))
                        return false;
                   }
            }
            return true;
        }, 
        message : '不能大于当前告警的后一级的告警时间'
    },
    compareDate: {
        validator: function (value, param) {
        return dateCompare($(param[0]).datetimebox('getValue'), value); //注意easyui 时间控制获取值的方式
        },
        message: '开始日期不能大于结束日期'
        },
});


/**
 * select标签 兼容chrome方法
 */
function simOptionClick4IE(){
	var evt=window.event  ;
	var selectObj=evt?evt.srcElement:null;
	// IE Only
	if (evt && selectObj &&  evt.offsetY && evt.button!=2
		&& (evt.offsetY > selectObj.offsetHeight || evt.offsetY<0 ) ) {
			
			setTimeout(function(){
				var option=selectObj.options[selectObj.selectedIndex];
				// 此时可以通过判断 oldIdx 是否等于 selectObj.selectedIndex
				// 来判断用户是不是点击了同一个选项,进而做不同的处理.
				getCitiesByProvinceId(option.value);

			}, 60);
	}
}

/** 选择checkbox  
 * 	如：<input type="checkbox" name="checkbox_Name" id="checkbox_Name" value="value1" /> 
 *  返回所有  name="checkbox_Name" 状态：checked 的值拼接成返回结果
 *  返回结果：value1,value2,.....,valuen
 * @param checkbox_Name
 * @returns {String}
 */
function checkboxList(checkbox_Name){
	var check_list=document.getElementsByName(checkbox_Name);
	var checkList ="";
	for(var i=0;i<check_list.length;i++){
		if(check_list[i].checked){
			checkList += check_list[i].value+","; 
		}
	}	
	if(checkList!=null && checkList.length > 0){
		checkList =  checkList.toString().substring(0,checkList.toString().lastIndexOf(","));
	}
	return checkList;
}

/**checkbox是否全选  ：按钮实现
 * @param checkbox_Name 子Checkbox Name属性
 * @param check_all     总Checkbox Name属性
 */
function isCheckboxAllSelected(checkbox_Name,check_all){
	var check_list=document.getElementsByName(checkbox_Name);
	var boProductIds = checkboxList(checkbox_Name);
	if(boProductIds!=null && boProductIds !=""){
		var productslength = boProductIds.split(",");
		if(check_list.length == productslength.length){
			$("input[name='"+check_all+"']").prop("checked",true);
		}else{
			$("input[name='"+check_all+"']").prop("checked",false);
		}
	}
}
