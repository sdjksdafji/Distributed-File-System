package cs5412.project.distributed_file_system.pojo;

public class User {
	private int uid;
	private String username;
	private String password;
	private String email;
	private int rootfid;

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

	public String getHashedPassword() {
		return password;
	}

	public void setHashedPassword(String password) {
		this.password = password;
	}

	public void setUnhashedPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRootfid() {
		return rootfid;
	}

	public void setRootfid(int rootfid) {
		this.rootfid = rootfid;
	}

}
