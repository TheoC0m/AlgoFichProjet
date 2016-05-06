package indexbuilding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.NameParser;

import java.nio.file.Path;

public class NameIndexBuilder extends AbstractIndexBuilder {

	public NameIndexBuilder(List<File> files) {
		super(files);
	}

	@Override
	public void buildIndex(Path path) {

		// objet qui associe un nom de fichier et son chemin d'acces
		NamePathCouple npc;
		// on cree une liste de NamePathCouple
		List<NamePathCouple> npclist = new ArrayList<NamePathCouple>();
		for (File f : files) {
			try {
				// si le fichier est un fichier d'index, on ne fait rien
				if (!this.isIndex(f.getName())) {
					npc = new NamePathCouple(f.getName(), f.getCanonicalPath());
					npclist.add(npc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int i = 0;
		// on trie la liste de couple nom/chemin d'acces
		npclist.sort(null);
		StringBuffer sb = new StringBuffer();
		// pour chaque element de la liste : on ajoute le couple au stringbuffer
		for (NamePathCouple n : npclist) {
			i++;
			sb.append(n.name + System.getProperty("line.separator") + n.path + System.getProperty("line.separator")
					+ System.getProperty("line.separator"));

			System.out.println(
					n.name + System.getProperty("line.separator") + n.path + System.getProperty("line.separator"));
		}
		System.out.println(i + " fichier(s) indexe(s)");

		try {
			FileWriter fwriter = new FileWriter(path.toString() + "/IndexByName.index");
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
	private class NamePathCouple implements Comparable<NamePathCouple> {

		public String name;
		public String path;

		public NamePathCouple(String n, String p) {
			this.name = n;
			this.path = p;
		}

		@Override
		public int compareTo(NamePathCouple npc) {
			return this.name.compareTo(npc.name);
		}

	}
}
