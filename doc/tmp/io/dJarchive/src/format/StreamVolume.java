package io.dJarchive.src.format;

import com.github.junrar.Archive;
import com.github.junrar.Volume;
import com.github.junrar.io.IReadOnlyAccess;
import com.github.junrar.io.InputStreamReadOnlyAccessFile;
import io.dJarchive.src.JFile;

import java.io.IOException;
import java.io.InputStream;

public class StreamVolume implements Volume {
	private JFile file;
	private Archive arc;
	private InputStream is;

	public StreamVolume(JFile file, Archive arc, InputStream is) {
		this.file = file;
		this.arc = arc;
		this.is = is;
	}

	@Override
	public Archive getArchive() {
		return arc;
	}

	@Override
	public long getLength() {
		return file.getSize();
	}

	@Override
	public IReadOnlyAccess getReadOnlyAccess() throws IOException {
		return new InputStreamReadOnlyAccessFile(is);
	}
}
