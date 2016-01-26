package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;

public class AuthorDAO extends AbstractDAO {
	

	public void create(Author a) throws SQLException {
		
		save("insert into tbl_author (authorName) values (?)", new Object[] { a.getAuthorName() });

	}

	public void update(Author a) throws SQLException {

		save("update tbl_author set authorName = ? where authorId = ?", new Object[]{a.getAuthorName(),a.getAuthorId()});
	}

	public void delete(Author a) throws SQLException {

		save ("delete from tbl_author where authorId = ?", new Object[]{a.getAuthorId()});

	}

	
	@SuppressWarnings("unchecked")
	public Author readOne(int authorId) throws SQLException {
		List<Author> list= (List<Author>) read("select * from tbl_author where authorId = ?",new Object[]{authorId});
		
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}

	@Override
	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Author> aList=new ArrayList<Author>();
		
		while(rs.next()){
			Author a=new Author ();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			
			aList.add(a);
			
		}
		return aList;
	}


	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws SQLException {
		return (List<Author>) read("select * from tbl_author", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Author>) read(
				"select * from tbl_author where authorName like ?",
				new Object[] { qString });

	}


}
