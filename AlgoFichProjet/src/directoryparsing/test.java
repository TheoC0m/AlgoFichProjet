package directoryparsing;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import indexbuilding.IndexBuilderFactory;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DirectoryParser dp = new DirectoryParser();
		Path path = FileSystems.getDefault().getPath("/home/theo/Documents");
		IndexBuilderFactory ibf = IndexBuilderFactory.getInstance();
		
		
		try {
			dp.parse(path, "filesize", ibf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
