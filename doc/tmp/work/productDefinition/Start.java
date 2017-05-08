package work.productDefinition;

import work.productDefinition.controlle.MainControlle;
import work.productDefinition.utils.SingletonFactory;

@SuppressWarnings("serial")
public class Start extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainControlle mv = SingletonFactory
						.getInstance(MainControlle.class);
				mv.setVisible(true);
			}
		});
	}
}
