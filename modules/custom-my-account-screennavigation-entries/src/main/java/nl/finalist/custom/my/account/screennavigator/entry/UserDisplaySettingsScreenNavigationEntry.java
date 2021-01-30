package nl.finalist.custom.my.account.screennavigator.entry;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.model.User;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = "screen.navigation.entry.order:Integer=30", service = ScreenNavigationEntry.class)
public class UserDisplaySettingsScreenNavigationEntry extends BaseUserScreenNavigationEntry {

	@Override
	public String getActionCommandName() {
		return "/users_admin/edit_display_settings";
	}

	@Override
	public String getCategoryKey() {
		return UserScreenNavigationEntryConstants.CATEGORY_KEY_PREFERENCES;
	}

	@Override
	public String getEntryKey() {
		return UserScreenNavigationEntryConstants.ENTRY_KEY_DISPLAY_SETTINGS;
	}

	@Override
	public String getJspPath() {
		return "/user/display_settings.jsp";
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
