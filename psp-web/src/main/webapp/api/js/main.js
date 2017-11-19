//var Base_Path = 'http://10.0.0.6/scope-app/';
var href = window.document.location.href;
var index = href.indexOf("psp-web");
var Base_Path = href.substring(0, index) + 'psp-web';
//字符转json对象
var jsonData = '';
var protocolsJson = '';//eval("("+protocols+")")
  //遍历json对象
$(function(){
  $.ajax({
      url: Base_Path + '/api/getData',
      dataType: 'JSON',
      error: function(){
          alert('Error loading XML document');
      },
      success: function(data){
          jsonData = data.beans;
          protocolsJson = data.protocolFiles;
          //　处理beans
          setBeans();
          // 处理协议
          SetProtocols();
      }
  });



});


//　处理beans
function setBeans(){
  var menuhtml = '<ul>';
  var beanhtml = '';
  var beantble = '<table  class="pct90 mb20 auto beantbl">'
    +'<tr><th width="15%">类型</th><th width="20%">名称</th><th width="35%">默认值</th><th width="30%">备注</th></tr>';
  $.each(jsonData,function(idx,item){
    //输出--目录
    menuhtml += '<li><a href="#bean-' + item.className + '">';
    menuhtml += item.className + '-' + item.notes;

    // 输出bean table
    beanhtml += '<h4 class="pct90 f14 m15 auto " id="bean-' + item.className  +'">'+item.className+'</h4>';
    beanhtml += beantble;
    var trhtml = '';
    $.each(item.attrs,function(index, attr){
      trhtml += '<tr>';
      trhtml += '<td>' +
        (typeof(attr.isCustom!='undefined') && attr.isCustom ?
        '<a href="#bean-' + item.className + '">' + attr.type + '</a>'
        : attr.type) + '</td>';
      trhtml += '<td>' + attr.name + '</td>';
      trhtml += '<td>' + (typeof(attr.demoValue) == 'undefined' ? '' : attr.demoValue) + '</td>';
      trhtml += '<td>' + (typeof(attr.notes) == 'undefined' ? '' : attr.notes) + '</td>';
      trhtml += '</tr>'
    });
    beanhtml += trhtml + '</table>';

  });
  menuhtml += '</ul>';
  $('#beans-menu').html(menuhtml);
  $('#beans-div').html(beanhtml);
}
// 处理协议
function SetProtocols(){
  var menuhtml = '';
  var protocol = '';
  $.each(protocolsJson,function(pdex, ptem){
    menuhtml += '<h3><a href="#p_' + ptem.className + '-' + pdex +  '" >' + ptem.notes +' '+ ptem.version + '-' + ptem.className + '</a></h3>';
    menuhtml += '<ul class="pl10">';
    protocol += '<h3 id="p_' + ptem.className + '-' + pdex +  '">' + ptem.notes+' '+ ptem.version +'-' + ptem.className + '</h3>';
    var protocolslist = ptem.protocols;
    $.each(protocolslist,function(idx,item){
      //输出--目录
      menuhtml += '<li><a href="#protocol-' + item.name + '-' + pdex +  '" >';
      menuhtml += (idx+Number(1)) +'. ' + item.notes + '-' + item.name;

      // 输出protocol bean table
      protocol += '<h4 class="pct90 f14 m10 auto " id="protocol-' + item.name + '-' + pdex + '">'+ (idx+Number(1)) + '. ' +item.notes+'-'+item.name+'</h4>';
      protocol += '<table  class="pct90 mb20 auto protocoltbl">';
      protocol += '<tr><td class="pct15 tc">方法</td><td class="tl pl10">' + ptem.requestMapping + item.requestMapping + '</td></tr>';
      protocol += '<tr><td class="pct15 tc">说明</td><td class="tl pl10">' + item.notes + '</td></tr>';
      protocol += '<tr><td class="pct15 tc">接口状态</td><td class="tl pl10">' + (item.state==0?'<span style="color:red">未完成</span>':'<span style="color:green">完成</span>') + '</td></tr>';
      // 示例参数
      var params = '';
      if(typeof(item.requestName)!='undefined' || typeof(item.request)!='undefined'){
        protocol += '<tr><td class="pct15 tc">参数名称</td><td class="tl pl10">' + item.requestName + '</td></tr>';
        if(typeof(item.request)!='undefined'){
          var rqParam = '<table class="pct100" border="0"><tr>'
                    + '<th class="pct15 tc">参数名</th><th class="pct15 tc">参数类型</th>'
                    + '<th class="pct15 tc">参数demo</th><th class="tc">备注</th></tr>';
          $.each(item.request,function(index, attr){
              rqParam += '<tr>';
              var require = attr.require ? ('<span style="color:red">'+attr.name+' *</span>') : attr.name;
              rqParam += '<td class="pct15 pl10">' + require +'</td>';
              rqParam += '<td class="tl pl10">' + attr.type + '</td>';
              rqParam += '<td class="tl pl10">' + attr.demoValue + '</td>';
              rqParam += '<td class="tl pl10">' + attr.notes + '</td>';
              rqParam += '</tr>'
              // 拼接示例参数
              params += '&' + attr.name + '=' + attr.demoValue;
          });
          rqParam += '</table>'
        }
        protocol += '<tr><td class="pct15 tc">参数</td><td class="tl">' + rqParam + '</td></tr>';
      }
      protocol += '<tr><td class="pct15 tc">返回类型</td><td class="tl pl10">' + item.resType + '</td></tr>';
      protocol += '<tr><td class="pct15 tc">响应对象类型</td><td class="tl pl10">' + item.resDataType + '</td></tr>';
      var responseName = item.responseName;
	  if(responseName != undefined) {
	      responseName = responseName.endWith('Bean')? '<a href="#bean-' + item.responseName  + '">'+ item.responseName + '</a>'
	                  : item.responseName ;
	      protocol += '<tr><td class="pct15 tc">响应对象</td><td class="tl pl10">' + responseName + '</td></tr>';
	  }
      protocol += typeof(item.responseDemoValue)=='undefined' ? ''
              : '<tr><td class="pct15 tc">响应对象实例</td><td class="tl pl10">' + item.responseDemoValue + '</td></tr>';
      // 实例
      var subparams = params.substring(1, params.length);
      var reqdemo = params==''? '' :'<textarea id="paramarea' + pdex + idx +'" class="m10 mb5" cols="80" rows="6">' + subparams +'</textarea>'
      var url =  Base_Path + ptem.requestMapping +'/' + item.requestMapping ;
      reqdemo += '<p><span class="ajaxbtn" onclick="testInWindow(\'' + pdex + idx + '\',\'' + url + '\',\'post\',\'' + item.resType + '\')">test</span>'
                  + '<span class="ajaxbtn" onclick="testOutWindow(\'' + pdex + idx + '\',\'' + url + '\')">browse</span></p>';
      reqdemo += '<div class="json-collapsed" id="json-collapsed' + pdex + idx + '"></div>'
      protocol += '<tr><td class="pct15 tc">示例</td><td class="tl">' + reqdemo + '</td></tr>';
      protocol += '</table>';
    });
    menuhtml += '</ul>';
  });


  $('#protocols-menu').html(menuhtml);
  $('#protocols-div').html(protocol);
}

// 定义endWith 方法
String.prototype.endWith=function(s){
 if(s==null||s==""||this.length==0||s.length>this.length)
    return false;
 if(this.substring(this.length-s.length)==s)
    return true;
 else
    return false;
 return true;
}

// 在当前页示例
function testInWindow( num, url, type, dataType){
	
  $.ajax({
      url: url  + '?' +  $("#paramarea"+num).val(),
      type: type,
      dataType: dataType,
      timeout: 2000,
      async: false,
      contentType: "application/json; charset=utf-8",  
      error: function(){
          alert('Error loading XML document');
      },
      success: function(data){
          $("#json-collapsed" + num).show();
          $("#json-collapsed" + num).JSONView(data, { collapsed: true, nl2br: true, recursive_collapser: true });
      }
  });

}

// 跳出窗口示例
function testOutWindow(num, url){
	window.open(url + '?' + $("#paramarea"+num).val());
}
