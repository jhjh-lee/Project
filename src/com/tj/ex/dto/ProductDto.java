package com.tj.ex.dto;

import java.sql.Date;

public class ProductDto {
	private String pCode;
	private String pName;
	private String pDes;
	private String pType;
	private String pFilename;
	private int pPrice;
	private int pStock;
	private Date pRdate;
	public ProductDto() {
	}
	public ProductDto(String pCode, String pName, String pDes, String pType, String pFilename, int pPrice, int pStock,
			Date pRdate) {
		this.pCode = pCode;
		this.pName = pName;
		this.pDes = pDes;
		this.pType = pType;
		this.pFilename = pFilename;
		this.pPrice = pPrice;
		this.pStock = pStock;
		this.pRdate = pRdate;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDes() {
		return pDes;
	}
	public void setpDes(String pDes) {
		this.pDes = pDes;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpFilename() {
		return pFilename;
	}
	public void setpFilename(String pFilename) {
		this.pFilename = pFilename;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpStock() {
		return pStock;
	}
	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	public Date getpRdate() {
		return pRdate;
	}
	public void setpRdate(Date pRdate) {
		this.pRdate = pRdate;
	}
	@Override
	public String toString() {
		return "ProductDto [pCode=" + pCode + ", pName=" + pName + ", pDes=" + pDes + ", pType=" + pType
				+ ", pFilename=" + pFilename + ", pPrice=" + pPrice + ", pStock=" + pStock + ", pRdate=" + pRdate + "]";
	}
}
