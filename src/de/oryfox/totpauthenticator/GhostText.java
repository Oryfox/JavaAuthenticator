package de.oryfox.totpauthenticator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
    private final JTextField textField;
    private boolean isEmpty;
    private final Color ghostColor;
    private Color foregroundColor;
    private final String ghostText;

    protected GhostText(JTextField textField, String ghostText) {
        this.textField = textField;
        this.ghostText = ghostText;
        this.ghostColor = Color.LIGHT_GRAY;
        textField.addFocusListener(this);
        this.registerListeners();
        this.updateState();
        if (!this.textField.hasFocus()) {
            this.focusLost(null);
        }

    }

    private void registerListeners() {
        this.textField.getDocument().addDocumentListener(this);
        this.textField.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        this.textField.getDocument().removeDocumentListener(this);
        this.textField.removePropertyChangeListener("foreground", this);
    }

    private void updateState() {
        this.isEmpty = this.textField.getText().length() == 0;
        this.foregroundColor = this.textField.getForeground();
    }

    public void focusGained(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();

            try {
                this.textField.setText("");
                this.textField.setForeground(this.foregroundColor);
            } finally {
                this.registerListeners();
            }
        }

    }

    public void focusLost(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();

            try {
                this.textField.setText(this.ghostText);
                this.textField.setForeground(this.ghostColor);
            } finally {
                this.registerListeners();
            }
        }

    }

    public void propertyChange(PropertyChangeEvent evt) {
        this.updateState();
    }

    public void changedUpdate(DocumentEvent e) {
        this.updateState();
    }

    public void insertUpdate(DocumentEvent e) {
        this.updateState();
    }

    public void removeUpdate(DocumentEvent e) {
        this.updateState();
    }
}
