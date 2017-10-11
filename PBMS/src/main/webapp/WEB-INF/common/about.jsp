<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>
<body>
	<div id="main">
			<div id="table">
				<h5 class="toph5">我们的工作小组</h5>
				<div id="flash">
					<script type="text/javascript">
						AC_FL_RunContent(
								'codebase',
								'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0',
								'width',
								'600',
								'height',
								'400',
								'src',
								'images/~a',
								'quality',
								'high',
								'pluginspage',
								'http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash',
								'movie', 'images/~a'); //end AC code
					</script>
					<noscript>
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
							codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0"
							width="600" height="400">
							<param name="movie" value="images/~a.swf" />
							<param name="quality" value="high" />
							<embed src="images/~a.swf" quality="high"
								pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
								type="application/x-shockwave-flash" width="600" height="400"></embed>
						</object>
					</noscript>
				</div>
			</div>
		</div>
</body>
<jsp:include page="/footer.do" />
</html>