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
 */
%>

<%@include file="/html/init.jsp" %>
<%
	String redirect = ParamUtil.getString(request, "redirect");
	
	String pref_protocol = prefs.getValue("pref_protocol","http");
	String pref_server = prefs.getValue("pref_server","");
	String pref_port = prefs.getValue("pref_port","80");
	String pref_api_base = prefs.getValue("pref_api_base","bigbluebutton/api");

	String pref_salt = prefs.getValue("pref_salt","");
	
%>


<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" name="savePreferences"/>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:select name="pref_protocol" label="bbb.protocol">
		<aui:option value="http" selected='<%=pref_protocol.equals("http")%>' label="bbb.http"></aui:option>
		<aui:option value="https" selected='<%=pref_protocol.equals("https")%>' label="bbb.https"></aui:option>
	</aui:select>
	<aui:input name="pref_server" value="<%=pref_server%>" label="bbb.server"/>
	<aui:input name="pref_port" value="<%=pref_port%>" label="bbb.port"/>
	<aui:input name="pref_api_base" value="<%=pref_api_base%>" label="bbb.api_base"/>

	<aui:input name="pref_salt" value="<%=pref_salt%>" label="bbb.salt"/>
	
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>

</aui:form>
