package nl.finalist.custom.my.account.screennavigator.entry;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.model.User;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = "screen.navigation.entry.order:Integer=60", service = ScreenNavigationEntry.class)
public class UserPasswordScreenNavigationEntry extends BaseUserScreenNavigationEntry {

	@Override
	public String getActionCommandName() {
		return "/users_admin/update_password";
	}

	@Override
	public String getCategoryKey() {
		return UserScreenNavigationEntryConstants.CATEGORY_KEY_GENERAL;
	}

	@Override
	public String getEntryKey() {
		return UserScreenNavigationEntryConstants.ENTRY_KEY_PASSWORD;
	}

	@Override
	public String getJspPath() {
		return "/user/password.jsp";
	}

	@Override
	public boolean isVisible(User user, User selUser) {
		if (selUser == null) {
			return false;
		}

		return true;
	}

	@Override
	@Reference(unbind = "-")
	public void setJSPRenderer(JSPRenderer jspRenderer) {
		this.jspRenderer = jspRenderer;
	};

}