package indexcomparison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndexCompare {

	public IndexCompare() {

	}

	/*
	 * Verifie que les indexs existent et sont de meme type puis utilise la
	 * methode comp sur 2 indexs, en changeant le compare et le comparant
	 */
	public void compare(String index1_path, String index2_path) throws IOException {

		File index1 = new File(index1_path);
		File index2 = new File(index2_path);

		// on verifie que les fichiers existent
		if (index1.exists() || index2.exists()) {
			// on verifie que ce sont des fichiers d'index et qu'ils sont du
			// meme type
			if (!((index1.getName().equals("IndexByName.index") && index2.getName().equals("IndexByName.index"))
					|| (index1.getName().equals("IndexBySize.index") && index2.getName().equals("IndexBySize.index"))
					|| (index1.getName().equals("IndexByType.index")
							&& index2.getName().equals("IndexByType.index")))) {
				throw new IOException("Erreur : veuillez fournir des fichiers d'index, de meme type.");
			}
		} else {
			throw new IOException("Erreur : veuillez fournir des chemins d'acces valides");
		}

		StringBuffer sb = new StringBuffer();
		// on compare index1 a index2
		sb.append(this.comp(index1, index2));
		// des sauts de lignes
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		// on compare index2 a index1
		sb.append(this.comp(index2, index1));

		System.out.println(sb.toString());
	}

	private StringBuffer comp(File i1, File i2) {

		StringBuffer sb = new StringBuffer();

		try {
			// pour lire l'index 1
			BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(i1)));
			// ligne courante de l'index1
			String line1;
			// ligne courante de l'index2
			String line2;
			// compteur qui permet de "sauter" les lignes de l'index qui ne nous
			// interressent pas (1 / 3 contient le nom d'un fichier)
			int i = -1;
			// pour chaque ligne de l'index1
			while (((line1 = br1.readLine()) != null)) {
				i++;
				// on incremente le compteur de ligne
				boolean found = false;
				// si on est sur une ligne qui contient un nom de fichier
				if (i == 0) {

					// pour lire l'index2
					BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(i2)));
					// pour chaque ligne de l'index2
					while (((line2 = br2.readLine()) != null) && !found) {
						// si la ligne1 = ligne2
						// cad un fichier de l'index1 est present dans l'index2
						if (line2.equals(line1)) {
							// le fichier se trouve dans les 2 indexs : on
							// interrompt le parcours
							found = true;
						}

					}
					if (!found) {
						// si la ligne courante de l'index1 n'a pa ete trouvee
						// dans
						// tout l'index2
						sb.append("-> " + line1 + " :" + System.getProperty("line.separator") + " present dans : "
								+ i1.getCanonicalPath() + System.getProperty("line.separator") + " absent dans :"
								+ i2.getCanonicalPath() + System.getProperty("line.separator"));
					}
					br2.close();
				} else {
					// sinon la ligne ne contient pas de nom de fichier donc on
					// incremente et on passe a la suivante
					if (i == 2) {
						i = -1;
					}
				}
			}
			br1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb;
	}

	public boolean fileExists(String file_path, String index_path) throws IOException {

		File file = new File(file_path);
		File index = new File(index_path);

		// on verifie que les fichiers existent
		if (file.exists() || index.exists()) {
			// on verifie que l'index est bien un fichier d'index
			if (!(index.getName().equals("IndexByName.index") || index.getName().equals("IndexBySize.index")
					|| index.getName().equals("IndexByType.index"))) {
				throw new IOException("Erreur : l'un des deux fichiers doit être un index");
			}
		} else {
			throw new IOException("Erreur : veuillez fournir des chemins d'acces valides");
		}

		// pour lire l'index
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(index)));
		// booleen indiquant si le fichier a ete trouve
		boolean found = false;
		// ligne courante
		String line;
		// compteur qui permet de "sauter" les lignes de l'index qui ne nous
		// interressent pas (1 / 3 contient le nom d'un fichier)
		int i = -1;
		// tant qu'on a pas parcouru tout l'index et qu'on a pas trouve le
		// fichier
		while (((line = br.readLine()) != null) && !found) {
			// on incremente le compteur de ligne
			i++;
			// si on est sur une ligne qui contient un nom de fichier
			if (i == 0) {

				// si le nom du fichier recherche correspond a la ligne courante
				// de l'index
				if (file.getName().equals(line)) {
					// on a trouve : le fichier est present dans le repertoire
					// On affiche son chemin d'acces
					System.out.println("Fichier trouve : " + file.getCanonicalPath());
					found = true;
				}

			} else {
				// sinon la ligne ne contient pas de nom de fichier donc on
				// incremente et on passe a la suivante
				if (i == 2) {
					i = -1;
				}
			}

		}
		// si on a parcouru tout l'index sans trouver le fichier
		if (!found) {
			System.out.println("Le fichier n'est pas present dans le repertoire");
		}
		return found;
	}

}
