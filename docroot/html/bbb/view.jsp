<%
/**
 *  bbbClient : BigBlueButton Liferay portal integration portlet
 *  Copyright (C) 2011  Aritz Galdos Otermin, Sareweb
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: aritz@sareweb.net
 *  twitter: @aritzg
 */
%>

<%@include file="/html/init.jsp"%>
<% 
	String roomURL = (String)request.getAttribute("roomURL");
%>
<div id="container">
<iframe src="<%=roomURL%>" width="100%" height="700">
  <p>Your browser does not support iframes.</p>
</iframe>
</div>