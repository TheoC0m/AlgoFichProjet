package indexbuilding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TypeIndexBuilder extends AbstractIndexBuilder {

	public TypeIndexBuilder(List<File> files) {
		super(files);
	}

	@Override
	public void buildIndex(Path path) {
		// objet qui associe un type de fichier et son chemin d'acces
		ExtPathCouple epc;
		// on cree une liste de ExtPathCouple
		List<ExtPathCouple> epclist = new ArrayList<ExtPathCouple>();
		for (File f : files) {
			try {
				// si le fichier est un fichier d'index, on ne fait rien
				if (!this.isIndex(f.getName())) {
					epc = new ExtPathCouple(this.getFileType(f), f.getCanonicalPath());
					epclist.add(epc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int i = 0;
		// on trie la liste de couple type/chemin d'acces
		epclist.sort(null);
		StringBuffer sb = new StringBuffer();
		// pour chaque element de la liste : on ajoute le couple au stringbuffer
		for (ExtPathCouple n : epclist) {
			i++;
			sb.append(n.extension + System.getProperty("line.separator") + n.path + System.getProperty("line.separator")
					+ System.getProperty("line.separator"));

			System.out.println(
					n.extension + System.getProperty("line.separator") + n.path + System.getProperty("line.separator"));
		}
		System.out.println(i + " fichier(s) indexe(s)");
		try {
			FileWriter fwriter = new FileWriter(path.toString() + "/IndexByType.index");
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			bwriter.write(sb.toString());
			bwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * methode qui tente de retourner le type du fichier
	 */
	public String getFileType(File file) {
		String fileType = "indertemine";
		try {
			fileType = Files.probeContentType(file.toPath());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return fileType;
	}

	/*
	 * classe interne permettant de gerer couple type/Path qui implemente
	 * Comparable pour pouvoir trier l'index
	 */
	private class ExtPathCouple implements Comparable<ExtPathCouple> {

		public String extension;
		public String path;

		public ExtPathCouple(String ext, String p) {
			this.extension = ext;
			this.path = p;
		}

		@Override
		public int compareTo(ExtPathCouple epc) {
			return this.extension.compareTo(epc.extension);
		}

	}

}
