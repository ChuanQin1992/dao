package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Book;

public class BookDAO extends AbstractDAO{

	
	public void create(Book b) throws SQLException {
		save("insert into tbl_book (title) values (?)",
				new Object[]{b.getTitle()});
	}

	public void update(Book b) throws SQLException {
		save("update tbl_book set title = ? where bookId = ?",
				new Object[]{b.getTitle(),b.getBookId()});

	}	

	public void delete(Book b) throws SQLException {
		save("delete from tbl_book where bookId = ?",
				new Object[]{b.getBookId()});
	}

	@SuppressWarnings("unchecked")
	public Book readOne(int bookId) throws SQLException {
		List<Book> list= (List<Book>) read("select * from tbl_book where bookId = ?",new Object[]{bookId});
		
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws SQLException {
		return (List<Book>) read("select * from tbl_book", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Book>) read(
				"select * from tbl_book where title like ?",
				new Object[] { qString });

	}

	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book> aList=new ArrayList<Book>();
		
		while(rs.next()){
			Book a=new Book ();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			
			aList.add(a);
			
		}
		return aList;
	}
}
