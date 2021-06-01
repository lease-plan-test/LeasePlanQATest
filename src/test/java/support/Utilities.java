package support;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Properties;
import java.util.Random;

public class Utilities {

    public static JSONObject getRandomIP() {
        String projectPath = System.getProperty("user.dir");
        JSONParser jsonParser = new JSONParser();
        String ipv4DataFile = projectPath + "/src/test/resources/data/ipData.json";
        try (FileReader reader = new FileReader(ipv4DataFile)) {
            Object obj = jsonParser.parse(reader);
            JSONArray ipList = (JSONArray) obj;
            int randomInt = new Random().nextInt(ipList.size() - 1);
            return (JSONObject) ipList.get(randomInt);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getErroneousIP(String type) {
        if (type.equals("invalid")) {
            return "999.999.999.999";
        } else if (type.equals("malformed")) {
            return "1234567890";
        } else {
            return "abcdefghijk";
        }
    }

    public static String getErroneousKey(String keyType) {
        if (keyType.equals("invalid")) {
            return "7fd477777de6a9f999e9b999634805e6";
        } else {
            return "";
        }
    }

    public static String getAPIConfigData(String key) {
        String projectPath = System.getProperty("user.dir");
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(projectPath + "/src/test/resources/data/apiData.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
