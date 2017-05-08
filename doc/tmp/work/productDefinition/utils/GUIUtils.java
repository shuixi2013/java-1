package work.productDefinition.utils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public final class GUIUtils {
	
	@SuppressWarnings("unchecked")
	public static <T extends JComponent> T[] helpCreateArr(Class<T> componentType,final String[] strArr){
		int len = strArr.length;
		T[] result = (T[]) Array.newInstance(componentType , len);
		for(int i=0;i<len;i++){
			Constructor<T> constructor = null;
			try {
				constructor = componentType.getConstructor(String.class);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			try {
				result[i] = constructor.newInstance(strArr[i]);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static <T extends JComponent> ArrayList<T> helpCreateList(Class<T> componentType,final String[] strArr){
		int len = strArr.length;
		ArrayList<T> result = new  ArrayList<T>();
		for(int i=0;i<len;i++){
			Constructor<T> constructor = null;
			try {
				constructor = componentType.getConstructor(String.class);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			try {
				result.add(constructor.newInstance(strArr[i]));
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void invokeAndWait(final Runnable task) {
		if (EventQueue.isDispatchThread()) {
			task.run();
		} else {
			try {
				EventQueue.invokeAndWait(task);
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Centers a window on screen.
	 * @param w The window to center.
	 */
	public static void centerOnScreen(Window w) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension splashSize = w.getPreferredSize();
		w.setLocation(screenSize.width / 2 - (splashSize.width / 2),
				screenSize.height / 2 - (splashSize.height / 2));
//		int guiWidth = (int) this.getSize().getWidth();
//		int guiHeight = (int) this.getSize().getHeight();
//		this.setLocation((screenWidth - guiWidth)/2,(screenHeight-guiHeight)/2);
	}

	public static void maximizeJFrame(JFrame f) {
		f.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	public static void lockJTableColumnWidth(TableColumn tc, int pixels) {
		if (tc != null) {
			tc.setMinWidth(pixels);
			tc.setMaxWidth(pixels);
			tc.setPreferredWidth(pixels);
			tc.setResizable(false);
		}
	}

	public static void hideJTableColumn(TableColumn tc) {
		lockJTableColumnWidth(tc, 0);
	}

}
