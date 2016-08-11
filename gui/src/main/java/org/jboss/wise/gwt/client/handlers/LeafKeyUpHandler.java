package org.jboss.wise.gwt.client.handlers;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.SimpleCheckBox;

/**
 * User: rsearls
 * Date: 8/11/16
 */
public class LeafKeyUpHandler implements KeyUpHandler {
    SimpleCheckBox checkBox;

    public LeafKeyUpHandler(SimpleCheckBox checkBox) {

        this.checkBox = checkBox;
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {

        checkBox.setValue(true);
    }
}
