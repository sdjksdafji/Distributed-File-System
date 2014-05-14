package cs5412.project.distributed_file_system.jsfbean;

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

import org.primefaces.context.RequestContext;
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
@URLBeanName("selectBranchBean")
@URLMapping(id = "selectBranch", pattern = "/branch", viewId = "/views/FileBrowser/SelectBranch.xhtml")
public class SelectBranchBean {
	private List<File> branches = null;
	private File selectedBranch;// only fid is valid in selectedItem, other
								// fields
								// are all default value
	private int userId;

	@Inject
	private CookieService cookieService;
	@Inject
	private FileDAO fileDao;
	@Inject
	private UserAccountService userAccountService;

	@URLAction
	public void init() {
		checkLoginCookie();
		readBranchListForUser();
		System.out.println(this.branches.size());
	}

	private void checkLoginCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.userId = this.userAccountService.getUidFromCookie(request);
		System.out.println("userid: " + this.userId);
	}

	private void readBranchListForUser() {
		System.out.println("userid: " + this.userId);
		if (this.userId >= 0) {
			this.branches = this.fileDao.getBranchesForUser(userId);
		}
	}

	public String select() {
		if (this.selectedBranch != null) {
			this.selectedBranch = this.fileDao.getFileByFid(this.selectedBranch
					.getFid());
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context
					.getResponse();
			this.cookieService.storeBranchInfo(this.selectedBranch.getFid(),
					this.selectedBranch.getName(), response);
			this.cookieService.storeFolderFid(this.selectedBranch.getFid(),
					response);
			return "pretty:fileBrowser";
		} else {
			return null;
		}

	}

	public void deleteActionListener(ActionEvent actionEvent) {
		if (this.selectedBranch != null) {
			this.selectedBranch = this.fileDao.getFileByFid(this.selectedBranch
					.getFid());
			this.fileDao.deleteFile(selectedBranch);
			readBranchListForUser();
		}
	}

	public void forkListener(ActionEvent actionEvent) {

		if (this.selectedBranch != null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context
					.getResponse();
			this.cookieService.storeBranchInfo(this.selectedBranch.getFid(),
					"Not Valid", response);
			RequestContext.getCurrentInstance().openDialog("inputFolderName");
		}
	}

	public void onForkNameEntered(SelectEvent event) {
		String newBranchName = (String) event.getObject();
		File branch = this.fileDao.forkBrank(
				this.fileDao.getFileByFid(this.readFidOfSourceBranch()),
				newBranchName);
		readBranchListForUser();
	}

	public void merge() {
		if (this.selectedBranch != null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context
					.getResponse();
			this.cookieService.storeBranchInfo(this.selectedBranch.getFid(),
					"Not Valid", response);
			RequestContext.getCurrentInstance().openDialog(
					"selectMergeDestination");
		}
	}

	public void onMergingDestinationChosen(SelectEvent event) {
		File srcBranch = this.fileDao
				.getFileByFid(this.readFidOfSourceBranch());
		File dstBranch = (File) event.getObject();
		FacesMessage message = null;
		if (this.fileDao.mergeBranch(srcBranch, dstBranch)) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Branch Merged Successfully", "\"" + srcBranch.getName()
							+ "\" merged into \"" + dstBranch.getName() + "\"");
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Branch Merging Failed", "Unknown Error occured");
		}
		readBranchListForUser();
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private int readFidOfSourceBranch() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		return this.cookieService.getBranchFid(request);
	}

	public List<File> getBranches() {
		if (this.branches != null) {
			return branches;
		} else {
			return new ArrayList<File>();
		}
	}

	public void setBranches(List<File> branches) {
		this.branches = branches;
	}

	public File getSelectedBranch() {
		return selectedBranch;
	}

	public void setSelectedBranch(File selectedBranch) {
		this.selectedBranch = selectedBranch;
	}

}
