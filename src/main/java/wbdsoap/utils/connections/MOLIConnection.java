package wbdsoap.utils.connections;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.simple.JSONObject;
import wbdsoap.enums.HTTPMethod;
import wbdsoap.utils.responses.HTTPResponse;

// TODO: Implement base calls etc
public class MOLIConnection extends BaseJSONConnection {
    public static String url = Dotenv.load().get("MOLI_URL", "http://localhost:8011");
    public static String token = Dotenv.load().get("MOLI_KEY", "phpinkm");

    public HTTPResponse sendRequest(String endpoint, HTTPMethod method, JSONObject data){
        return send(url, endpoint, method, data);
    }
}
