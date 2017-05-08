package by.swing.swt;


import java.awt.*;
import java.awt.event.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SimpleMenu
{
	private Frame f = new Frame("����");
	private MenuBar mb = new MenuBar();
	Menu file = new Menu("�ļ�");
	Menu edit = new Menu("�༭");
	MenuItem newItem = new MenuItem("�½�");
	MenuItem saveItem = new MenuItem("����");
	// ����exitItem�˵��ָ��ʹ�� Ctrl+X ��ݼ�
	MenuItem exitItem = new MenuItem("�˳�"
		, new MenuShortcut(KeyEvent.VK_X));
	CheckboxMenuItem autoWrap = new CheckboxMenuItem("�Զ�����");
	MenuItem copyItem = new MenuItem("����");
	MenuItem pasteItem = new MenuItem("ճ��");
	Menu format = new Menu("��ʽ");
	// ����commentItem�˵��ָ��ʹ�� Ctrl+Shift+/ ��ݼ�
	MenuItem commentItem = new MenuItem("ע��" ,
		new MenuShortcut(KeyEvent.VK_SLASH , true));
	MenuItem cancelItem = new MenuItem("ȡ��ע��");
	private TextArea ta = new TextArea(6 , 40);
	public void init()
	{
		// �������ڲ������ʽ�����˵�������
		ActionListener menuListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String cmd = e.getActionCommand();
				ta.append("������" + cmd + "���˵�" + "\n");
				if (cmd.equals("�˳�"))
				{
					System.exit(0);
				}
			}
		};
		// ΪcommentItem�˵�������¼�������
		commentItem.addActionListener(menuListener);
		exitItem.addActionListener(menuListener);
		// Ϊfile�˵���Ӳ˵���
		file.add(newItem);
		file.add(saveItem);
		file.add(exitItem);
		// Ϊedit�˵���Ӳ˵���
		edit.add(autoWrap);
		// ʹ��addSeparator��������Ӳ˵��ָ���
		edit.addSeparator();
		edit.add(copyItem);
		edit.add(pasteItem);
		// Ϊformat�˵���Ӳ˵���
		format.add(commentItem);
		format.add(cancelItem);
		// ʹ�����new MenuItem("-")�ķ�ʽ��Ӳ˵��ָ���
		edit.add(new MenuItem("-"));
		// ��format�˵���ϵ�edit�˵��У��Ӷ��γɶ����˵�
		edit.add(format);
		// ��file��edit�˵���ӵ�mb�˵�����
		mb.add(file);
		mb.add(edit);
		// Ϊf�������ò˵���
		f.setMenuBar(mb);
		// �������ڲ������ʽ�������¼�����������
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		f.add(ta);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args)
	{
		new SimpleMenu().init();
	}
}
