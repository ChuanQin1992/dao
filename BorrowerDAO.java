package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.gcit.training.lms.entity.Borrower;


public class BorrowerDAO extends AbstractDAO{
	
	public void create(Borrower b) throws SQLException {
		save("insert into tbl_borrower (name,address,phone) values (?)", 
				new Object[]{b.getName(),b.getAddress(),b.getPhone()});

	}
	
	public void update(Borrower b) throws SQLException {
		save("update tbl_borrower set name = ?, address=?, phone=? where cardNo = ?",
				new Object[] {b.getName(),b.getAddress(),b.getPhone(),b.getCardNo() });
	
	}
	
	public void delete(Borrower b) throws SQLException {
	    save("delete from tbl_borrower where cardNo = ?", new Object[] {b.getCardNo()});
	    
	}
	
	@SuppressWarnings("unchecked")
	public Borrower readOne(int cardNo) throws SQLException {
        List<Borrower> list=(List<Borrower>) read("select * from tbl_borrower where cardNo = ?",
        		new Object[]{cardNo});
        
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws SQLException {
		return (List<Borrower>) read("select * from tbl_borrower", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Borrower> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Borrower>) read(
				"select * from tbl_borrower where name like ?",
				new Object[] { qString });
	}	

	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		List<Borrower> aList = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));

			aList.add(a);
		}

		return aList;

    }
	
	
}
