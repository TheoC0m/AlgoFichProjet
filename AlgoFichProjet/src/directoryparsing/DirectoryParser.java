package directoryparsing;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import indexbuilding.IndexBuilder;
import indexbuilding.IndexBuilderFactory;

public class DirectoryParser {

	public DirectoryParser() {
		// TODO Auto-generated constructor stub
	}

	public void parse(Path path, String criterion, IndexBuilderFactory ibf ) throws IOException {
		List<File> files = new ArrayList<File>();
		try {
			files = Files.walk(path)
			        .filter(Files::isRegularFile)
			        .map(Path::toFile)
			        .collect(Collectors.toList());
		} catch (UncheckedIOException e) {
			System.out.println("l'acces a certains repertoires a ete refuse, veuillez verifier vos droits d'acces");
			
		}
		
		
		IndexBuilder indexbuilder = ibf.getIndexBuilder(files, criterion);
		indexbuilder.buildIndex(path);
		
		
	}
	
	
}
