package work.productDefinition.view;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextField;

import work.productDefinition.component.calendar.JCalendarButton;

@SuppressWarnings("serial")
public class JCalendarPanelView extends JPanel {
	
	private JTextField jTextField;
	private JCalendarButton jCalendarButton;
	
	public static DateFormat dateFormat = DateFormat
			.getDateInstance(DateFormat.MEDIUM);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static DateFormat timeFormat = DateFormat
			.getTimeInstance(DateFormat.SHORT);
	public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.SHORT);

	public JCalendarPanelView() {
		setLayout(new BorderLayout());
		Date now = new Date();
		jTextField = new JTextField();
		jTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				String date = jTextField.getText();
				setDate(date);
			}
		});
		
		jCalendarButton = new JCalendarButton();
		jCalendarButton
				.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
					public void propertyChange(
							java.beans.PropertyChangeEvent evt) {
						if (evt.getNewValue() instanceof Date)
							setDate((Date) evt.getNewValue());
					}
				});
		
		setDate(now);
		add(jTextField);
		add(jCalendarButton,BorderLayout.EAST);
	}

	public void setDate(String dateString) {
		Date date = null;
		try {
			if ((dateString != null) && (dateString.length() > 0))
				date = sdf.parse(dateString);
		} catch (Exception e) {
			date = null;
		}
		this.setDate(date);
	}

	public void setDate(Date date) {
		String dateString = "";
		if (date != null)
			dateString = sdf.format(date);
		jTextField.setText(dateString);
		jCalendarButton.setTargetDate(date);
	
	}
	public String getString(){
		return jTextField.getText().trim();
	}

}
