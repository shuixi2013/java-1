package by.net.sftp;

import javax.transaction.SystemException;

import org.apache.commons.io.FilenameUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPTest {
	public SFTPChannel getSFTPChannel() {
		return new SFTPChannel();
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SFTPTest test = new SFTPTest();

		String src = "D:\\eclipseworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\sino-ACLifeWei\\wx\\share\\20161110\\79B77F26-E90C-A8C5-1E2E-0849DF00FD18.html"; // 本地文件名
		// String dst =
		// SFTPConstants.SFTP_REQ_LOC+"/share/20161111/79B77F26-E90C-A8C5-1E2E-0849DF00FD18.html";
		// // 目标文件名

		String dstTmp = "/share/2016-11-14/test2.html"; // 目标文件名
		String dst = dstTmp.substring(1); // 目标文件名
		String path = FilenameUtils.getFullPath(dst);

		SFTPChannel channel = test.getSFTPChannel();
		ChannelSftp chSftp = channel.getChannel();

		/**
		 * 代码段1 OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); //
		 * 使用OVERWRITE模式 byte[] buff = new byte[1024 * 256]; //
		 * 设定每次传输的数据块大小为256KB int read; if (out != null) {
		 * System.out.println("Start to read input stream"); InputStream is =
		 * new FileInputStream(src); do { read = is.read(buff, 0, buff.length);
		 * if (read > 0) { out.write(buff, 0, read); } out.flush(); } while
		 * (read >= 0); System.out.println("input stream read done."); }
		 **/
		// chSftp.mkdir(path);
		// chSftp.cd(path);
		// chSftp.lstat(path);
		// chSftp.ls(path);
		// SftpATTRS ffff = chSftp.stat(path);
		createDir(path, chSftp);
		chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

		// chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); //
		// 代码段3

		chSftp.quit();
		channel.closeChannel();
	}

	/**
	 * 创建一个文件目录
	 * 
	 * @throws SystemException
	 */
	private static boolean createDir(String createpath, ChannelSftp sftp) {
		if (isDirExist(createpath, sftp)) {
			return true;
		}
		String pathArry[] = createpath.split("/");
		StringBuffer filePath = new StringBuffer();
		for (String path : pathArry) {
			if (!"".equals(path)) {
				filePath.append(path + "/");
				if (!isDirExist(filePath.toString(), sftp)) {
					try {
						sftp.mkdir(filePath.toString());  // 建立目录
					} catch (SftpException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断目录是否存在
	 */
	private static boolean isDirExist(String directory, ChannelSftp sftp) {
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				return false;
			}
		}
		return false;
	}
}
