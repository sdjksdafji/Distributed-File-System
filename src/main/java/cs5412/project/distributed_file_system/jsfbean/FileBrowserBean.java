package cs5412.project.distributed_file_system.jsfbean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import cs5412.project.distributed_file_system.pojo.File;

@Named
@Scope("request")
public class FileBrowserBean {
	private ArrayList<File> files;
	private File selectedItem;

	@PostConstruct
	private void init() {
		// -------------------- test purpose -----------------------
		this.files = new ArrayList<File>();
		this.files.add(new File("file1",false));
		this.files.add(new File("file2",false));
		this.files.add(new File("dir1",true));
		// ---------------------------------------------------------
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

}
