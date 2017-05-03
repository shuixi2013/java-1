package io.dJarchive.src;

import io.dJarchive.src.format.JDIRInputStream;
import io.dJarchive.src.format.JRAWInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JArchive {
	public static JInputStream getJInputStream(File file) throws FileNotFoundException, IOException {
		if (file.isDirectory()) {
			return new JDIRInputStream(file);
		}

		return getJInputStream(
			new JFile(file),
			new FileInputStream(file)
		);
	}

	public static JInputStream getJInputStream(JFile file, InputStream is) throws IOException {
		if (file.isArchive() && file.getStreamClass() != null) {
			try {
				return (JInputStream) file.getStreamClass().getConstructor(JFile.class, InputStream.class).newInstance(file, is);
			} catch (Exception e) {
				throw new IOException(e);
			}
		} else if (file.isDirectory()) {
			return new JDIRInputStream(new File(file.getName()));
		}

		return new JRAWInputStream(file, is);
	}
}