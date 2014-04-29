package cs5412.project.distributed_file_system.jsfbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("fileBrowserBean")
@URLMapping(id = "fileBrowser", pattern = "/file_browser/#{fileBrowserBean.dirPath}", viewId = "/views/FileBrowser/FileBrowser.xhtml")
public class FileBrowserBean {
	private List<File> files;
	private File selectedItem;
	private String dirPath;
	private int userId;

	@Inject
	private UserAccountService userAccountService;

	@Inject
	private FileDAO fileDao;

	@URLAction
	// @PostConstruct
	public void init() {
		System.out.println("init function called2");
		checkLoginCookie();
		readFileList();

	}

	public String openDir() {
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
		if (this.selectedItem != null) {
			System.out.println("selected:" + this.selectedItem);
		} else {
			System.out.println("selected nothing");
		}
		return null;
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
			if (this.dirPath.equals("home")) {
				File rootFolder = this.fileDao.getRootDirForUser(userId);
				this.files = this.fileDao.getFileByParentDir(rootFolder);
			} else {
				this.files = new ArrayList<File>();
			}
		} else {
			this.files = new ArrayList<File>();
		}
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public File getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(File selectedItem) {
		this.selectedItem = selectedItem;
	}

}
