package com.cg.asset.dto;

import java.util.Date;

public class Asset {
	
	@Override
	public String toString() {
		return "Asset [allocationId=" + allocationId + ", assetId=" + assetId
				+ ", empNo=" + empNo + ", employeeName=" + employeeName
				+ ", allocationDate=" + allocationDate + ", releaseDate="
				+ releaseDate + ", assetName=" + assetName + ", assetDes="
				+ assetDes + ", quantity=" + quantity + ", allocatedQuantity="
				+ allocatedQuantity + ", userName=" + userName + ", password="
				+ password + "]";
	}

	private int allocationId;
	private int assetId;
	private int empNo;
	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	private String employeeName;
	private Date allocationDate;
	private Date releaseDate;
	private String assetName;
	private String assetDes;
	private int quantity;
	private int allocatedQuantity;
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	private String userName;
	private String password;
	
	
	


	public int getAllocatedQuantity() {
		return allocatedQuantity;
	}


	public void setAllocatedQuantity(int allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}


	public Asset(int assetId, int empNo,int allocatedQuantity) {
		super();

		this.assetId = assetId;
		this.empNo = empNo;
		this.allocatedQuantity=allocatedQuantity;
	}


	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public Asset(int allocationId, int assetId, int empNo, String assetName, String assetDes, int quantity) {
		super();
		this.allocationId = allocationId;
		this.assetId = assetId;
		this.empNo = empNo;
	
		this.assetName = assetName;
		this.assetDes = assetDes;
		this.quantity = quantity;
	
	}


	public Asset() {
		
	}


	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetDes() {
		return assetDes;
	}

	public void setAssetDes(String assetDes) {
		this.assetDes = assetDes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

}
