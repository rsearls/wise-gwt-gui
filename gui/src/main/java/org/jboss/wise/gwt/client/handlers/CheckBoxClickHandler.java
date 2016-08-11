package org.jboss.wise.gwt.client.handlers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import org.jboss.wise.gwt.client.ui.WiseTreeItem;

/**
 * User: rsearls
 * Date: 8/11/16
 */

public class CheckBoxClickHandler implements ClickHandler {

    private WiseTreeItem rootTreeItem;

    public CheckBoxClickHandler(WiseTreeItem rootTreeItem) {

        this.rootTreeItem = rootTreeItem;
    }

    public void onClick(ClickEvent event) {
        SimpleCheckBox checkBox = (SimpleCheckBox) event.getSource();
        boolean enable = checkBox.getValue();

        // skip disabling root element but set value to be passed
        if (rootTreeItem.getWTreeElement() != null) {
            rootTreeItem.getWTreeElement().setNil(!enable);
        }

        enableAllChildren(enable, rootTreeItem);
    }

    private void enableAllChildren(boolean enable, WiseTreeItem treeItem) {

        int cnt = treeItem.getChildCount();
        for (int i = 0; i < cnt; i++) {
            WiseTreeItem child = (WiseTreeItem) treeItem.getChild(i);

            // disabled children remain disabled no matter the parent setting.
            if (isChecked(child)) {
                enableAllChildren(enable, child);
            }

            child.setEnableTreeItem(enable);
        }
    }

    private boolean isChecked(WiseTreeItem child) {

        boolean isValue = true;
        if (child != null) {
            SimpleCheckBox checkBox = child.getCheckBox();
            if (checkBox != null) {
                return checkBox.getValue();
            }
        }
        return isValue;
    }
}
