package directoryparsing;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import indexbuilding.IndexBuilderFactory;
import indexcomparison.IndexCompare;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DirectoryParser dp = new DirectoryParser();
		Path path = FileSystems.getDefault().getPath("/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/dir3");
		IndexBuilderFactory ibf = IndexBuilderFactory.getInstance();

		try {
			dp.parse(path, "filename", ibf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			IndexCompare ic = new IndexCompare(
					"/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/IndexByName.index",
					"/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/dir3/IndexByName.index");
			ic.compare();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
