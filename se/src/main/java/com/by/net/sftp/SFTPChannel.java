package com.by.net.sftp;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPChannel {
	Session session = null;
	Channel channel = null;

	
	private static Logger LOG = LogManager.getLogger(SFTPChannel.class);
	
	public ChannelSftp getChannel() throws JSchException {

		String ftpHost = SFTPConstants.SFTP_REQ_HOST;
		String port = SFTPConstants.SFTP_REQ_PORT;
		String ftpUserName = SFTPConstants.SFTP_REQ_USERNAME;
		String ftpPassword = SFTPConstants.SFTP_REQ_PASSWORD;
		int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
		int timeout = Integer.valueOf(SFTPConstants.TIME_OUT);
		
		if (port != null && !port.equals("")) {
			ftpPort = Integer.valueOf(port);
		}

		JSch jsch = new JSch(); // 创建JSch对象
		session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
		LOG.debug("Session created.");
		if (ftpPassword != null) {
			session.setPassword(ftpPassword); // 设置密码
		}
		Properties config = new Properties();
		
		// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
		config.put("StrictHostKeyChecking", "no");
//		config.put("UseDNS", "no");
		session.setConfig(config); // 为Session对象设置properties
		session.setTimeout(timeout); // 设置登陆超时时间
		session.connect(); // 通过Session建立链接
		LOG.debug("Session connected.");

		LOG.debug("Opening Channel.");
		channel = session.openChannel("sftp"); // 打开SFTP通道
//		channel.setFilenameEncoding("gbk");
		channel.connect(); // 建立SFTP通道的连接
		LOG.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
				+ ", returning: " + channel);
		return (ChannelSftp) channel;
	}

	public void closeChannel() throws Exception {
		if (channel != null) {
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}
}
