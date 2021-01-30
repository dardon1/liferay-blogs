package nl.finalist.custom.my.account.screennavigator.entry;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;
import com.liferay.users.admin.web.internal.constants.UsersAdminWebKeys;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Reference;

import nl.finalist.custom.my.account.screennavigator.constants.Constants;

public abstract class BaseUserScreenNavigationEntry implements ScreenNavigationEntry<User> {

	JSPRenderer jspRenderer;

	@Reference
	protected Portal portal;

	public abstract String getActionCommandName();

	public abstract String getJspPath();

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(getResourceBundle(locale), getEntryKey());
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, getClass());

		return new AggregateResourceBundle(resourceBundle, PortalUtil.getResourceBundle(locale));
	}

	@Override
	public String getScreenNavigationKey() {
		return Constants.MY_ACCOUNT_PREFIX + UserScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_USERS;
	}

	public boolean isEditable(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		return true;
	}

	public boolean isShowControls() {
		return true;
	}

	public boolean isShowTitle() {
		return true;
	};

	@Override
	public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {

		if (getJspPath() == null) {
			return;
		}

		httpServletRequest.setAttribute(UsersAdminWebKeys.ACTION_COMMAND_NAME, getActionCommandName());
		httpServletRequest.setAttribute(UsersAdminWebKeys.EDITABLE,
				isEditable(httpServletRequest, httpServletResponse));
		httpServletRequest.setAttribute(UsersAdminWebKeys.FORM_LABEL, getLabel(httpServletRequest.getLocale()));
		httpServletRequest.setAttribute(UsersAdminWebKeys.JSP_PATH, getJspPath());
		httpServletRequest.setAttribute(UsersAdminWebKeys.SHOW_CONTROLS, isShowControls());
		httpServletRequest.setAttribute(UsersAdminWebKeys.SHOW_TITLE, isShowTitle());
		jspRenderer.renderJSP(httpServletRequest, httpServletResponse, "/edit_user_navigation.jsp");
	}

	/*
	 * Always override when extending this class, otherwise jsp renderer is null in
	 * the render method
	 */
	@Reference(unbind = "-")
	public void setJSPRenderer(JSPRenderer jspRenderer) {
		this.jspRenderer = jspRenderer;
	}
}
