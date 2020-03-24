package com.tj.ex.dto;

import java.sql.Date;

public class QnADto {
	private int qNo;
	private String aId;
	private String mId;
	private String qTitle;
	private String qContent;
	private String qFilename;
	private int qGroup;
	private int qStep;
	private int qIndent;
	private int qHit;
	private Date qRdate;
	public QnADto() {
	}
	public QnADto(int qNo, String aId, String mId, String qTitle, String qContent, String qFilename, int qGroup,
			int qStep, int qIndent, int qHit, Date qRdate) {
		this.qNo = qNo;
		this.aId = aId;
		this.mId = mId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qFilename = qFilename;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qHit = qHit;
		this.qRdate = qRdate;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getqFilename() {
		return qFilename;
	}
	public void setqFilename(String qFilename) {
		this.qFilename = qFilename;
	}
	public int getqGroup() {
		return qGroup;
	}
	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}
	public int getqStep() {
		return qStep;
	}
	public void setqStep(int qStep) {
		this.qStep = qStep;
	}
	public int getqIndent() {
		return qIndent;
	}
	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}
	public int getqHit() {
		return qHit;
	}
	public void setqHit(int qHit) {
		this.qHit = qHit;
	}
	public Date getqRdate() {
		return qRdate;
	}
	public void setqRdate(Date qRdate) {
		this.qRdate = qRdate;
	}
	@Override
	public String toString() {
		return "QnADto [qNo=" + qNo + ", aId=" + aId + ", mId=" + mId + ", qTitle=" + qTitle + ", qContent=" + qContent
				+ ", qFilename=" + qFilename + ", qGroup=" + qGroup + ", qStep=" + qStep + ", qIndent=" + qIndent
				+ ", qHit=" + qHit + ", qRdate=" + qRdate + "]";
	}
}
