package Recommend;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import util.DBConnection;
import util.getMovies;
import util.movieInfo;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserRecommender {  
        public List<RecommendedItem> myUserBasedRecommender(long userID,int size,String filepath){  
        	List<RecommendedItem> list =new ArrayList<RecommendedItem>();
            try {  
                DataModel model = new FileDataModel(new File(filepath));//构造数据模型，File-based  
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
                Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);//构造推荐引擎  
                list = recommender.recommend(userID, size);
                
            } catch (Exception e) {  
                // TODO: handle exception  
                e.printStackTrace();  
            }  
            return list;
        }
        public HashSet<movieInfo> getUserRecommend(String filepath,String moviepath)
	    {
	    	HashSet<movieInfo> result=new HashSet<movieInfo>();
	    	long res=0;
	    	try {
	    		 RandomAccessFile rf = new RandomAccessFile(filepath, "rw"); 
	    		 rf.seek(21593502);
	    		 long length=rf.length()-21593502;
	    		 for(int i=0;i<length;i++)
	    		 {
	    			 rf.writeBytes(" ");
	    		 }
	    		 rf.seek(21593502);
	    		 Iterator iter = TempUser.userrate.entrySet().iterator();
	    		 int empty=1;
	    		 while (iter.hasNext()) {
	    			empty=0;
		    		String writeData="6041,";
		    		Map.Entry entry = (Map.Entry) iter.next();
		    		String key = entry.getKey().toString();
		    		String val = entry.getValue().toString();
		    		writeData+=key+","+val+","+"956715648"+'\n';
		    		System.out.print(writeData);
		    		rf.writeBytes(writeData);
		    		
	    		}
	    		if(empty==1)
	    		{
	    			return result;
	    		}
	    		rf.setLength(rf.getFilePointer());
	    		
	    		List<RecommendedItem> list=myUserBasedRecommender(6041,5,filepath);
	    		if(list.size()==0)
	    		{
	    			return result;
	    		}
	    		else
	    		{
	    			getMovies gm=new getMovies();
	    			
	    			for(RecommendedItem ri:list)
	    			{
	    				int mid=(int)ri.getItemID();
	    				result.add(gm.MovieId(mid,moviepath));
	    			}
	    			
	    		}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	return result;
	    }
    }  
