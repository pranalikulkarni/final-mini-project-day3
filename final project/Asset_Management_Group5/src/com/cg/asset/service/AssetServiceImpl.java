package com.cg.asset.service;

import java.util.ArrayList;

import com.cg.asset.dao.AssetDaoImpl;
import com.cg.asset.dao.IAssetDao;
import com.cg.asset.dto.Asset;
import com.cg.asset.exception.AssetException;

public class AssetServiceImpl implements IAssetService {
	IAssetDao dao = null;
	
	@Override
	public String loginDetails(Asset b) {
		dao = new AssetDaoImpl();
		return dao.loginDetails(b);
	}

	@Override
	public int getUserType(String string, Asset b) {
		dao = new AssetDaoImpl();
		return dao.getUserType(string,b);
	}

	@Override
	public int addDetails(Asset b) {
		dao = new AssetDaoImpl();
		return dao.addDetails(b);
	}

	@Override
	public int modifyAsset(String assetName1, int quantity1) {
		dao = new AssetDaoImpl();
		return dao.modifyAsset(assetName1,quantity1);
	}
	

	@Override
	public Asset raiseRequest(Asset bean) throws AssetException {
		dao = new AssetDaoImpl();
		return dao.raiseRequest(bean);
	}

	@Override
	public int insertRequests(Asset bean) throws AssetException {
		dao = new AssetDaoImpl();
		return dao.insertRequests(bean);
	}

	@Override
	public int addAllocatedQuantity(Asset bean) throws AssetException {
		dao = new AssetDaoImpl();
		return dao.addAllocatedQuantity(bean);
	}

	@Override
	public int checkAsset(int assetId) throws AssetException {
		dao = new AssetDaoImpl();
		return dao.checkAsset(assetId);
	}

	@Override
	public int approveRequest( int allocationId)
			throws AssetException {
		dao = new AssetDaoImpl();
		return dao.approveRequest( allocationId);
	}

	@Override
	public ArrayList<Asset> retrieveDetails() throws AssetException {
		dao = new AssetDaoImpl();
		return dao.retrieveDetails();
	}

	@Override
	public void setStatus(int allocId, String rejectStatus) throws AssetException {
		dao = new AssetDaoImpl();
		 dao.setStatus(allocId, rejectStatus);
	}


	
	

}
