package com.by.swing.self.tab;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Test
 * @author Tom
 *
 */
public class TestDemo {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("可关闭Tab测试");
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TabbedPane tabbedPane = new TabbedPane();
		tabbedPane.setCloseButtonEnabled(true);
		tabbedPane.addTab("测试一", null, new JLabel("测试一"));
		tabbedPane.addTab("测试二", null, new JLabel("测试二"));
		tabbedPane.addTab("测试三", null, new JLabel("测试三"));
		tabbedPane.addTab("测试四", null, new JLabel("测试四"));
		
		frame.add(tabbedPane,BorderLayout.NORTH);
//		MySelfTabbedPane f = new MySelfTabbedPane();
//		frame.add(f);
		frame.setVisible(true);
	}
	
}
