package indexcomparison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndexCompare {

	private File index1;
	private File index2;

	public IndexCompare(String index1_path, String index2_path) throws IOException {
		this.index1 = new File(index1_path);
		this.index2 = new File(index2_path);
		// on verifie que les fichiers existent
		if (this.index1.exists() || this.index2.exists()) {
			// on verifie que ce sont des fichiers d'index et qu'ils sont du
			// meme type
			if (!((this.index1.getName().equals("IndexByName.index")
					&& this.index2.getName().equals("IndexByName.index"))
					|| (this.index1.getName().equals("IndexBySize.index")
							&& this.index2.getName().equals("IndexBySize.index"))
					|| (this.index1.getName().equals("IndexByType.index")
							&& this.index2.getName().equals("IndexByType.index")))) {
				throw new IOException("Erreur : veuillez fournir des fichiers d'index, de meme type.");
			}
		} else {
			throw new IOException("Erreur : veuillez fournir des chemins d'acces valides");
		}

	}

	/*
	 * utilise la methode comp sur 2 indexs, en changeant le compare et le comparant
	 */
	public void compare() {
		StringBuffer sb = new StringBuffer();
		//on compare index1 a index2
		sb.append(this.comp(this.index1, this.index2));
		//des sauts de lignes
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		//on compare index2 a index1
		sb.append(this.comp(this.index2, this.index1));

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

				} else {
					//sinon la ligne ne contient pas de nom de fichier donc on incremente et on passe a la suivante
					if (i == 2) {
						i = -1;
					}

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb;
	}
	
	
	public boolean fileExists(File file, File index){
		
	}

}
