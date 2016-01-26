package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.gcit.training.lms.entity.Publisher;

public class PublisherDAO extends AbstractDAO {
	
	
	public void create(Publisher p) throws SQLException {
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?)", 
				new Object[]{p.getPublisherName(),p.getPublisherAddress(),p.getPublisherPhone()});

	}
	
	public void update(Publisher p) throws SQLException {
		save("update tbl_publisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?",
				new Object[] { p.getPublisherName(), p.getPublisherAddress(),p.getPublisherPhone(),p.getPublisherId() });
	
	}
	
	public void delete(Publisher p) throws SQLException {
	    save("delete from tbl_publisher where publisherId = ?", new Object[] {p.getPublisherId()});
	    
	}
	
	@SuppressWarnings("unchecked")
	public Publisher readOne(int publisherId) throws SQLException {
        List<Publisher> list=(List<Publisher>) read("select * from tbl_publisher where publisherId = ?",
        		new Object[]{publisherId});
        
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Publisher> readAll() throws SQLException {
		return (List<Publisher>) read("select * from tbl_publisher", null);
	}

	
	@SuppressWarnings("unchecked")
	public List<Publisher> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Publisher>) read(
				"select * from tbl_publisher where publisherName like ?",
				new Object[] { qString });
	}

	@Override
	protected List<Publisher> processResult(ResultSet rs) throws SQLException {
		List<Publisher> aList = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));

			aList.add(a);
		}

		return aList;
	}
	
	
	

}
