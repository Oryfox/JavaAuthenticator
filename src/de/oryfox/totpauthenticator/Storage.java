package de.oryfox.totpauthenticator;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    static List<KeyItem> keys = new ArrayList<>();

    private static final File home = new File(System.getProperty("user.home") + "/.github-oryfox/java-authenticator");
    private static final File keysJSON = new File(home.getAbsolutePath() + "/keys.json");
    public static final File password = new File(home.getAbsolutePath() + "/password");

    public static void loadKeys() {
        StringBuilder builder = new StringBuilder();
        String line;
        if (password.exists()) {
            Crypto.readPassword();

            if (keysJSON.exists()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(keysJSON));
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    reader.close();

                    JSONObject rootObject = new JSONObject(builder.toString());
                    JSONArray keys = rootObject.getJSONArray("keys");
                    JSONObject current;
                    for (int i = 0; i < keys.length(); i++) {
                        current = keys.getJSONObject(i);
                        Storage.keys.add(new KeyItem(current.getString("title"), current.getString("account"), current.getString("key")));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else saveKeys();
        } else {
            Crypto.createPassword();

            if (keysJSON.exists()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(keysJSON));
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    reader.close();

                    JSONObject rootObject = new JSONObject(builder.toString());
                    JSONArray keys = rootObject.getJSONArray("keys");
                    JSONObject current;
                    for (int i = 0; i < keys.length(); i++) {
                        current = keys.getJSONObject(i);
                        Storage.keys.add(new KeyItem(current.getString("title"), current.getString("account"), current.getString("key")));
                    }
                    saveKeys();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else saveKeys();
        }
    }

    public static void saveKeys() {
        if (!home.exists()) {
            //noinspection ResultOfMethodCallIgnored
            home.mkdirs();
            keys.add(new KeyItem("OryMail", "random.guy@orymail.com", "JBSWY3DPEHPK3PXP"));
            keys.add(new KeyItem("Aurora Merchandise Store", "obsessed.fan@orymail.com", "SIHGDFNADKGEMGLF"));
            keys.add(new KeyItem("SocialCollection","listen-to.chuchyard@byAurora.now","8DFMOFUPASDGHNWE"));
            Authenticator.demo = true;
        }
        JSONObject rootObject = new JSONObject();
        JSONArray keyArray = new JSONArray();
        JSONObject current;
        for (KeyItem item : keys) {
            if (!item.editMode) {
                current = new JSONObject();
                current.put("title", item.title);
                current.put("account", item.account);
                current.put("key", item.key);
                keyArray.put(current);
            }
        }
        rootObject.put("keys", keyArray);

        try {
            FileWriter writer = new FileWriter(keysJSON);
            writer.write(rootObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}