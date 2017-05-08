package by.swing.self.tab;

import java.awt.Component;

public interface TabbedPaneListener {
    void tabRemoved(Tab tab, Component component, int index);
    void tabAdded(Tab tab, Component component, int index);
    void tabSelected(Tab tab, Component component, int index);
    void allTabsRemoved();
    boolean canTabClose(Tab tab, Component component);
}
