package nl.finalist.custom.my.account.screennavigator.category;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.users.admin.constants.UserScreenNavigationEntryConstants;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import nl.finalist.custom.my.account.screennavigator.constants.Constants;

@Component(property = "screen.navigation.category.order:Integer=30", service = ScreenNavigationCategory.class)
public class UserPreferencesScreenNavigationCategory implements ScreenNavigationCategory {

	@Override
	public String getCategoryKey() {
		return UserScreenNavigationEntryConstants.CATEGORY_KEY_PREFERENCES;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "preferences");
	}

	@Override
	public String getScreenNavigationKey() {
		return Constants.MY_ACCOUNT_PREFIX + UserScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_USERS;
	}

}
