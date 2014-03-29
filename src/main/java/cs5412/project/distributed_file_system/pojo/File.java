package cs5412.project.distributed_file_system.pojo;

public class File {
	private int fid;
	private String name;
	private boolean isDir;
	private String location;
	private String parentDir;
	private String hash;
	private int version;
	private int uid;

	public File() {
		super();
	}

	public File(String name, boolean isDir) {
		super();
		this.name = name;
		this.isDir = isDir;
	}

	public String getType() {
		return isDir ? "Folder" : "File";
	}

	public void setType() {

	}

	public int getfId() {
		return fid;
	}

	public void setfId(int fId) {
		this.fid = fId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getParentDir() {
		return parentDir;
	}

	public void setParentDir(String parentDir) {
		this.parentDir = parentDir;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getuId() {
		return uid;
	}

	public void setuId(int uId) {
		this.uid = uId;
	}
}
