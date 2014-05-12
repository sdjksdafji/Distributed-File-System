package cs5412.project.distributed_file_system.jsfbean;

//import java.io.IOException;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//
//import org.apache.commons.io.FilenameUtils;
//import org.apache.myfaces.custom.fileupload.UploadedFile;
//
//@ManagedBean
//@RequestScoped
//public class FileUploadBean {
//
//    private UploadedFile uploadedFile;
//
//    public void submit() throws IOException {
//    	System.out.println("submit");
//        String fileName = FilenameUtils.getName(uploadedFile.getName());
//        String contentType = uploadedFile.getContentType();
//        byte[] bytes = uploadedFile.getBytes();
//
//        // Now you can save bytes in DB (and also content type?)
//
//        FacesContext.getCurrentInstance().addMessage(null, 
//            new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
//    }
//
//    public UploadedFile getUploadedFile() {
//        return uploadedFile;
//    }
//
//    public void setUploadedFile(UploadedFile uploadedFile) {
//        this.uploadedFile = uploadedFile;
//    }
//
//}

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.CookieService;
import cs5412.project.distributed_file_system.service.HdfsFileService;
import cs5412.project.distributed_file_system.service.UserAccountService;

@ManagedBean
@RequestScoped
@Named
public class FileUploadBean {

	@Inject
	private HdfsFileService hdfsFileService;

	@Inject
	private UserAccountService userAccountService;

	@Inject
	private FileDAO fileDao;

	@Inject
	private CookieService cookieService;

	private int userId;
	private int folderFid = -1;
	private int branchId = -1;

	public void handleFileUpload(FileUploadEvent event) {

		System.out.println("handleFileUpload");
		checkLoginCookie();
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		String location = "";
		try {
			location = hdfsFileService.setFile(event.getFile().getFileName(),
					event.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (location.length() > 0) {
			File f = new File();
			f.setHash(location);
			f.setLocation(location);
			f.setName(event.getFile().getFileName());
			f.setParentDir(folderFid);
			f.setUid(userId);
			fileDao.createFile(f);
		}
	}

	private void checkLoginCookie() {
		System.out.println("checkLoginCookie");
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		if (request == null) {
			System.out.println("request is null");
		}
		if (cookieService == null) {
			System.out.println("cookieService");
		}
		this.userId = 2;
		this.folderFid = this.cookieService.getFolderFid(request);
		this.branchId = this.cookieService.getBranchFid(request);
		if (this.folderFid == -1) {
			this.folderFid = this.branchId;
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {

			String destination = "C:\\tmp\\";
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new java.io.File(
					destination + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
