package cs5412.project.distributed_file_system.pojo;

public class File {
	private int fid;
	private String name;
	private String location;
	private int parentDir;
	private String hash;
	private int referenceCount;
	private int uid;
	private boolean isDir;
	private boolean hidden;

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

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uId) {
		this.uid = uId;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int getParentDir() {
		return parentDir;
	}

	public void setParentDir(int parentDir) {
		this.parentDir = parentDir;
	}

	public int getReferenceCount() {
		return referenceCount;
	}

	public void setReferenceCount(int referenceCount) {
		this.referenceCount = referenceCount;
	}

	@Override
	public String toString() {
		return "File [fid=" + fid + ", name=" + name + ", location=" + location
				+ ", parentDir=" + parentDir + ", hash=" + hash
				+ ", referenceCount=" + referenceCount + ", uid=" + uid
				+ ", isDir=" + isDir + ", hidden=" + hidden + "]";
	}
}
