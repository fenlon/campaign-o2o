<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
<title>兑换页面</title>
<script type="text/javascript" src="${actx}/js/lib/WeixinJS-SDK.js"></script>
<script type="text/javascript">
	$(function() {
		// wechatConfig在jsp中通过el表达式获得
		var con;
		// 微信js相关配置
		var url = ctx + "/wechat/config_info.mbay";
		$.get(url, {
			url : window.location.href
		}, function(data) {
			con = data;
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : con.appId, // 必填，公众号的唯一标识
				timestamp : con.timestamp, // 必填，生成签名的时间戳
				nonceStr : con.nonceStr, // 必填，生成签名的随机串
				signature : con.signature,// 必填，签名，见附录1
				jsApiList : [ 'scanQRCode' ]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		});

		wx.ready(function() {
			wx.checkJsApi({
				jsApiList : [ 'scanQRCode' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				success : function(res) {
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					// alert("检测js接口,返回信息 -> " +
					// res.errMsg);
				}
			});
		});

		$("#wechatScan").click(function() {
			wx.scanQRCode({
				needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				scanType : [ "qrCode" ], // 可以指定扫二维码还是一维码，默认二者都有(现在去掉了条形码)
				success : function(res) {
					var result = res.resultStr; // 当needResult
					//alert(result);

					// 为 1
					// 时，扫码返回的结果
					//alert(result);
				}
			});
		});

		$("#redeem_btn").click(function() {
			var code = $("#redeem_code").val();
			var url = ctx + "/store_ope/redeem.mbay";
			$.post(url, {
				code : code
			}, function(result) {
				alert(result.message);
			});
		});
	});
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

ul,ol,li {
	list-style: none;
}

html,body {
	width: 100%;
	height: 100%;
}

.fl {
	float: left
}

.fr {
	float: right
}

.con {
	height: 100%;
	width: 100%;
	background: #fff;
	background-size: cover;
	overflow: hidden
}

.body_con {
	width: 80%;
	margin: 10%;
	height: 90%
}
</style>
</head>
<body>
	<section class='con'>
		<div class='body_con'>
			<div style='padding: 10%'>
				<div class='logo fl'
					style='width: 40%; height: 30px; background: gray;'>
					<img />
				</div>
				<div class='status fr'
					style='width: 40%; height: 30px; background: yellow'>Hi,${cellPhone }</div>
			</div>
			<div style='width: 75%; margin: 10%'>
				<input type='text' id="redeem_code" value="${code }"
					style='width: 100%; border: 1px solid #94ABC4; line-height: 20px; padding: 5px 5px; border-radius: 4px' />
			</div>
			<div style='width: 75%; margin: 10%; height: 40%'>
				<textarea
					style='width: 100%; border: 1px solid #94ABC4; line-height: 20px; height: 100%; padding: 5px 5px; border-radius: 4px; resize: none'>
					<c:if test="${!empty campaign }">
						活动名称:${campaign.name }---活动编号:${campaign.number }---活动价值${campaign.price }---活动连接${campaign.link }
					</c:if>
				</textarea>
			</div>
			<div style='width: 80%; margin: 0px auto;'>
				<button id="wechatScan" style='width: 100%; height: 30px;'>扫一扫二维码</button>
			</div>
			<div style='padding: 10%'>
				<div class='confirm fl'
					style='width: 40%; height: 30px; background: #000'>
					<button id="redeem_btn" style='width: 100%; height: 100%'>确定使用</button>
				</div>
				<div class='record fr'
					style='width: 40%; height: 30px; background: yellow'>
					<a href="${ctx }/store_ope/record.mbay?storeId=${storeId}"
						style='width: 100%; height: 100%'>使用记录</a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>