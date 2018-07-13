package Test;

import util.ImportMovies;


public class TestImport {
	public static void main(String[] args)
	{
		ImportMovies im=new ImportMovies();
		im.readFile("E:\\movies.dat");
	}

}
