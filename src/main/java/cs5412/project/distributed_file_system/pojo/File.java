package cs5412.project.distributed_file_system.pojo;

public class File {
	private static final String FOLDER_ICON = "https://s3.amazonaws.com/edu-cornell-cs-cs5412-project/Folder-icon.png";
	private static final String FILE_ICON = "https://s3.amazonaws.com/edu-cornell-cs-cs5412-project/File-icon.png";
	private int fid;
	private String name;
	private String location = "";
	private int parentDir = -1;
	private String hash = "";
	private int referenceCount = 0;
	private int uid;
	private boolean isDir;
	private boolean hidden = false;
	private boolean isBranch = false;
	private boolean isPublic = false;

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

	public String getIcon() {
		return isDir ? FOLDER_ICON : FILE_ICON;
	}

	public void setIcon() {

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

	public boolean isBranch() {
		return isBranch;
	}

	public void setBranch(boolean isBranch) {
		this.isBranch = isBranch;
	}

	@Override
	public String toString() {
		return "File [fid=" + fid + ", name=" + name + ", location=" + location
				+ ", parentDir=" + parentDir + ", hash=" + hash
				+ ", referenceCount=" + referenceCount + ", uid=" + uid
				+ ", isDir=" + isDir + ", hidden=" + hidden + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		File otherFile = (File) other;
		return this.fid == otherFile.getFid();
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;

	}

	@Override
	public File clone() {
		File ret = new File();
		ret.fid = this.fid;
		ret.name = this.name;
		ret.location = this.location;
		ret.parentDir = this.parentDir;
		ret.hash = this.hash;
		ret.referenceCount = this.referenceCount;
		ret.uid = this.uid;
		ret.isDir = this.isDir;
		ret.hidden = this.hidden;
		ret.isBranch = this.isBranch;
		ret.isPublic = this.isPublic;
		return ret;
	}
}
