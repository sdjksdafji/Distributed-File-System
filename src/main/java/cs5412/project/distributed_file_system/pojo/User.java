package cs5412.project.distributed_file_system.pojo;

public class User {
	private int uid;
	private String username;
	private String password;
	private String email;
	private int currentVersion;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(int currentVersion) {
		this.currentVersion = currentVersion;
	}
}
