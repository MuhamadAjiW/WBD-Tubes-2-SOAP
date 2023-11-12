package wbdsoap.services;

import wbdsoap.enums.SubscriptionFilter;
import wbdsoap.enums.SubscriptionStatus;
import wbdsoap.utils.responses.ArraySubsSOAPResponse;
import wbdsoap.utils.responses.GenericSOAPResponse;
import wbdsoap.daos.SubscriptionDAO;
import wbdsoap.models.SubscriptionEntity;
import wbdsoap.utils.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SubscriptionService {
    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionService(){
        this.subscriptionDAO = new SubscriptionDAO(HibernateUtil.getSessionFactory());
    }

    @WebMethod
    public ArraySubsSOAPResponse getSubscriptionsByUser(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "filter") String filterArg){
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
        if (rowCount == 0){
            return new GenericSOAPResponse<>("Subscription request successfully updated", true, rowCount);
        }
        else{
            return new GenericSOAPResponse<>("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<Integer> deleteSubscriptionsOne(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }

        int rowCount = subscriptionDAO.deleteOne(user_id, author_id);
        if (rowCount == 0){
            return new GenericSOAPResponse<>("Subscription request successfully deleted", true, rowCount);
        }
        else{
            return new GenericSOAPResponse<>("Subscription entry does not exist", false, null);
        }
    }

}
