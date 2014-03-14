package cs5412.project.distributed_file_system.pojo;

public class File {
	private String name;
	private boolean isDir;
	

	public File() {
		super();
	}

	public File(String name, boolean isDir) {
		super();
		this.name = name;
		this.isDir = isDir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}
	
	public String getType(){
		return isDir?"Folder":"File";
	}
	
	public void setType(){
		
	}
}
