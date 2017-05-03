package io.dJarchive.src.rar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import io.dJarchive.src.JFile;
@io.dJarchive.src.FileExtensions(values = { "rar" })
public class UnlJRARInputStream extends JRARInputStream {
	public UnlJRARInputStream(JFile file, InputStream is) throws IOException {
		super(file, is);
		// TODO Auto-generated constructor stub
	}
	@Override
	public JFile getNextEntry() throws IOException {
		FileHeader fh = arc.nextFileHeader();
		if (fh == null) {
			return null;
		}
		if(!fh.getFileNameString().endsWith(".unl")){
			return getNextEntry();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			arc.extractFile(fh, baos);
		} catch (RarException e) {
			throw new IOException(e);
		}

		setInnerStream(new ByteArrayInputStream(baos.toByteArray()));

		String name = fh.getFileNameString().replace("\\", "/");
		name+= (fh.isDirectory() ? "/" : "");

		JFile jentry = new JFile(name, fh.getFullPackSize(), fh.getFullUnpackSize(), fh.isDirectory());

		return jentry;
	}
	
}
