import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import directoryparsing.DirectoryParser;
import indexbuilding.IndexBuilderFactory;
import indexcomparison.IndexCompare;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {

		if (args.length == 3) {
			
			

			switch (args[0]) {

			case "index":
				//on instancie l'objet qui permet de parcourir recursivement un repertoire
				DirectoryParser dp = new DirectoryParser();
				//on obtient une instance de la factory qui permet d'avoir les différents objets constructeurs d'indexs
				IndexBuilderFactory ibf = IndexBuilderFactory.getInstance();
				//on cree un objet path a partir du chemin du repertoire a indexer
				Path path = FileSystems.getDefault().getPath(args[1]);
				dp.parse(path, args[2], ibf);
				break;

			case "compare":
				IndexCompare ic = new IndexCompare();
				ic.compare(args[1], args[2]);
				break;

			case "search":
				IndexCompare ic2 = new IndexCompare();
				ic2.fileExists(args[1], args[2]);
				break;

			default:
				System.out.println("Les commandes sont : " + System.getProperty("line.separator")
						+ "index <chemin_absolu_du_repertoire_a_indexer> <critere d'indexation> (filename/filetype/filesize)"
						+ System.getProperty("line.separator") + "compare <chemin_index1> <chemin_index2>"
						+ System.getProperty("line.separator")
						+ "search <nom_du_fichier_a_chercher> <chemin_de_l'index_dans_lequel_chercher>");
			}

		} else {
			System.out.println("Mauvais nombre d'arguments" + System.getProperty("line.separator")
					+ "Les commandes sont : " + System.getProperty("line.separator")
					+ "index <chemin_absolu_du_repertoire_a_indexer> <critere d'indexation> (filename/filetype/filesize)"
					+ System.getProperty("line.separator") + "compare <chemin_index1> <chemin_index2>"
					+ System.getProperty("line.separator")
					+ "search <nom_du_fichier_a_chercher> <chemin_de_l'index_dans_lequel_chercher>");

		}

	}

}
