package com.cg.asset.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.cg.asset.dbutil.DbUtil;
import com.cg.asset.dto.Asset;
import com.cg.asset.exception.AssetException;



public  class AssetDaoImpl implements IAssetDao {
	Connection conn = null;
	
	
	
	public String loginDetails(Asset b)
	{
		
		String message=null;
		int c=0;
		
		try {
			conn=DbUtil.getConnections();
			String sql="Select userName,UserPassword from User_Master";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				String name=rs.getString(1);
				String pass=rs.getString(2);
				
				
				if((name.equals(b.getUserName())&& (pass.equals(b.getPassword()))))
						{
					c=1;
						}
				
				
			}
			if(c==1)
			{
				message="Login Successfull!!!";
				
			}
			else
			{
				message="Invalid UserName or Password";
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return message;
		
	}

	public int getUserType(String string,Asset b) {
		 int a=0;
		try {
			conn=DbUtil.getConnections();
			String sql="select * from User_Master";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				String usertype=rs.getString(4);
				String name=rs.getString(2);
				String pass=rs.getString(3);
				
				if(usertype.equalsIgnoreCase(string) && name.equals(b.getUserName())&& pass.equals(b.getPassword()))
				{
					a=1;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return a;
	}



	public int addDetails(Asset b) {
		// TODO Auto-generated method stub
		int n=0;
		try {
			int allocQuantity=0;
			conn=DbUtil.getConnections();
			String sql="insert into Asset values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setInt(1,b.getAssetId());
			ps.setString(2, b.getAssetName());
			ps.setString(3,b.getAssetDes() );
			ps.setInt(4, b.getQuantity());
			ps.setInt(5,allocQuantity);
			
			 n=ps.executeUpdate();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}
	
	public int modifyAsset(String assetName1, int quantity1) {
		// TODO Auto-generated method stub
		int n = 0;
		try {
			conn=DbUtil.getConnections();
			String sql="update Asset set Quantity=Quantity+? where AssetName=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, quantity1);
			ps.setString(2, assetName1);
			 n=ps.executeUpdate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}
	
	public Asset raiseRequest(Asset bean) throws AssetException
	{

	
		try {
			
			conn=DbUtil.getConnections();
			
		String query="SELECT empNo FROM Employee where Ename=?";
		PreparedStatement empStr=conn.prepareStatement(query);
		empStr.setString(1, bean.getEmployeeName());
		ResultSet empRs=empStr.executeQuery();
		empRs.next();
		bean.setEmpNo(empRs.getInt(1));
	
		String sql="Select assetId FROM Asset WHERE AssetName = ? ";
		PreparedStatement str=conn.prepareStatement(sql);
		str.setString(1,bean.getAssetName());
        
		ResultSet rs=str.executeQuery();
		while(rs.next())
		{
			
			bean.setAssetId(rs.getInt(1));
		}	
			
			System.out.println(bean.getAssetId());
		
		
		}catch(IOException | SQLException e)
		{
			throw new AssetException("Could not retrieve");
			
		}
		return bean;
	}
	public int insertRequests(Asset bean) throws AssetException
	{
	
       int row=0;
       int result=0;
       int allocationId=-1;
		try {
	
			conn=DbUtil.getConnections();
		
			String query="insert into asset_allocation values(seq_allocId.nextval,?,?,NULL,NULL,NULL)";
			
			PreparedStatement str=conn.prepareStatement(query);
			
			str.setInt(1,bean.getAssetId());
			
			str.setInt(2,bean.getEmpNo());
			
		
			
			row= str.executeUpdate();
	
			
			PreparedStatement pst=conn.prepareStatement("update asset set allocatedQuantity=? where assetId=?");
			pst.setInt(1, bean.getAllocatedQuantity());
			System.out.println("quantity"+bean.getAllocatedQuantity());
			pst.setInt(2, bean.getAssetId());
			System.out.println("id"+bean.getAssetId());
			result=pst.executeUpdate();
		
			
		
			if(row==1)
			{
			String sql="select seq_allocId.currval from dual";
			Statement str1=conn.createStatement();	
			ResultSet rs=str1.executeQuery(sql);
			rs.next();
			
			allocationId=rs.getInt(1);
			System.out.println(allocationId);
			}
			System.out.println(allocationId);
		
		}catch(IOException | SQLException e)
		{
			throw new AssetException("Could not retrieve");
			
		}	
		
		return allocationId;
			
	}
	
	
	

	
		    public int checkAsset(int allocId) throws AssetException
		    {
		    int checkQuantity;
		    try{
			conn=DbUtil.getConnections();
			System.out.println(allocId);
			String q="select quantity-allocatedQuantity from asset where assetId=(SELECT assetId FROM asset_allocation where allocationId=?)";
			PreparedStatement psq=conn.prepareStatement(q);
			psq.setInt(1, allocId);
			ResultSet rs1=psq.executeQuery();
			rs1.next();
			checkQuantity=rs1.getInt(1);
			
		    }catch(IOException | SQLException e)
			{
				throw new AssetException("Could not update");
				
			}
		    return checkQuantity;
		    }
			
			public int approveRequest(int allocationId) throws AssetException
			{
				int row=0;
				int result = 0;
				try{
				conn=DbUtil.getConnections();
			
						System.out.println("ALLOCATIONiD"+allocationId);
				String query="update asset_allocation set Allocation_date=sysdate ,release_date=sysdate+10 where allocationId=?";
				PreparedStatement ps=conn.prepareStatement(query);
				ps.setInt(1,allocationId);
				row=ps.executeUpdate();
				if(row == 1)
				{
					System.out.println("IN IF LOOP");
					String sql1 = "select allocatedQuantity from asset where assetId=(SELECT assetid from asset_allocation where allocationid = ?)"; 
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setInt(1, allocationId);
					ResultSet rs= pst1.executeQuery();
					rs.next();
					int quantity=rs.getInt(1);
					System.out.println("in dao"+quantity);
					
					String sql="update asset set allocatedQuantity=allocatedQuantity-? , quantity = quantity-?  where assetId=(SELECT assetid from asset_allocation where allocationid = ?)";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setInt(1, quantity);
					pst.setInt(2, quantity);
					pst.setInt(3, allocationId);
					result =pst.executeUpdate();
					
					
				}
				
				
			}catch(IOException | SQLException e)
			{
				throw new AssetException("Could not update");
				
			}
				return result;
			}
			
			
			
			
	/*	public int rejectRequest(int id) throws AssetException
		{
			int row=0;
			try{
			conn=DbUtil.getConnections();
			String query="delete from asset_allocation where Allocation_id=?";
			PreparedStatement ps=conn.prepareStatement(query);
			
			ps.setInt(1,id);
			
			row=ps.executeUpdate();
			}catch(IOException | SQLException e)
			{
				throw new AssetException("Could not delete the request");
				
			}
		
			return row;
			
		}*/
		
		
		
/*		public ArrayList<Asset> retrieveDetails(int id) throws AssetException {
			
			ArrayList<Asset> list = new ArrayList<Asset>();
			try {
				Connection conn = DbUtil.getConnections();
				
			
			String sql = "SELECT * FROM Asset s JOIN Asset_Allocation l ON s.Assetid = l.Assetid WHERE AllocationId = ?";;
			
			PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setInt(1, id);
	      
			ResultSet rs = pst.executeQuery();
			
			
			
			
			while(rs.next())
			{
				
				
				int assetId = rs.getInt(1);
				String assetName = rs.getString(2);
				String assetDes = rs.getString(3);
				
				int allocatedQuantity =rs.getInt(5);
				int allocationId= rs.getInt(6);
				int empNo  = rs.getInt(8);
				Date allocationDate = rs.getDate(9);
				Date releaseDate = rs.getDate(10);
				
				
				
				list.add(new Asset(allocationId,assetId,empNo,allocationDate,releaseDate,assetName,assetDes,allocatedQuantity));
				
			}
			
			}
			
			catch (IOException | SQLException e) {
				throw new AssetException("Could not retrieve");
				
			}
			
			return list;
		}*/

			
	public void setStatus(int allocId,String status) throws AssetException
	{
		try {
			Connection conn = DbUtil.getConnections();
			String updateStatus="update asset_allocation set status=? where AllocationId=?";
			PreparedStatement ps=conn.prepareStatement(updateStatus);
			ps.setString(1, status);
			ps.setInt(2,allocId);
			ps.executeUpdate();
			
			
			
		}
		catch(IOException | SQLException e) {
			throw new AssetException("Could not retrieve");
			
		}
		
		
		
	}
		

	public ArrayList<Asset> retrieveDetails() throws AssetException {
			
			ArrayList<Asset> list = new ArrayList<Asset>();
			try {
				Connection conn = DbUtil.getConnections();
				
			
			String sql = "SELECT * FROM Asset s JOIN Asset_Allocation l ON s.Assetid = l.Assetid ";;
			
			PreparedStatement pst = conn.prepareStatement(sql);
	     
	      
			ResultSet rs = pst.executeQuery();
			
			
			
			
			while(rs.next())
			{
				
				
				int assetId = rs.getInt(1);
				String assetName = rs.getString(2);
				String assetDes = rs.getString(3);
				int quantity = rs.getInt(4);
				
				int allocationId= rs.getInt(6);
				int empNo  = rs.getInt(8);
				
				
				
				
				list.add(new Asset(allocationId,assetId,empNo,assetName,assetDes,quantity));
				
			}
			
			}
			
			catch (IOException | SQLException e) {
				throw new AssetException("Could not retrieve");
				
			}
			
			return list;
		}

	@Override
	public int addAllocatedQuantity(Asset bean) throws AssetException {
		// TODO Auto-generated method stub
		return 0;
	}

	
}





