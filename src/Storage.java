import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    static ArrayList<KeyItem> keys = new ArrayList<>();

    private static final File home = new File(System.getProperty("user.home") + "/.oryfox/java-authenticator");
    private static final File keysJSON = new File(home.getAbsolutePath() + "/keys.json");

    public static void loadKeys() {
        StringBuilder builder = new StringBuilder();
        String line;
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
        }
    }

    public static void saveKeys() {
        if (!home.exists()) {
            //noinspection ResultOfMethodCallIgnored
            home.mkdirs();
            JSONObject rootObject = new JSONObject();
            JSONArray keyArray = new JSONArray();
            JSONObject current;
            for (KeyItem item : keys) {
                current = new JSONObject();
                current.put("title", item.title);
                current.put("account", item.account);
                current.put("key", item.key);
                keyArray.put(current);
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
}