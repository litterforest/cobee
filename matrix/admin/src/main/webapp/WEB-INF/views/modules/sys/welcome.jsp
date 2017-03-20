<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>jqGrid - Ace Admin</title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/ui.jqgrid.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${ctx}/static/plugin/ace/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${ctx}/static/plugin/ace/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="page-header">
							<h1>
								欢迎使用Cobee后台管理系统
							</h1>
						</div><!-- /.page-header -->

					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${ctx}/static/plugin/ace/assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script src="assets/js/jquery-1.11.3.min.js"></script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/plugin/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/static/plugin/ace/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="${ctx}/static/plugin/ace/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/static/plugin/ace/assets/js/jquery.jqGrid.min.js"></script>
		<script src="${ctx}/static/plugin/ace/assets/js/grid.locale-en.js"></script>

		<!-- ace scripts -->
		<script src="${ctx}/static/plugin/ace/assets/js/ace-elements.min.js"></script>
		<script src="${ctx}/static/plugin/ace/assets/js/ace.min.js"></script>

	</body>
</html>
