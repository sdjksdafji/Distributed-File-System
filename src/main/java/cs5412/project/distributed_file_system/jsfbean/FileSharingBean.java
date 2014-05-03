package cs5412.project.distributed_file_system.jsfbean;

import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

@Named
@Scope("request")
@URLBeanName("fileSharingBean")
@URLMapping(id = "fileSharing", pattern = "/sharing/#{fileSharingBean.urlFileId}", viewId = "/views/FileBrowser/FileBrowser.xhtml")
public class FileSharingBean {

	private String urlFileId;

	private StreamedContent file;

	public FileSharingBean() {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/images/optimusprime.jpg");
		file = new DefaultStreamedContent(stream, "image/jpg",
				"downloaded_optimus.jpg");
	}
	
	@URLAction
	public void init() {
		
	}

	public StreamedContent getFile() {
		return file;
	}

	public String getUrlFileId() {
		return urlFileId;
	}

	public void setUrlFileId(String urlFileId) {
		this.urlFileId = urlFileId;
	}
}
