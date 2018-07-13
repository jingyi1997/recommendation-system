package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImportMovies {
	public void readFile(String path) {
		DBConnection db=new DBConnection();
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
        {
        	System.out.println("The file does not exist!");
        	return;
        }
        try{
        	InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
        	BufferedReader br=new BufferedReader(isr);
        
	        String temp=null;
	        StringBuffer sb=new StringBuffer();
	        temp=br.readLine();
	        while(temp!=null){
	            //System.out.println(temp);
	        	 String[] info=temp.split("::");
		         
		         int id=Integer.parseInt(info[0]);
		         
		         String year=info[1].substring(info[1].length()-5,info[1].length()-1);
		        String name=info[1].substring(0,info[1].length()-7);
		        
		        //System.out.println(name);
		        db.DBInsertMovies(id, year, name, info[2]);
		        System.out.println("Success:"+id);
	            temp=br.readLine();
	            
	          
		        
	        }
	        
        }
        catch(IOException e)
        {
        	e.printStackTrace(); 
        
        	
        }
	}
	        
	        
        
    

}
