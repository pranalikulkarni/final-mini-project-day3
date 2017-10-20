package com.cg.asset.service;

import java.util.ArrayList;

import com.cg.asset.dto.Asset;
import com.cg.asset.exception.AssetException;

public interface IAssetService {

	public String loginDetails(Asset b);
	public int getUserType(String string,Asset b);
	public int addDetails(Asset b);
	public int modifyAsset(String assetName1, int quantity1) ;
	public Asset raiseRequest(Asset bean) throws AssetException;
	public int insertRequests(Asset bean) throws AssetException;
	public int addAllocatedQuantity(Asset bean) throws AssetException;
	public int checkAsset(int assetId) throws AssetException;
	public int approveRequest(int allocationId) throws AssetException;
	public ArrayList<Asset> retrieveDetails() throws AssetException;
	public void setStatus(int allocId, String rejectStatus) throws AssetException;
	

}
