package de.oryfox.totpauthenticator;

import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.function.BiFunction;

public class Crypto {
    static char[] password;
    public static void readPassword() {
        String hash = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Storage.password));
            hash = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var finished = false;
        int ok = -1;
        var passwordField = new JPasswordField();
        while (!finished) {
            var box = Box.createVerticalBox();
            box.add(new JLabel("Enter master password"));
            passwordField.setText("");
            box.add(passwordField);
            ok = JOptionPane.showConfirmDialog(null, box, "Enter master password", JOptionPane.OK_CANCEL_OPTION);
            if (ok == JOptionPane.OK_OPTION) finished = BCrypt.verifyer().verify(passwordField.getPassword(), hash).verified;
        }
        password = passwordField.getPassword();
    }

    public static void createPassword() {
        int ok = -1;
        boolean finished = false;
        var passwordField = new JPasswordField();
        var validationField = new JPasswordField();
        while (!finished) {
            var box = Box.createVerticalBox();
            box.add(new JLabel("Create master password"));
            passwordField.setText("");
            box.add(passwordField);
            box.add(new JLabel("Validate password"));
            validationField.setText("");
            box.add(validationField);
            ok = JOptionPane.showConfirmDialog(null, box, "Password creation", JOptionPane.OK_CANCEL_OPTION);
            if (ok == JOptionPane.OK_OPTION) {
                finished = new String(passwordField.getPassword()).equals(new String(validationField.getPassword()));
            }
        }

        var hashedPassword = BCrypt.withDefaults().hashToString(12, password = passwordField.getPassword());
        try {
            FileWriter writer = new FileWriter(Storage.password);
            writer.write(hashedPassword);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateSalt(int length) {
        var r = new SecureRandom();
        byte[] salt = new byte[length];
        r.nextBytes(salt);
        return salt;
    }
}
