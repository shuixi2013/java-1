package work.productDefinition.controlle;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import work.productDefinition.component.layoutTable.Table;
import work.productDefinition.sqldefinition.SQLDefinition;
import work.productDefinition.utils.GUIUtils;
import work.productDefinition.view.JCalendarPanelView;
import work.productDefinition.view.JCheckBoxPanelView;
import work.productDefinition.view.JRadioButtonPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class TableControlle extends Table {
	
	public TableControlle() {
		super();
		orgaTable();
	}

	private void orgaTable() {
		//生成JLabel
		String[] strArr = {"险种代码","险种名称","险种简称","险种年份","险种类别1","险种类别2","险种类别3","险种分类","险种标识"
				,"主附险标识","开售时间","结束时间","投保人最小年龄","投保人最大年龄","被保人最小年龄","被保人最小年龄单位","被保人最大年龄"
				,"被保人最大年龄单位","销售渠道","产品渠道","责任名称","保险期间","保险期间单位","保额保费互算","缴费期间读取方式"
				,"领取年龄读取方式","保险年龄读取方式"};
		List<JLabel> jLabelList = GUIUtils.helpCreateList(JLabel.class,strArr);
		Iterator<JLabel> jLabelIter  = jLabelList.iterator();
		
		//生成JTextField
		String[] jtfArr = new String[21];
		List<JTextField> jFieldList = GUIUtils.helpCreateList(JTextField.class,jtfArr);
		Iterator<JTextField> jFieldListIter  = jFieldList.iterator();
		//设置JTextField.name
		String[] jtComArr = {"code","riskName","riskShortName","riskYear","codeName","riskFlag"
				,"subRiskFlag","minAppntAge","maxAppntAge","minInsuredAge","minAppntAgeFlag"
				,"maxInsuredAge","maxAppntAgeFlag","mngCon","duytName","insuYear","insuYearFlag"
				,"calmode","payEndYearRela","getYearRel","getYearRel"};
		for(int i = 0;i<21;i++){
			jFieldList.get(i).setName(jtComArr[i]);
		}
		
		String[] str1 = {"L","N","1"};//险种类别 单选框
		JRadioButtonPanelView riskType1 = new JRadioButtonPanelView(str1);
		riskType1.setName("riskType1");
		JRadioButtonPanelView riskType2 = new JRadioButtonPanelView(str1);
		riskType2.setName("riskType2");
		JRadioButtonPanelView riskType3 = new JRadioButtonPanelView(str1);
		riskType3.setName("riskType3");
		
		String[] str2 = {"I","B","SP","BS"};//销售渠道 复选框
		JCheckBoxPanelView jbp = new JCheckBoxPanelView(str2);
		jbp.setName("saleFlag");
		
		JCalendarPanelView jcp1 = new JCalendarPanelView();//险种定义日期
		jcp1.setName("startDate");
		JCalendarPanelView jcp2 = new JCalendarPanelView();
		jcp2.setName("endDate");
		
		JButton execBtn = new JButton("生成险种SQL");
		execBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		execBtn.addActionListener(new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				Map<String,Object> result = vali(TableControlle.this);
				boolean flag = (boolean) result.get("flag");
				if(flag){
					Map<String,String> data = (Map<String,String>)result.get("data");
					System.out.println(data);
					SQLDefinition.initMapData = data;
					SQLDefinition.makeSQL("d://sqlOK.txt");
				}else{
					JComponent jc = (JComponent)result.get("element");
					JOptionPane.showConfirmDialog(null,jc.getName());
				}
			}
			
		});
		columnDefaults(0).width(130);
		columnDefaults(1).width(200);
		columnDefaults(2).width(120);
		columnDefaults(3).width(200);
		columnDefaults(4).width(130);
		columnDefaults(5).width(200);
		
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),riskType1,jLabelIter.next(),riskType2});
		addRow(new JComponent[]{jLabelIter.next(),riskType3,jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jcp1,jLabelIter.next(),jcp2});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jbp,jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		addRow(new JComponent[]{jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next(),jLabelIter.next(),jFieldListIter.next()});
		
		row().height(100);
		addCell(execBtn).colspan(6).height(50).spaceTop(40);
//		setFocusTraversalPolicy(new FocusTraversalOnArray(//焦点策略
//				new Component[] { jtp }));
	}
	
	private void addRow(JComponent[] jc){
		row().height(30);
		for(int i=0;i<jc.length;i++){
			addCell(jc[i]).spaceBottom(10).padLeft(5);
		}
	}
	
	private Map<String,Object> vali(JComponent superComp) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,String> map = new HashMap<String,String>();
		for (Component comp : superComp.getComponents()) {
			if (comp instanceof JTextField
					&& !(comp instanceof JFormattedTextField)
					&& !(comp instanceof JTextArea)) {
				JTextField temp = (JTextField) comp;
				if(temp.getText().isEmpty()){
					result.put("flag", false);
					result.put("element", temp);
					return result;
				}else{
					map.put(temp.getName(), temp.getText().trim());
				}
			}else if(comp instanceof JRadioButtonPanelView) {
				JRadioButtonPanelView jbv = (JRadioButtonPanelView)comp;
				map.put(jbv.getName(), jbv.getString());
			}else if(comp instanceof JCalendarPanelView){
				JCalendarPanelView jbv = (JCalendarPanelView)comp;
				map.put(jbv.getName(), jbv.getString());
			}else if(comp instanceof JCheckBoxPanelView){
				JCheckBoxPanelView jbv = (JCheckBoxPanelView)comp;
				map.put(jbv.getName(), jbv.getString());
			}
		}
		result.put("flag", true);
		result.put("data", map);
		return result;
	}
}
