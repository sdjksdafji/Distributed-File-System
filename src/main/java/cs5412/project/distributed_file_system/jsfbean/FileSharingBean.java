package cs5412.project.distributed_file_system.jsfbean;

import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;

@Named
@Scope("request")
@URLBeanName("fileSharingBean")
@URLMapping(id = "fileSharing", pattern = "/sharing/#{fileSharingBean.urlFileId}", viewId = "/views/FileBrowser/FileSharing.xhtml")
public class FileSharingBean {

	private String urlFileId;
	private File file = null;

	private boolean isDownloadable;
	private StreamedContent fileStream;

	@Inject
	private FileDAO fileDao;

	public FileSharingBean() {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("https://s3.amazonaws.com/edu-cornell-cs-cs5412-project/DxDiag.txt");
		fileStream = new DefaultStreamedContent(stream, "image/jpg",
				"downloaded.txt");
	}

	@URLAction
	public void init() {
		int fid = -1;
		try {
			fid = Integer.parseInt(urlFileId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("the shared fid is " + fid);
		this.isDownloadable = this.fileDao.isFilePublic(fid);
		if (this.isDownloadable) {
			this.file = this.fileDao.getFileByFid(fid);
		}
	}

	public StreamedContent getFileStream() {
		return fileStream;
	}

	public String getUrlFileId() {
		return urlFileId;
	}

	public void setUrlFileId(String urlFileId) {
		this.urlFileId = urlFileId;
	}

	public boolean getIsDownloadable() {
		return isDownloadable;
	}

	public void setIsDownloadable(boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}

	public String getFileName() {
		if (this.file != null) {
			return file.getName();
		} else {
			return "File not existed or not downloadable";
		}
	}

	public void setFileName(String file) {

	}
}
