package cs5412.project.distributed_file_system.jsfbean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.pojo.File;

@Named
@Scope("request")
@URLMapping(id = "fileBrowser", pattern = "/file_browser/#{fileBrowserBean.url}", viewId = "/views/FileBrowser/FileBrowser.xhtml")
public class FileBrowserBean {
	private ArrayList<File> files;
	private File selectedItem;
	private String url;

	@PostConstruct
	private void init() {
		// -------------------- test purpose -----------------------
		this.files = new ArrayList<File>();
		this.files.add(new File("file1", false));
		this.files.add(new File("file2", false));
		this.files.add(new File("dir1", true));
		// ---------------------------------------------------------
	}

	public String getViewPath() {
		return "/views/FileBrowser/FileBrowser.xhtml";
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
