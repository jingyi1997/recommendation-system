package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import Recommend.ItemRecommender;
import Recommend.TempUser;
import Recommend.UserRecommender;
import util.DBConnection;
import util.getMovies;
import util.movieInfo;


public class TestRe {
	public static void main(String[] args)
	{
		//UserRecommender ur=new UserRecommender();
		//TempUser.userrate.put("2045", "4");
		//TempUser.userrate.put("2002", "5");
		//TempUser.userrate.put("2033", "1");
		
		//HashSet<movieInfo> result=ur.getUserRecommend("E:\\ratings.dat", "E:\\movies.csv");
		
		//DBConnection dbc=new DBConnection();
		/*HashSet<movieInfo> res=dbc.getMovies("an");*/
		
		//dbc.UpdateName();
		getMovies gm=new getMovies();
		List<movieInfo> result=gm.MoviesCSV("wonderful", "E:\\movies.csv");
		
		for(movieInfo res:result){
			System.out.println(res.id+" "+res.name);
		}
		
		
	}

}
