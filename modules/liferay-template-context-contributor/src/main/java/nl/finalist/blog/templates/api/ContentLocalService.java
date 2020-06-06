package nl.finalist.blog.templates.api;

import com.liferay.portal.kernel.xml.Document;

import java.util.Locale;

import javax.servlet.http.HttpServletRequestWrapper;

public interface ContentLocalService {
	public Document getDocumentFromRequest(HttpServletRequestWrapper request, Locale locale);
	public String getPreviewUrlFromFileEntryId(long fileEntryId);

}
