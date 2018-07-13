package util;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.csvreader.CsvReader;

public class getMovies {
	public List<movieInfo> Movies(String query)
    {
		DBConnection dbc=new DBConnection();
		return dbc.getMovies(query);
		
    	
    }
	public List<movieInfo> MoviesCSV(String query,String filepath)
	{
		 List<movieInfo> res=new ArrayList<movieInfo>();
		 try {
	            // ����CSV������
	            CsvReader csvReader = new CsvReader(filepath);

	            // ����ͷ
	            csvReader.readHeaders();
	            while (csvReader.readRecord()){
	                // ��һ����
	                //System.out.println(csvReader.getRawRecord());
	                // �����е�ĳһ��
	                if(csvReader.get("name").toLowerCase().contains(query.toLowerCase()))
	                {
	                	String name=csvReader.get("name");
	                	//System.out.println(name);
	            		int id=Integer.parseInt(csvReader.get(0));
	            		String type=csvReader.get("type");
	            		String year=csvReader.get("published_year");
	            		movieInfo mi=new movieInfo(id,name,year,type);
	            		res.add(mi);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
		 return res;
	}
	public movieInfo MovieId(int mid,String filepath)
	{
		 movieInfo mi=new movieInfo();
		 try {
	            // ����CSV������
	            CsvReader csvReader = new CsvReader(filepath);

	            // ����ͷ
	            csvReader.readHeaders();
	            while (csvReader.readRecord()){
	                // ��һ����
	                //System.out.println(csvReader.getRawRecord());
	                // �����е�ĳһ��
	            	int id=Integer.parseInt(csvReader.get(0));
	                if(id==mid)
	                {
	                	String name=csvReader.get("name");
	                	//System.out.println(name);
	            		
	            		String type=csvReader.get("type");
	            		String year=csvReader.get("published_year");
	            		mi=new movieInfo(id,name,year,type);
	            		break;
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    return mi;
		
	}
}
