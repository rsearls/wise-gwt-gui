package org.jboss.wise.gwt.client.handlers;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.Label;
import java.text.ParseException;
import org.jboss.wise.gwt.client.ui.WiseTreeItem;
import org.jboss.wise.gwt.client.view.EndpointConfigView;

/**
 * User: rsearls
 * Date: 8/11/16
 */
public class IntegerFieldValidator extends NumberFieldValidator {

    public IntegerFieldValidator (WiseTreeItem wTreeItem, Label errorLabel, EndpointConfigView that) {
        super(wTreeItem, errorLabel, that);
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {

        try {
            if (event.getNativeKeyCode() == KEY_NUM_PERIOD  ||
                event.getNativeKeyCode() == KEY_NUM_COMMA) {
                throw new ParseException("", 0);
            } else {
                inputBox.getValueOrThrow();
                String text = inputBox.getText();

                //remove error msg only when valid number is present
                if (text.indexOf(".") == -1 && text.indexOf(",") == -1) {
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
