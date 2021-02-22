package nl.finalist.layout.model.pre.filter.contributor;

import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author danielle.ardon
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.portal.kernel.model.Layout",
	service = ModelPreFilterContributor.class
)
public class PortletLayoutModelPreFilterContributor
	implements ModelPreFilterContributor {
	
	/**
	 * Overrides LayoutModelPreFilterContributor to stop portlet pages from being filtered out
	 * @param booleanFilter
	 * @param modelSearchSettings
	 * @param searchContext
	 */
	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		List<BooleanClause<Filter>> filters = booleanFilter.getMustBooleanClauses();
		for(BooleanClause<Filter> filter: filters) {
			if(filter.getClause().getClass().equals(TermsFilter.class)) {
				TermsFilter termsFilter = (TermsFilter)filter.getClause();
				if(termsFilter.getValues().length > 0 && termsFilter.getValues()[0].equalsIgnoreCase(LayoutConstants.TYPE_CONTENT)) {
					termsFilter.addValues(new String[] {LayoutConstants.TYPE_CONTENT, LayoutConstants.TYPE_PORTLET});
				}
			}
		}
	}
}