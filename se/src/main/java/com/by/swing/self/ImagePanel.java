package com.by.swing.self;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	// �������
	Image im;

	// ���캯��ȥָ����JPanel�Ĵ�С
	public ImagePanel(Image im) {
		this.im = im;// �˴���this�����٣������ܻ���ͼƬ
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width, height);
	}

	public void paintComponent(Graphics g) {// ��������
		super.paintComponent(g);// ����
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
