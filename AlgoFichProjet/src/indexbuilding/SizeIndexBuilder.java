package indexbuilding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SizeIndexBuilder extends AbstractIndexBuilder {

	public SizeIndexBuilder(List<File> files) {
		super(files);
	}

	@Override
	public void buildIndex(Path path) {

		// objet qui associe la taille d'un fichier et son chemin
		SizePathCouple spc;
		// on cree une liste de spc
		List<SizePathCouple> spclist = new ArrayList<SizePathCouple>();
		for (File f : files) {
			try {
				// si le fichier est un fichier d'index, on ne fait rien
				if (!this.isIndex(f.getName())) {
					spc = new SizePathCouple(f.length(), f.getCanonicalPath());
					spclist.add(spc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int i = 0;
		// on trie la liste de couple taille/chemin d'acces
		spclist.sort(null);
		StringBuffer sb = new StringBuffer();
		// pour chaque element de la liste : on ajoute le couple au stringbuffer
		for (SizePathCouple n : spclist) {
			i++;
			sb.append(n.size + System.getProperty("line.separator") + n.path + System.getProperty("line.separator")
					+ System.getProperty("line.separator"));

			System.out.println(
					n.size + System.getProperty("line.separator") + n.path + System.getProperty("line.separator"));
		}
		System.out.println(i + " fichier(s) indexe(s)");
		try {
			FileWriter fwriter = new FileWriter(path.toString() + "/IndexBySize.index");
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			bwriter.write(sb.toString());
			bwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * classe interne permettant de gerer couple Nom/Path qui implemente
	 * Comparable pour pouvoir trier l'index
	 */
	private class SizePathCouple implements Comparable<SizePathCouple> {

		public Long size;
		public String path;

		public SizePathCouple(long s, String p) {
			this.size = s;
			this.path = p;
		}

		@Override
		public int compareTo(SizePathCouple spc) {
			return this.size.compareTo(spc.size);
		}

	}
}
