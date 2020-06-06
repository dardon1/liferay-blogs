package nl.finalist.blog.templates.context.contributor;

import com.liferay.portal.kernel.template.TemplateContextContributor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import nl.finalist.blog.templates.api.ContentLocalService;

/**
 * @author danielle.ardon
 */
@Component(
	immediate = true,
	property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
	service = TemplateContextContributor.class
)
public class LiferayTemplateContextContributorTemplateContextContributor
	implements TemplateContextContributor {

	@Reference
	ContentLocalService contentLocalService;
	
	@Override
	public void prepare(
		Map<String, Object> contextObjects, HttpServletRequest request) {

		contextObjects.put("contentLocalService", contentLocalService);
	}

}