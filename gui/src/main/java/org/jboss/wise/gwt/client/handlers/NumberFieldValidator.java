package org.jboss.wise.gwt.client.handlers;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValueBox;
import com.google.gwt.user.client.ui.Widget;
import java.text.ParseException;
import java.util.Iterator;
import org.jboss.wise.gwt.client.ui.WiseTreeItem;
import org.jboss.wise.gwt.client.view.EndpointConfigView;

/**
 * User: rsearls
 * Date: 8/11/16
 */
public class NumberFieldValidator implements KeyUpHandler {

    // GWT KeyCode does not provide code for period or comma.
    protected static final int KEY_NUM_PERIOD = 190;
    protected static final int KEY_NUM_COMMA = 188;

    WiseTreeItem wTreeItem;
    ValueBox inputBox;
    Label errorLabel;
    EndpointConfigView that;

    public NumberFieldValidator (WiseTreeItem wTreeItem, Label errorLabel, EndpointConfigView that) {
        this.wTreeItem = wTreeItem;
        this.errorLabel = errorLabel;
        this.that = that;
        init();
        errorLabel.setVisible(false);
        errorLabel.addStyleName("numberValidationError");

    }

    private void init() {

        Widget widget = wTreeItem.getWidget();

        if (widget instanceof HorizontalPanel) {

            Iterator<Widget> itWidget = ((ComplexPanel) widget).iterator();
            while (itWidget.hasNext()) {
                Widget w = itWidget.next();
                if (w instanceof ValueBox){
                    inputBox = (ValueBox)w;
                    break;
                }
            }

            ((HorizontalPanel)widget).add(errorLabel);
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {

        try {
            if (event.getNativeKeyCode() == KEY_NUM_COMMA) {
                throw new ParseException("", 0);
            } else {
                inputBox.getValueOrThrow();
                String text = inputBox.getText();

                //remove error msg only when valid number is present
                if (text.indexOf(",") == -1) {
                    inputBox.removeStyleName("numberValidationError");
                    errorLabel.setVisible(false);
                    wTreeItem.setValidationError(false);

                    that.decValidationError(wTreeItem);
                }
            }
        } catch(ParseException e) {
            inputBox.addStyleName("numberValidationError");
            errorLabel.setVisible(true);
            wTreeItem.setValidationError(true);

            that.incValidationError(wTreeItem);
        }
    }
}
