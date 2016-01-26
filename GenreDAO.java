package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Genre;

public class GenreDAO extends AbstractDAO {
	
	public void create(Genre g) throws SQLException {
		save("insert into tbl_genre (genreName) values (?)", 
				new Object[]{g.getGenreName()});

	}

	public void update(Genre g) throws SQLException {
		save("update tbl_genre set genreName = ? where genreId = ?", new Object[]{g.getGenreName(),g.getGenreId()});
	}
	
	public void delete(Genre g) throws SQLException {
		save ("delete from tbl_genre where genreId = ?", new Object[]{g.getGenreId()});

	}
	
	@SuppressWarnings("unchecked")
	public Genre readOne(int genreId) throws SQLException {
		List<Genre> list= (List<Genre>) read("select * from tbl_genre where genreId = ?",new Object[]{genreId});
		
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }

	}
	
	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws SQLException {
		return (List<Genre>) read("select * from tbl_genre", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Genre> readByName(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";
		return (List<Genre>) read(
				"select * from tbl_genre where genreName like ?",
				new Object[] { qString });

	}
	

	protected List<?> processResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Genre> aList=new ArrayList<Genre>();
		
		while(rs.next()){
			Genre a=new Genre ();
			a.setGenreId(rs.getInt("genreId"));
			a.setGenreName(rs.getString("genreName"));
			
			aList.add(a);
			
		}
		return aList;
	}

}
