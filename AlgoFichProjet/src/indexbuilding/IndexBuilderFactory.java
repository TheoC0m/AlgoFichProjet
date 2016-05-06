package indexbuilding;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.List;

public class IndexBuilderFactory {

	// mise en oeuvre du pattern Singleton
	private static IndexBuilderFactory instance;

	private IndexBuilderFactory() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * mise en oeuvre du pattern Singleton permet de s'assurer qu'on ne cree
	 * qu'une seule instance de la factory
	 */
	public static IndexBuilderFactory getInstance() {
		if (instance == null) {
			instance = new IndexBuilderFactory();
		}
		return instance;

	}

	/*
	 * Methode qui renvoie le bon type d'objet en fonction du type de cle que
	 * l'on veut utiliser pour l'index
	 * (nom, taille ou type du fichier)
	 */
	public IndexBuilder getIndexBuilder(List<File> files, String criterion) throws InvalidParameterException {
		IndexBuilder ib;
		switch (criterion) {

		case "filename":
			ib = new NameIndexBuilder(files);
			break;

		case "filetype":
			ib = new TypeIndexBuilder(files);
			break;

		case "filesize":
			ib = new SizeIndexBuilder(files);
			break;

		default:
			throw new InvalidParameterException("Critere invalide");
		}
		return ib;
	}

}
