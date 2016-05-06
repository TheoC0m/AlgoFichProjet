package indexcomparison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
			// on verifie que ce sont des fichiers d'index et qu'ils sont du meme type
			if (!((this.index1.getName().equals("IndexByName.index")
					&& this.index1.getName().equals("IndexByName.index"))
					|| (this.index1.getName().equals("IndexBySize.index")
							&& this.index2.getName().equals("IndexBySize.index"))
					|| (this.index1.getName().equals("IndexByType.index")
							&& this.index1.getName().equals("IndexByType.index")))) {
				throw new IOException("Erreur : veuillez fournir des fichiers d'index, de meme type.");
			}
		} else {
			throw new IOException("Erreur : veuillez fournir des chemins d'acces valides");
		}

	}
	
	public void compare(){
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(this.index1)));
		 String line;
		 boolean found = false;
         while (((line = br1.readLine()) != null) && !found){
        	 
         }
         
		
		
		
	}

}
