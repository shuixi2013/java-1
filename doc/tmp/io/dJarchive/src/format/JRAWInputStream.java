package io.dJarchive.src.format;

import io.dJarchive.src.JFile;
import io.dJarchive.src.JInputStream;

import java.io.IOException;
import java.io.InputStream;

public class JRAWInputStream extends JInputStream {
	public JRAWInputStream(JFile file, InputStream is) {
		super(file, is);
	}

	@Override
	public JFile getNextEntry() throws IOException {
		JFile myJFile = getFile();

		setFile(null);

		return myJFile;
	}
}
