package io.dJarchive.src.rar;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import io.dJarchive.src.JFile;
import io.dJarchive.src.JInputStream;
import io.dJarchive.src.format.StreamVolumeManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@io.dJarchive.src.FileExtensions(values = { "rar" })
public class JRARInputStream extends JInputStream {
	protected Archive arc;//个人修改原来为private

	public JRARInputStream(JFile file, InputStream is) throws IOException {
		super(file, null);

		try {
			arc = new Archive(new StreamVolumeManager(file, is));
		} catch (RarException e) {
			throw new IOException(e);
		}
	}

	@Override
	public JFile getNextEntry() throws IOException {
		FileHeader fh = arc.nextFileHeader();

		if (fh == null) {
			return null;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			arc.extractFile(fh, baos);
		} catch (RarException e) {
			throw new IOException(e);
		}
		//把读取到的转换为输入流
		setInnerStream(new ByteArrayInputStream(baos.toByteArray()));

		String name = fh.getFileNameString().replace("\\", "/");
		name+= (fh.isDirectory() ? "/" : "");

		JFile jentry = new JFile(name, fh.getFullPackSize(), fh.getFullUnpackSize(), fh.isDirectory());

		return jentry;
	}
}