<%@include file="/html/init.jsp"%>
<% 
	String roomURL = (String)request.getAttribute("roomURL");

%>
<div id="container">
<iframe src="<%=roomURL%>" width="100%" height="700">
  <p>Your browser does not support iframes.</p>
</iframe>
</div>