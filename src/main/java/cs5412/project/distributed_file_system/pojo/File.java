package cs5412.project.distributed_file_system.pojo;

public class File {
	private String name;
	private boolean isDir;

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
}
