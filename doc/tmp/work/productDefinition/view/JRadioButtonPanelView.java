package work.productDefinition.view;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//定义一个JPanel类扩展类，该类的对象包含多个横向排列的JRadioButton控件
//且JPanel扩展类可以指定一个字符串作为TitledBorder
@SuppressWarnings("serial")
public class JRadioButtonPanelView extends JPanel {
	private ButtonGroup group;

	public JRadioButtonPanelView(ActionListener action, String title,
			String[] labels) {
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), title));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		group = new ButtonGroup();
		for (int i = 0; labels != null && i < labels.length; i++) {
			JRadioButton b = new JRadioButton(labels[i]);
			b.setActionCommand(labels[i]);
			add(b);
			// 添加事件监听器
			b.addActionListener(action);
			group.add(b);
			b.setSelected(i == 0);
		}
	}

	public JRadioButtonPanelView(String[] labels) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		group = new ButtonGroup();
		for (int i = 0; labels != null && i < labels.length; i++) {
			JRadioButton b = new JRadioButton(labels[i]);
			b.setActionCommand(labels[i]);
			add(b);
			group.add(b);
			b.setSelected(i == 0);
		}
	}

	// 定义一个方法，用于返回用户选择的选项
	public String getString() {
		return group.getSelection().getActionCommand().trim();
	}
}
