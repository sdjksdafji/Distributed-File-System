package cs5412.project.distributed_file_system.jsfbean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLMapping(id = "fileBrowser", pattern = "/file_browser/#{fileBrowserBean.url}", viewId = "/views/FileBrowser/FileBrowser.xhtml")
public class FileBrowserBean {
	private ArrayList<File> files;
	private File selectedItem;
	private String url;
	private int userId;

	@Inject
	private UserAccountService userAccountService;
	
	@Inject
	private FileDAO fileDao;

	@PostConstruct
	private void init() {
		checkLoginCookie();
		readFileList();

	}

	public String getViewPath() {
		return "/views/FileBrowser/FileBrowser.xhtml";
	}

	private void checkLoginCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.userId = this.userAccountService.getUidFromCookie(request);
	}

	private void readFileList() {
		if (this.userId >= 0) {
			this.fileDao
		} else {
			this.files = new ArrayList<File>();

			// -------------------- test purpose -----------------------
			this.files.add(new File("file1", false));
			this.files.add(new File("file2", false));
			this.files.add(new File("dir1", true));
			// ---------------------------------------------------------
		}
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

	public File getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(File selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
