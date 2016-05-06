package indexbuilding;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractIndexBuilder implements IndexBuilder {

	List<File> files;

	public AbstractIndexBuilder(List<File> files) {
		this.files = files;
	}

	public abstract void buildIndex(Path path);

	public boolean isIndex(String s) {

		return (s.equals("IndexByName.index") || s.equals("IndexBySize.index") || s.equals("IndexByType.index"));
	}
}