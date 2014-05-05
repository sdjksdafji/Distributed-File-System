package cs5412.project.distributed_file_system.jsfbean;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.pojo.File;

@Named
@Scope("request")
@URLMapping(id = "fileBrowser", pattern = "/branch", viewId = "/views/FileBrowser/SelectBranch.xhtml")
public class SelectBranchBean {
	private List<File> files;
	private File selectedItem;// only fid is valid in selectedItem, other fields
								// are all default value
	
	public String select(ActionEvent e){
		return "";
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public File getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(File selectedItem) {
		this.selectedItem = selectedItem;
	}

}
