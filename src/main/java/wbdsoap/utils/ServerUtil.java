package wbdsoap.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ServerUtil {
    public static String token = Dotenv.load().get("API_KEY", "nyabun");
}
