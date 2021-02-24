package controller.gui;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class MediaAlert {

    private final String titleOfAlert;
    private final String headerOfAlert;
    private final String contentText;
    private final String labelText;
    private final String placeHolderTextField;
    private final Alert alert;
    private final Label label;
    private final TextField textField;
    private final ButtonType type;

    /**
     * Create a Alert
     * Source: https://o7planning.org/de/11529/anleitung-javafx-alert-dialog
     *
     * @param titleOfAlert         = title
     * @param headerOfAlert        = top text
     * @param labelText            = label text
     * @param contentText          = bottom text
     * @param placeHolderTextField = textfield placeholder
     */
    public MediaAlert(String titleOfAlert, String headerOfAlert, String labelText, String contentText, String placeHolderTextField) {

        if (contentText == null && placeHolderTextField == null) {
            new MediaAlert(titleOfAlert, headerOfAlert, labelText);
        } else if (titleOfAlert == null || headerOfAlert == null || labelText == null) {
            throw new NullPointerException(
                    String.format("Die Option waren falsch parametriert %s %s %s", titleOfAlert, headerOfAlert, labelText)
            );
        }

        this.titleOfAlert = titleOfAlert;
        this.headerOfAlert = headerOfAlert;
        this.contentText = contentText;
        this.labelText = labelText;
        this.placeHolderTextField = placeHolderTextField;

        alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleOfAlert);
        alert.setHeaderText(headerOfAlert);
        alert.setContentText(contentText);

        VBox box = new VBox();

        label = new Label();
        label.setText(labelText);

        textField = new TextField();
        textField.setText(placeHolderTextField);

        box.getChildren().setAll(label, textField);

        alert.getDialogPane().setContent(box);

        Optional<ButtonType> result = alert.showAndWait();

        this.type = result.orElse(null);
    }

    /**
     * Create a Alert
     * Source: https://o7planning.org/de/11529/anleitung-javafx-alert-dialog
     *
     * @param titleOfAlert  = title
     * @param headerOfAlert = top text
     * @param labelText     = label text
     */
    public MediaAlert(String titleOfAlert, String headerOfAlert, String labelText) {

        if (titleOfAlert == null || headerOfAlert == null || labelText == null) {
            throw new NullPointerException(
                    String.format("Die Option waren falsch parametriert %s %s %s", titleOfAlert, headerOfAlert, labelText)
            );
        }

        this.titleOfAlert = titleOfAlert;
        this.headerOfAlert = headerOfAlert;
        this.labelText = labelText;
        this.contentText = null;
        this.placeHolderTextField = null;

        alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleOfAlert);
        alert.setHeaderText(headerOfAlert);

        VBox box = new VBox();

        label = new Label();
        label.setText(labelText);

        textField = new TextField();

        box.getChildren().setAll(label, textField);

        alert.getDialogPane().setContent(box);

        Optional<ButtonType> result = alert.showAndWait();

        this.type = result.orElse(null);
    }

    public ButtonType getButtonType() {
        return type;
    }

    public String getText() {
        return this.textField.getText();
    }

    public String getTitleOfAlert() {
        return titleOfAlert;
    }

    public String getHeaderOfAlert() {
        return headerOfAlert;
    }

    public String getContentText() {
        return contentText;
    }

    public String getLabelText() {
        return labelText;
    }

    public String getPlaceHolderTextField() {
        return placeHolderTextField;
    }

    public Alert getAlert() {
        return alert;
    }

    public Label getLabel() {
        return label;
    }

    public TextField getTextField() {
        return textField;
    }

    public ButtonType getType() {
        return type;
    }
}
