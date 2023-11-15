package wbdsoap.services;

import wbdsoap.enums.SubscriptionFilter;
import wbdsoap.enums.SubscriptionStatus;
import wbdsoap.utils.responses.ArraySubsSOAPResponse;
import wbdsoap.utils.responses.GenericSOAPResponse;
import wbdsoap.daos.SubscriptionDAO;
import wbdsoap.models.SubscriptionEntity;
import wbdsoap.utils.HibernateUtil;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;
import java.util.Map;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SubscriptionService {
    private final SubscriptionDAO subscriptionDAO;

    private WebServiceContext webServiceContext;

    @Resource
    public void setContext(WebServiceContext context) {
        this.webServiceContext = context;
    }


    public SubscriptionService(){
        this.subscriptionDAO = new SubscriptionDAO(HibernateUtil.getSessionFactory());
    }

    @WebMethod
    public ArraySubsSOAPResponse getSubscriptionsByUser(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "filter") String filterArg){
        // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("MOLI_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new ArraySubsSOAPResponse("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new ArraySubsSOAPResponse("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new ArraySubsSOAPResponse("Authorization header is missing", false, null);
        }

        if(user_id == null){
            return new ArraySubsSOAPResponse("No user_id provided", false, null);
        }

        SubscriptionFilter filter;
        if(filterArg == null){
            filter = SubscriptionFilter.ALL;
        }
        else{
            switch (filterArg){
                case "ALL": filter = SubscriptionFilter.ALL;break;
                case "ACCEPT": filter = SubscriptionFilter.ACCEPTONLY;break;
                case "REJECT": filter = SubscriptionFilter.REJECTONLY;break;
                case "PENDING": filter = SubscriptionFilter.PENDINGONLY;break;
                default: return new ArraySubsSOAPResponse("Bad filter argument", false, null);
            }
        }

        List<SubscriptionEntity> subs = subscriptionDAO.selectByUser(user_id, filter);
        if (!subs.isEmpty()){
            SubscriptionEntity[] retval = new SubscriptionEntity[subs.size()];
            subs.toArray(retval);
            return new ArraySubsSOAPResponse("Get subscription request successfully sent", true, retval);
        }
        else{
            return new ArraySubsSOAPResponse("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public ArraySubsSOAPResponse getSubscriptionsByAuthor(@WebParam(name = "author_id") Integer author_id, @WebParam(name = "filter") String filterArg){
         // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("REST_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new ArraySubsSOAPResponse("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new ArraySubsSOAPResponse("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new ArraySubsSOAPResponse("Authorization header is missing", false, null);
        }
        
        if(author_id == null){
            return new ArraySubsSOAPResponse("No user_id provided", false, null);
        }

        SubscriptionFilter filter;
        if(filterArg == null){
            filter = SubscriptionFilter.ALL;
        }
        else{
            switch (filterArg){
                case "ALL": filter = SubscriptionFilter.ALL;break;
                case "ACCEPT": filter = SubscriptionFilter.ACCEPTONLY;break;
                case "REJECT": filter = SubscriptionFilter.REJECTONLY;break;
                case "PENDING": filter = SubscriptionFilter.PENDINGONLY;break;
                default: return new ArraySubsSOAPResponse("Bad filter argument", false, null);
            }
        }

        List<SubscriptionEntity> subs = subscriptionDAO.selectByAuthor(author_id, filter);
        if (!subs.isEmpty()){
            SubscriptionEntity[] retval = new SubscriptionEntity[subs.size()];
            subs.toArray(retval);
            return new ArraySubsSOAPResponse("Get subscription request successfully sent", true, retval);
        }
        else{
            return new ArraySubsSOAPResponse("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<SubscriptionEntity> getSubscriptionsOne(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
         // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("REST_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new GenericSOAPResponse<>("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new GenericSOAPResponse<>("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new GenericSOAPResponse<>("Authorization header is missing", false, null);
        }
        
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }

        List<SubscriptionEntity> subs = subscriptionDAO.selectOne(user_id, author_id);
        if (!subs.isEmpty()){
            return new GenericSOAPResponse<>("Get subscription request successfully sent", true, subs.get(0));
        }
        else{
            return new GenericSOAPResponse<>("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<SubscriptionEntity> subscribeRequest(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
         // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("MOLI_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new GenericSOAPResponse<>("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new GenericSOAPResponse<>("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new GenericSOAPResponse<>("Authorization header is missing", false, null);
        }
        
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }
        if(!subscriptionDAO.selectOne(user_id, author_id).isEmpty()){
            return new GenericSOAPResponse<>("Request already exists", false, null);
        }

        SubscriptionEntity subs = new SubscriptionEntity(user_id, author_id);
        try{
            subscriptionDAO.insertData(subs);
        } catch (Exception e){
            return new GenericSOAPResponse<>("Subscription request failed", false, null);
        }

        return new GenericSOAPResponse<>("Subscription request successfully sent", true, subs);
    }

    @WebMethod
    public GenericSOAPResponse<Integer> subscribeUpdate(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id, @WebParam(name = "status") String status){
         // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("REST_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new GenericSOAPResponse<>("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new GenericSOAPResponse<>("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new GenericSOAPResponse<>("Authorization header is missing", false, null);
        }
        
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }

        if(subscriptionDAO.selectOne(user_id, author_id).isEmpty()){
            return new GenericSOAPResponse<>("No request exists", false, null);
        }

        SubscriptionStatus newStatus;
        switch (status){
            case "ACCEPT": newStatus = SubscriptionStatus.ACCEPTED;break;
            case "REJECT": newStatus = SubscriptionStatus.REJECTED;break;
            case "PENDING": newStatus = SubscriptionStatus.PENDING;break;
            default: return new GenericSOAPResponse<>("Bad status argument", false, null);
        }

        int rowCount = subscriptionDAO.updateStatus(user_id, author_id, newStatus);
        if (rowCount != 0){
            return new GenericSOAPResponse<>("Subscription request successfully updated", true, rowCount);
        }
        else{
            return new GenericSOAPResponse<>("Subscription request update failed", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<Integer> deleteSubscriptionsOne(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
         // Access WebServiceContext to get the MessageContext
        MessageContext messageContext = webServiceContext.getMessageContext();

        // Get the HTTP headers from the MessageContext
        Map<String, List<String>> headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        // Check if the "Authorization" header is present
        if (headers != null && headers.containsKey("Authorization")) {
            // Get the value of the "Authorization" header
            List<String> authHeaderValues = headers.get("Authorization");
            if (authHeaderValues != null && !authHeaderValues.isEmpty()) {
                String authHeaderValue = authHeaderValues.get(0);

                // Check if the header starts with "Bearer "
                if (authHeaderValue.startsWith("Bearer ")) {
                    // Extract the key part after "Bearer "
                    String key = authHeaderValue.substring("Bearer ".length());

                    // Now you can use the key as needed
                    // For example, you can compare it with the expected key
                    if (!Dotenv.load().get("REST_KEY").equals(key)) {
                       // The provided key does not match the expected key
                        return new GenericSOAPResponse<>("Invalid key", false, null);
                    } 
                } else {
                    // Header is not in the expected format
                    return new GenericSOAPResponse<>("Invalid Authorization header format", false, null);
                }
            }
        } else {
            // "Authorization" header is not present
            return new GenericSOAPResponse<>("Authorization header is missing", false, null);
        }
        
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }

        int rowCount = subscriptionDAO.deleteOne(user_id, author_id);
        if (rowCount != 0){
            return new GenericSOAPResponse<>("Subscription request successfully deleted", true, rowCount);
        }
        else{
            return new GenericSOAPResponse<>("Subscription request deletion failed", false, null);
        }
    }

}
