package io.dJarchive.test;

import static org.junit.Assert.assertTrue;
import io.dJarchive.src.JArchive;
import io.dJarchive.src.JFile;
import io.dJarchive.src.JInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UnarchiveTest {
	private static final String ARCHIVE_PATH = "";

	private static final ArrayList<String> validFileList = new ArrayList<String>();
	private static String lipsum1Contents;
	private static String lipsum2Contents;
	@Test
	public void testUnzip() throws FileNotFoundException, IOException {
		testUnarchive("test.rar");
	}
	
	@Before
	public void setUp() throws Exception {
		validFileList.add("foldera/");
		validFileList.add("foldera/folderb/");
		validFileList.add("foldera/folderb/lipsum2.txt");
		validFileList.add("foldera/lipsum1.txt");

		lipsum1Contents = readFile(ARCHIVE_PATH + "lipsum1.txt");
		lipsum2Contents = readFile(ARCHIVE_PATH + "lipsum2.txt");
	}

	public void testUnarchive(String archive) throws FileNotFoundException, IOException {
		genericOutput(
			JArchive.getJInputStream(new File(ARCHIVE_PATH + archive))
		);
	}

	private void genericOutput(JInputStream is) throws IOException {
		JFile entry;
		int entryCounter = 0;

		while((entry = is.getNextEntry()) != null) {
			if (entry.isArchive()) {
				genericOutput(
					JArchive.getJInputStream(entry, is)
				);

				continue;
			}

			++entryCounter;

			// Make sure we're seeing an expected file
			assertTrue(entry.getName(), validFileList.contains(entry.getName()));

			if (entry.getName().equals("foldera/lipsum1.txt")) {
				assertTrue(lipsum1Contents.equals(readFile(is)));
			} else if (entry.getName().equals("foldera/folderb/lipsum2.txt")) {
				assertTrue(lipsum2Contents.equals(readFile(is)));
			}
		}

		// Ensure at least four entries were processed
		assertTrue(entryCounter + " != 4", entryCounter == 4);
	}

	private static String readFile(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);

		try {
			return readFile(fis);
		} finally {
			fis.close();
		}
	}

	private static String readFile(InputStream is) throws IOException {
		byte[] b = new byte[10240];
		int len = b.length;
		int total = 0;

		while (total < len) {
			int result = is.read(b, total, len - total);
			if (result == -1) {
				break;
			}
			total += result;
		}

		return new String(b);
	}
}
