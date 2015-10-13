<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>QuickStart Publisher</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<meta property="og:title" content="IBM IoT Foundation QuickStart Publisher サンプル"/>
<meta property="og:type" content="website"/>
<meta property="og:url" content="http://<%= request.getServerName() %>/"/>
<meta property="og:image" content="./logo.png"/>
<meta property="og:site_name" content="QuickStart Publisher"/>
<meta property="og:description" content="deviceID を指定して MQTT メッセージを IBM IoT Foundation Quickstart の MQTT ブローカーにパブリッシュするサンプル"/>

<meta http-equiv="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="apple-mobile-web-app-capable" content="yes" />

<script src="//code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
  $('#frm1').submit(function(){
    $.ajax({
      type: 'POST',
      url: './quickstartpublish',
      data: {
        "deviceid": $('#deviceid').val(),
        "payload": $('#payload').val()
      },
      success: function( json ){
        console.log( json );
      },
      error: function( XMLHttpRequest, textStatus, errorThrown ){
        console.log( "XMLHttpRequest = " + XMLHttpRequest );
        console.log( "textStatus = " + textStatus );
        console.log( "errorThrown = " + errorThrown );
      }
    });
    
    return false;
  });
});
</script>
<style>
html, body, {
  height: 100%;
  margin: 0px;
  padding: 0px
}

*{
 margin: 0;
 padding: 0;
}

.navbar-fixed-top{
 position: fixed;
 top: 0;
 right: 0;
 left: 0;
 z-index: 1030;
 margin-bottom: 0;
 background-color: #428BCA;
 foreground-color: #ffffff;
}

body{
 background-color: #eee;
}

.carnonactive{
  border-width: 0px;
}
.caractive{
  border-color: #ffcccc;
  border-width: thick;
  border-style: solid;
}
</style>

</head>
<body>

  <!-- //navi -->
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-header">
      <a class="navbar-brand" href="./">QuickStart Publish</a>
      <button class="navbar-toggle" data-toggle="collapse" data-target=".target">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    <div class="collapse navbar-collapse target">
      <ul class="nav navbar-nav navbar-right">   
      	<li>
		</li>
      </ul>
    </div>
  </nav>
  <!-- navi// -->

<%
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss", Locale.JAPAN );
String dt = sdf.format( date );
%>
  <!-- //publish form -->
  <div class="container" style="padding:80px 0">
    <form name="frm1" id="frm1" method="post" action="./quickstartpublish">
      <table class='table table-bordered table-hover table-condensed'>
        <tr><td>DeviceType</td><td><input type="text" name="devicetype" id="devicetype" value=""/></td></tr>
        <tr><td>EventType</td><td><input type="text" name="eventtype" id="eventtype" value=""/></td></tr>
        <tr><td>DeviceID</td><td><input type="text" name="deviceid" id="deviceid" value="publish.quickstart.juge.me"/></td></tr>
        <tr><td>Payload</td><td><textarea name="payload" id="payload" rows="5" cols="80">
{
 "d":{
  "message": "Hello! こんにちは！",
  "datetime": "<%= dt %>"
 }
}</textarea></td></tr>
        <tr><td colspan="2" align="right"><input type="submit" value="QuickStart Publish"/></td></tr>
      </table>
    </form>  
  </div>
  <!-- publish form// -->
</body>
</html>