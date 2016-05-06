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

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DirectoryParser dp = new DirectoryParser();
		Path path = FileSystems.getDefault().getPath("/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root");
		IndexBuilderFactory ibf = IndexBuilderFactory.getInstance();

		dp.parse(path, "filename", ibf);

		IndexCompare ic = new IndexCompare();
		ic.compare("/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/IndexByName.index",
				"/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/dir3/IndexByName.index");

		ic.fileExists("/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/dir1/dir1-1/fichier_a_trouver",
				"/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/IndexByName.index");
		
		ic.fileExists("/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/dir1/dir1-1/fichier_inexistant",
				"/home/theo/GitRepos/algofichprojet/AlgoFichProjet/root/IndexByName.index");

	}

}
