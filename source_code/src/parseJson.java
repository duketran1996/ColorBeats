import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class parseJson {

    //Using Google gson to parse the color api data.
    //Get the color names.
    public static String getName (String RGB) throws IOException
    {
        String name = "";
        try {
            String path = "http://www.thecolorapi.com/id?rgb=" + RGB;
            URL urlPath = new URL(path);
            URLConnection req = urlPath.openConnection();
            req.connect();

            JsonParser json = new JsonParser();
            JsonElement data = json.parse(new InputStreamReader((InputStream) req.getContent()));
            JsonObject jsonObj = data.getAsJsonObject();
            name = jsonObj.getAsJsonObject("name").get("value").toString();
        }
        catch (MalformedURLException e) {
            System.out.println("Connection Failed");
        }
        return name;
    }

    //Get the HSL values from the color api.
    public String getHSL (String RGB) throws IOException
    {
        String HSL = "";
        try {
            String path = "http://www.thecolorapi.com/id?rgb=" + RGB;
            URL urlPath = new URL(path);
            URLConnection req = urlPath.openConnection();
            req.connect();

            JsonParser json = new JsonParser();
            JsonElement data = json.parse(new InputStreamReader((InputStream) req.getContent()));
            JsonObject jsonObj = data.getAsJsonObject();
            HSL = jsonObj.getAsJsonObject("hsl").get("h").toString() + "," + jsonObj.getAsJsonObject("hsl").get("s").toString() + "," + jsonObj.getAsJsonObject("hsl").get("l").toString();
        }
        catch (MalformedURLException e) {
            System.out.println("Connection Failed");
        }
        return HSL;
    }
}