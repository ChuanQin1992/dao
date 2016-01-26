package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.gcit.training.lms.entity.Branch;
import com.gcit.training.lms.entity.Publisher;


public class BranchDAO extends AbstractDAO {

	
	public void create(Branch b) throws SQLException {
		save("insert into tbl_library_branch (branchName,branchAddress) values (?)", 
				new Object[]{b.getBranchName(),b.getBranchAddress()});

	}	
	
	public void update(Branch b) throws SQLException {
		save("update tbl_library_branch set branchName = ?,branchAddress=? where branchId = ?",
				new Object[] { b.getBranchName(),b.getBranchAddress(),b.getBranchId() });
	
	}	
	
	public void delete(Branch b) throws SQLException {
	    save("delete from tbl_library_branch where branchId = ?", new Object[] {b.getBranchId()});
	    
	}	
	
	@SuppressWarnings("unchecked")
	public Branch readOne(int branchId) throws SQLException {
        List<Branch> list=(List<Branch>) read("select * from tbl_library_branch where branchId = ?",
        		new Object[]{branchId});
        
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Branch> readAll() throws SQLException {
		return (List<Branch>) read("select * from tbl_library_branch", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Branch> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Branch>) read(
				"select * from tbl_library_branch where branchName like ?",
				new Object[] { qString });
	}
	
	@Override
	protected List<Branch> processResult(ResultSet rs) throws SQLException {
		List<Branch> aList = new ArrayList<Branch>();
		while (rs.next()) {
			Branch a = new Branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));

			aList.add(a);
		}

		return aList;
	}

	
	
	
}
