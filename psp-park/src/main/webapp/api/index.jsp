<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>
    <title>公共服务平台2.0 园区端文档</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- 引入 jsonVIEW style-->
    <link rel="stylesheet" href="css/jquery.jsonview.min.css" />

    <style>
        .beantbl tr> th,
        .beantbl tr> td {
            height: 30px;
            line-height: 30px;
            text-align: center;
            border: 1px solid #ccc;
        }
        .protocoltbl > tbody > tr > td{
            height: 30px;
            line-height: 30px;
            vertical-align: middle;
            border: 1px solid #ccc;
        }
        .protocoltbl > tbody > tr > td > table tr td,.protocoltbl > tbody > tr > td > table tr th{
            height: 30px;
            line-height: 30px;
            border-right: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
        }
        .ajaxbtn{
            display: inline-block;
            height:23px;
            line-height: 23px;
            padding:0 9px;
            background-color:#1889c8;
            color:#fff;
            margin-left:10px;
            cursor: pointer;
        }
        #protocols-menu > h3,#protocols-div > h3 {
            font-size:14px;
            font-weight: 600;

        }
        #protocols-div > h3 {
            margin:10px;
            margin-bottom: 0;
            padding-left: 30px;
        }
        .json-collapsed{
          display: none;
          margin:10px;
          padding:10px;
          border:1px solid #555;
          height:150px;
          overflow-y:auto;
        }
       	#protocols-menu h3{
			line-height:30px;  
			margin-top:10px;
       	}
       	#protocols-menu ul > li{
       		line-height:25px;
       	}
       	
       	#protocols-menu a,#beans-menu a{
       		color:#555;
       	}
   		#protocols-menu a:hover,#beans-menu a:hover{
   			color:#00e;
   		}
    </style>
</head>

<body>
    <div class="containter">
            <div class="col3 bdc ova mt10" style="width:300px; position: fixed; left:20px; top :0px; bottom:0px; overflow: auto;">
                <h3 class="f16">目录</h3>
                <div class="mb20">
                    <h3 class="f14">-protocols</h3>
                    <div class="pl20" id="protocols-menu"></div>
                </div>
                <div class="mb20">
                    <h3 class="f14">-Beans</h3>
                    <div class="pl20" id="beans-menu"></div>
                </div>

            </div>
            <div class="last" style="position: fixed; left:350px; top :0px; right:20px; bottom:0px; overflow: auto;">
                <div class="bdc mt10 mb10">
                    <h3 class="pl20 h40 lh40 bgf0 f16 bbc ">protocol</h3>
                    <div id="protocols-div" class="pct100">
                    </div>
                </div>
                <div class="bdc mt10 mb10">
                    <h3 class="pl20 h40 lh40 bgf0 f16 bbc ">beans</h3>
                    <div id="beans-div" class="pct100">
                    </div>
                </div>
            </div>
    </div>
    <script type=text/javascript src="js/jquery2.2.1.js"></script>
    <script type=text/javascript src="js/main.js"></script>
    <!-- json2.js -->
    <script type=text/javascript src="js/json2.js"></script>
    <!-- 引入 jsonVIEW js-->
    <script type="text/javascript" src="js/jquery.jsonview.min.js"></script>
</body>

</html>
