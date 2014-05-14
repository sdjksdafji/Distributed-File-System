package cs5412.project.distributed_file_system.jsfbean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.CookieService;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("fileBrowserBean")
@URLMapping(id = "fileBrowser", pattern = "/file_browser", viewId = "/views/FileBrowser/FileBrowser.xhtml")
public class FileBrowserBean {
	private List<File> files;
	private File selectedItem;// only fid is valid in selectedItem, other fields
								// are all default value
	private String branchName;

	private int userId;
	private int folderFid = -1;
	private int branchId = -1;

	public FileBrowserBean() {
		super();
		System.out.println("FileBrowserBean constructed");
	}

	@Inject
	private UserAccountService userAccountService;

	@Inject
	private FileDAO fileDao;

	@Inject
	private CookieService cookieService;

	@URLAction
	public void init() {
		checkLoginCookie();
		this.readInfoFromCookie();
		System.out.println("folder fid after read cookie: " + this.folderFid);
		System.out.println("branch fid after read cookie: " + this.branchId);
		if (this.folderFid < 0) {
			readBranchFileList();
		} else {
			File temp = new File();
			temp.setFid(this.folderFid);
			this.files = this.fileDao.getFileByParentDir(temp);
		}

	}

	public void openDir(ActionEvent actionEvent) {
		System.out.println("function openDir invoked");
		if (this.selectedItem != null) {
			System.out.println("selected:" + this.selectedItem);
			this.selectedItem = this.fileDao.getFileByFid(this.selectedItem
					.getFid());
			if (this.selectedItem.isDir()) {
				this.files = this.fileDao.getFileByParentDir(this.selectedItem);
				this.folderFid = this.selectedItem.getFid();
				System.out.println("read open folder");
				this.storeFidInCookie();
			}
		} else {
			System.out.println("selected nothing");
		}
	}

	public void parentFolderActionListener(ActionEvent actionEvent) {
		System.out.println("function parent folder invoked");
		System.out.println("folder fid: " + this.folderFid);
		if (folderFid >= 0) {
			System.out.println("folder fid: " + this.folderFid);
			File currentFolder = this.fileDao.getFileByFid(this.folderFid);
			if (currentFolder != null && currentFolder.isBranch() == false) {
				System.out.println("parent dir: "
						+ currentFolder.getParentDir());
				File parentFolder = this.fileDao.getFileByFid(currentFolder
						.getParentDir());
				if (parentFolder != null) {
					System.out.println("parent folder id: " + this.folderFid);
					this.files = this.fileDao.getFileByParentDir(parentFolder);
					System.out.println("read parent folder");
					this.folderFid = parentFolder.getFid();
					this.storeFidInCookie();
				}
			}
		}
	}

	public void createFolderActionListener(ActionEvent actionEvent) {
		System.out.println("function create folder invoked");
		if (folderFid >= 0) {
			RequestContext.getCurrentInstance().openDialog("inputFolderName");
		}
	}

	public void onFileNameEntered(SelectEvent event) {
		String folderName = (String) event.getObject();
		System.out.println("fid = " + folderFid);
		System.out.println(folderName);
		File folder = new File(folderName, true);
		folder.setUid(userId);
		folder.setParentDir(folderFid);
		int newfolderfid = fileDao.createFile(folder);
		System.out.println("new folder fid is " + newfolderfid);
		File parent = new File();
		parent.setFid(folderFid);
		File temp = new File();
		temp.setFid(this.folderFid);
		this.files = this.fileDao.getFileByParentDir(temp);
	}

	public void deleteActionListener(ActionEvent actionEvent) {
		System.out.println("deleteActionListener");
		if (this.selectedItem != null) {
			this.selectedItem = this.fileDao.getFileByFid(this.selectedItem
					.getFid());
			boolean isSuccess;
			if (selectedItem.isDir() == true) {
				isSuccess = this.fileDao.deleteDir(selectedItem);
			} else {
				isSuccess = this.fileDao.deleteFile(selectedItem);
			}
			File temp = new File();
			temp.setFid(this.folderFid);
			this.files = this.fileDao.getFileByParentDir(temp);
		}
	}

	public void downloadActionListener(ActionEvent actionEvent)
			throws IOException {
		System.out.println("downloadActionListener");
		if (this.selectedItem != null) {
			this.selectedItem = this.fileDao.getFileByFid(this.selectedItem
					.getFid());
			if (!this.selectedItem.isDir()) {
				System.out.println("\tselected fid="
						+ this.selectedItem.getFid());
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath() + "/sharing/"
						+ this.selectedItem.getFid());
			}
		}
	}

	public void shareListener(ActionEvent actionEvent) {
		if (this.selectedItem != null) {
			this.selectedItem = this.fileDao.getFileByFid(this.selectedItem
					.getFid());
			if (this.selectedItem.isDir())
				return;
			this.selectedItem.setPublic(true);
			this.fileDao.updateFile(this.selectedItem);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"You shared the file", "link is :/sharing/"
									+ this.selectedItem.getFid()));
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Sample info message", "PrimeFaces rocks!"));
	}

	public void uploadActionListener(ActionEvent actionEvent)
			throws IOException {
		System.out.println("uploadActionListener");
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.redirect(context.getRequestContextPath()
				+ "/views/FileBrowser/upload.xhtml");

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

	private void readBranchFileList() {
		this.files = null;
		if (this.userId >= 0) {
			File branch = this.fileDao.getFileByFid(branchId);
			if (branch != null) {
				this.files = this.fileDao.getFileByParentDir(branch);
				System.out.println("read branch folder");

			}
		}
		if (this.files == null) {
			this.files = new ArrayList<File>();
		}
	}

	private void storeFidInCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		this.cookieService.storeFolderFid(folderFid, response);
	}

	private void readInfoFromCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.folderFid = this.cookieService.getFolderFid(request);
		this.branchId = this.cookieService.getBranchFid(request);
		this.branchName = this.cookieService.getBranchName(request);
		if (this.folderFid == -1) {
			this.folderFid = this.branchId;
		}
	}

	public List<File> getFiles() {
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
