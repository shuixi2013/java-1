package work.productDefinition.controlle;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

@SuppressWarnings("serial")
public class MainControlle extends JFrame {

	/** UIManager中UI字体相关的key */
	private static String[] DEFAULT_FONT  = new String[]{
	    "Table.font"
	    ,"TableHeader.font"
	    ,"CheckBox.font"
	    ,"Tree.font"
	    ,"Viewport.font"
	    ,"ProgressBar.font"
	    ,"RadioButtonMenuItem.font"
	    ,"ToolBar.font"
	    ,"ColorChooser.font"
	    ,"ToggleButton.font"
	    ,"Panel.font"
	    ,"TextArea.font"
	    ,"Menu.font"
	    ,"TableHeader.font"
	    // ,"TextField.font"
	    ,"OptionPane.font"
	    ,"MenuBar.font"
	    ,"Button.font"
	    ,"Label.font"
	    ,"PasswordField.font"
	    ,"ScrollPane.font"
	    ,"MenuItem.font"
	    ,"ToolTip.font"
	    ,"List.font"
	    ,"EditorPane.font"
	    ,"Table.font"
	    ,"TabbedPane.font"
	    ,"RadioButton.font"
	    ,"CheckBoxMenuItem.font"
	    ,"TextPane.font"
	    ,"PopupMenu.font"
	    ,"TitledBorder.font"
	    ,"ComboBox.font"
	};
	static {//设置L&F和字体
		try {
			UIManager.put("RootPane.setupButtonVisible", false);//去掉设置测试按钮
			 //设置本属性将改变窗口边框样式定义
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int i = 0; i < DEFAULT_FONT.length; i++){// 调整默认字体
			UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.PLAIN,14));
		}
	}
	private MainControlle() {
		
		JMenuBar jmb = OrganizeMenuBar();
		JTabbedPane jtp = OrganizeTabbedPane();
		
		setJMenuBar(jmb);
		add(jtp, BorderLayout.CENTER);
		
		setTitle("\u4E2D\u56FD\u4EBA\u5BFFe\u6295\u4FDD\u5DE5\u5177\u96C6");
		setBounds(100, 100, 1050,600);
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						MainControlle.class
								.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		setUndecorated(true);pack();
	}

	private JTabbedPane OrganizeTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		JPanel panel1 = new JPanel();
		panel1.add(new TableControlle());
		JPanel panel2 = new JPanel();
		panel2.add(new JButton("第二页"));
		JPanel panel3 = new JPanel();
		panel3.add(new JButton("第3页"));
		panel2.setBackground(Color.ORANGE);
		panel3.setBackground(Color.BLUE);
		tabbedPane
				.addTab("\u9669\u79CD\u5143\u7D20\u914D\u7F6E",
						new ImageIcon(
								MainControlle.class
										.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")),
										panel1, null);
		tabbedPane
		.addTab("\u5229\u76CA\u6F14\u793A\u914D\u7F6E",
				new ImageIcon(
						MainControlle.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")),
								panel2, null);
		
		tabbedPane
		.addTab("\u9759\u6001\u6587\u4EF6\u914D\u7F6E",
				new ImageIcon(
						MainControlle.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")),
								panel3, null);

		return tabbedPane;
	}

	private JMenuBar OrganizeMenuBar() {
		JMenu jMenu1 = new JMenu("文件");
		JMenuItem jMenuItem11 = new JMenuItem("打开");
		JMenuItem jMenuItem12 = new JMenuItem("退出");
		jMenu1.add(jMenuItem11);
		jMenu1.add(jMenuItem12);

		JMenu jMenu2 = new JMenu("帮助");
		JMenuItem jMenuItem21 = new JMenuItem("使用说明");
		JMenuItem jMenuItem22 = new JMenuItem("联系作者");
		jMenu2.add(jMenuItem21);
		jMenu2.add(jMenuItem22);

		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.add(jMenu1);
		jMenuBar.add(jMenu2);
		return jMenuBar;
	}

}
