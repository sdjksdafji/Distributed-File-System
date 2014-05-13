package cs5412.project.distributed_file_system.pojo;

import java.sql.Timestamp;

public class History {
	private int hid;
	private Timestamp ts;
	private int uid;
	private int oldFid;
	private int newFid;
	private int operationType;
	private String oldName = "";

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOldFid() {
		return oldFid;
	}

	public void setOldFid(int oldFid) {
		this.oldFid = oldFid;
	}

	public int getNewFid() {
		return newFid;
	}

	public void setNewFid(int newFid) {
		this.newFid = newFid;
	}

	public int getOperationType() {
		return operationType;
	}

	public String getOT() {
		if (operationType == 1) {
			return "create";
		}
		if (operationType == 2) {
			return "delete";
		}
		if (operationType == 3) {
			return "update";
		}
		return "";
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	@Override
	public String toString() {
		return "History [hid=" + hid + ", ts=" + ts + ", uid=" + uid
				+ ", oldFid=" + oldFid + ", newFid=" + newFid
				+ ", operationType=" + operationType + ", oldName=" + oldName
				+ "]";
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		History otherHis = (History) other;
		return this.hid == otherHis.getHid();
	}

}
