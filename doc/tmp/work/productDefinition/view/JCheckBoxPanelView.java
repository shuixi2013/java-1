package work.productDefinition.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class JCheckBoxPanelView extends JPanel  {

	public JCheckBoxPanelView(ActionListener action, String title, String[] labels) {
//		setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createEtchedBorder(), title));
//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//		for (int i = 0; labels != null && i < labels.length; i++) {
//			JRadioButton b = new JRadioButton(labels[i]);
//			b.setActionCommand(labels[i]);
//			add(b);
//			// 添加事件监听器
//			b.addActionListener(action);
//			group.add(b);
//			b.setSelected(i == 0);
//		}
	}
	public JCheckBoxPanelView(String[] labels) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for (int i = 0; labels != null && i < labels.length; i++) {
			JCheckBox b = new JCheckBox(labels[i]);
			b.setActionCommand(labels[i]);
			add(b);
//			group.add(b);
			b.setSelected(i == 0);
		}
	}
	public String getString(){
		StringBuffer sb = new StringBuffer();
		for(Component comp :this.getComponents()){
			if(comp instanceof JCheckBox){
				JCheckBox jcb = (JCheckBox)comp;
				if(jcb.isSelected()){
					sb.append(jcb.getActionCommand().trim());
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
}
