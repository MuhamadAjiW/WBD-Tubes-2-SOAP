package wbdsoap.middlewares;

import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

public class AuthMiddleware {
    public String checkKey(MessageContext messageContext, List<String> allowedKeys){
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        if (headers != null && headers.containsKey("Authorization")) {
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);
                if (authHeaderValue.startsWith("Bearer ")) {
                    String key = authHeaderValue.substring("Bearer ".length());

                    if (!allowedKeys.contains(key)) {
                        return "Invalid key";
                    }
                } else {
                    return "Invalid Authorization header format";
                }
            }
        } else {
            return "Authorization header is missing";
        }
        return null;
    }
}
