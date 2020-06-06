package nl.finalist.blog.templates.service;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReader;

import java.lang.invoke.MethodHandles;
import java.util.Locale;

import javax.servlet.http.HttpServletRequestWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import nl.finalist.blog.templates.api.ContentLocalService;

@Component(immediate = true, service = ContentLocalService.class)
public class ContentLocalServiceImpl implements ContentLocalService{
	private static final Log LOG = LogFactoryUtil.getLog(MethodHandles.lookup().lookupClass());

	@Reference
	JournalArticleLocalService journalArticleLocalService;
	
	@Reference
	DLAppLocalService dLAppLocalService;
	
	@Reference
	SAXReader saxReader;

	@Override
	public Document getDocumentFromRequest(HttpServletRequestWrapper request, Locale locale) {
		Document document = null;
		AssetEntry entry = (AssetEntry) request.getAttribute(WebKeys.LAYOUT_ASSET_ENTRY);
		if(Validator.isNotNull(entry)) {
			JournalArticle article = getArticleFromClassPK(entry.getClassPK());
			String content = article.getContentByLocale(locale.getLanguage());
			try {
				document = saxReader.read(content);
			} catch (DocumentException e) {
				LOG.error("DocumentException while reading article content " + e);
			}
		}
		return document;
	}
	@Override
	public String getPreviewUrlFromFileEntryId(long fileEntryId) {
		String url = "";
		FileEntry fileEntry = getFileEntry(fileEntryId);
		if (Validator.isNotNull(fileEntry)) {
			try {
				url = DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getLatestFileVersion(), null, "");
			} catch (PortalException e) {
				LOG.error("PortalException while retrieving image url " + fileEntryId + " " + e);
				return "";
			}
		}
		return url;
	}
	
	private JournalArticle getArticleFromClassPK(long classPK) {
		return journalArticleLocalService.fetchLatestArticle(classPK, WorkflowConstants.STATUS_APPROVED);
	}
	
	private FileEntry getFileEntry(long fileEntryId) {
		FileEntry fileEntry = null;
		if (Validator.isNotNull(fileEntryId)) {
			try {
				fileEntry = dLAppLocalService.getFileEntry(fileEntryId);
			} catch (PortalException e) {
				LOG.error("Exception while retrieving fileEntry " + fileEntryId);
			}
		}
		return fileEntry;
	}
	
}
