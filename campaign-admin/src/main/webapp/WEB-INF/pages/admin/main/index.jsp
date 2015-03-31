<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理首页</title>

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var extjsPath = "http://222.22.91.35:8080/ext-4.2.1.883/";
</script>

<link
	href="${pageContext.request.contextPath }/resources/style/common/bootstrap3/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/admin/index.js"></script>
</style>
</head>
<body>
	<div class="container">
		<div>
			<table class="table">
				<tr>
					<td>aa</td>
				</tr>
			</table>
		</div>
	</div>

	<div>
		<div>
			<label>编号</label> <input name="number" type="text">
		</div>
		<div>
			<label>名称</label> <input name="name" type="text">
		</div>
		<div>
			<button>添加</button>
		</div>
	</div>
</body>
</html>