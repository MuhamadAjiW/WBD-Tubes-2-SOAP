package wbdsoap.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.time.Instant;

public class ServerUtil {
    public static String token = Dotenv.load().get("API_KEY", "nyabun");
    public static Duration rejectedSubExpireTime = Duration.ofSeconds(600);
    public static Integer subDeletionRoutineTime = 300 * 1000; //in milis
}
