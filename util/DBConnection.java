package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DBConnection {
	private Connection conn = null;
    private String url = "jdbc:mysql://localhost/movie?useUnicode=true&characterEncoding=UTF-8"; // URL指向要访问的数据库名
    private String user = "root"; // MySQL配置时的用户名
    private String password = "root"; // MySQL配置时的密码
    public DBConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int DBInsertMovies(int id,String year,String name,String type)
    {
    	int i=-1;
        try
        {
        	PreparedStatement pstmt;
        	String sql = "insert into movies (Id,name,published_year,type) values(?,?,?,?)";
        	pstmt = (PreparedStatement) conn.prepareStatement(sql);
        	
        	pstmt.setInt(1, id);
        	pstmt.setString(2, name);
        	pstmt.setString(3, year);
        	pstmt.setString(4, type);
        	
        	i = pstmt.executeUpdate();
        	pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failure in inserting into movies!");
        }
        return i;
    }
    public List<movieInfo> getMovies(String query)
    {
    	List rs=new ArrayList<movieInfo>();
    	ResultSet temp;
    	try
        {
        	PreparedStatement pstmt;
        	String sql = "select * from movies where name like ?";
        	pstmt = (PreparedStatement) conn.prepareStatement(sql);
        	
        	
        	pstmt.setString(1,"%"+query+"%");
        	
        	temp = pstmt.executeQuery();
        	while(temp.next())
        	{
        		/*rs.add(new movieInfo(temp.getInt("Id"),temp.getString(2),
        				temp.getString(3),temp.getString(4)));*/
        		
        		String name=temp.getString("name");
        		int id=temp.getInt("Id");
        		String type=temp.getString("type");
        		String year=temp.getString("published_year");
        		movieInfo mi=new movieInfo(id,name,year,type);
        		rs.add(mi);
        	}
        	
        	
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failure in querying!");
        }
    	return rs;
		
    	
    }
    public movieInfo getMovie(int mid)
    {
    	movieInfo mi=new movieInfo();
    	try
        {
        	PreparedStatement pstmt;
        	String sql = "select * from movies where Id= ?";
        	pstmt = (PreparedStatement) conn.prepareStatement(sql);
        	
        	
        	pstmt.setInt(1,mid);
        	
        	ResultSet temp = pstmt.executeQuery();
        	
        	while(temp.next())
        	{
        		/*rs.add(new movieInfo(temp.getInt("Id"),temp.getString(2),
        				temp.getString(3),temp.getString(4)));*/
        		
        		String name=temp.getString("name");
        		int id=temp.getInt("Id");
        		String type=temp.getString("type");
        		String year=temp.getString("published_year");
        		mi=new movieInfo(id,name,year,type);
        		
        	}
        	
        	
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failure in querying!");
        }
    	return mi;
		
    	
    }
    public void UpdateName()
    {
    	try
        {
        	PreparedStatement pstmt;
        	String sql = "select * from movies where name like '%The The%'";
        	pstmt = (PreparedStatement) conn.prepareStatement(sql);
        	
        	
        	
        	
        	ResultSet temp = pstmt.executeQuery();
        	
        	while(temp.next())
        	{
        		/*rs.add(new movieInfo(temp.getInt("Id"),temp.getString(2),
        				temp.getString(3),temp.getString(4)));*/
        		
        		String name=temp.getString("name");
        		String res=name.substring(4, name.length());
        		System.out.println(res);
        		sql = "update movies set name=? where Id=?";
        		pstmt = (PreparedStatement) conn.prepareStatement(sql);
        		pstmt.setString(1, res);
        		pstmt.setInt(2,temp.getInt("Id"));
        		pstmt.executeUpdate();
        	}
        	
        	
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failure in querying!");
        }
    	
    }

}
