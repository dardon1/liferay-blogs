<%@ include file="/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL");
User selUser = PortalUtil.getSelectedUser(request);
PortletURL portletURL = liferayPortletResponse.createRenderURL();
if (selUser != null) {
	portletURL.setParameter("p_u_i_d", String.valueOf(selUser.getUserId()));
}
portletURL.setParameter("mvcRenderCommandName", "/users_admin/edit_user");
if (Validator.isNotNull(backURL)) {
	portletURL.setParameter("backURL", backURL);
}

/* Begin override of My Account */
//initialize to the real form navigator id.
String formNavigatorId = UserScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_USERS;
//Only change the id if this jsp is used in my account portlet
if (portletName.equals(myAccountPortletId)) {
	// include the special prefix
	formNavigatorId = "my.account." + formNavigatorId;
}
/* End override of My Account */
%>


<liferay-frontend:screen-navigation
	containerCssClass="col-lg-8"
	containerWrapperCssClass="container-fluid container-fluid-max-xl container-form-lg"
	context="<%= selUser %>"
	headerContainerCssClass=""
	key="<%= formNavigatorId %>"
	menubarCssClass="menubar menubar-transparent menubar-vertical-expand-lg"
	navCssClass="col-lg-3"
	portletURL="<%= portletURL %>"
/>