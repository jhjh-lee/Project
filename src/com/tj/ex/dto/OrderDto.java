package com.tj.ex.dto;

import java.sql.Date;

public class OrderDto {
	private String oNo;
	private String mId;
	private String oName;
	private String oPostalcode;
	private String oAddress;
	private String oTel;
	private String oEmail;
	private Date oRdate;
	public OrderDto() {
	}
	public OrderDto(String oNo, String mId, String oName, String oPostalcode, String oAddress, String oTel,
			String oEmail, Date oRdate) {
		super();
		this.oNo = oNo;
		this.mId = mId;
		this.oName = oName;
		this.oPostalcode = oPostalcode;
		this.oAddress = oAddress;
		this.oTel = oTel;
		this.oEmail = oEmail;
		this.oRdate = oRdate;
	}
	public String getoNo() {
		return oNo;
	}
	public void setoNo(String oNo) {
		this.oNo = oNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoPostalcode() {
		return oPostalcode;
	}
	public void setoPostalcode(String oPostalcode) {
		this.oPostalcode = oPostalcode;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public String getoTel() {
		return oTel;
	}
	public void setoTel(String oTel) {
		this.oTel = oTel;
	}
	public String getoEmail() {
		return oEmail;
	}
	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}
	public Date getoRdate() {
		return oRdate;
	}
	public void setoRdate(Date oRdate) {
		this.oRdate = oRdate;
	}
	@Override
	public String toString() {
		return "OrderDto [oNo=" + oNo + ", mId=" + mId + ", oName=" + oName + ", oPostalcode=" + oPostalcode
				+ ", oAddress=" + oAddress + ", oTel=" + oTel + ", oEmail=" + oEmail + ", oRdate=" + oRdate + "]";
	}

}
