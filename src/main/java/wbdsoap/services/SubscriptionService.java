package wbdsoap.services;

import wbdsoap.utils.responses.ArraySubsResponse;
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
    public ArraySubsResponse getSubscriptionsByUser(@WebParam(name = "user_id") Integer user_id){
        if(user_id == null){
            return new ArraySubsResponse("No user_id provided", false, null);
        }

        List<SubscriptionEntity> subs = subscriptionDAO.selectByUser(user_id);
        if (!subs.isEmpty()){
            SubscriptionEntity[] retval = new SubscriptionEntity[subs.size()];
            subs.toArray(retval);
            return new ArraySubsResponse("Get subscription request successfully sent", true, retval);
        }
        else{
            return new ArraySubsResponse("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<SubscriptionEntity> getSubscriptionsByAuthor(@WebParam(name = "author_id") Integer author_id){
        if(author_id == null){
            return new GenericSOAPResponse<>("No author_id provided", false, null);
        }

        List<SubscriptionEntity> subs = subscriptionDAO.selectByAuthor(author_id);
        if (!subs.isEmpty()){
            return new GenericSOAPResponse<>("Get subscription request successfully sent", true, subs.get(0));
        }
        else{
            return new GenericSOAPResponse<>("Subscription entry does not exist", false, null);
        }
    }

    @WebMethod
    public GenericSOAPResponse<SubscriptionEntity> getSubscriptionsOne(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
        if(user_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
        }
        if(author_id == null){
            return new GenericSOAPResponse<>("No user_id provided", false, null);
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
            return new GenericSOAPResponse<>("No user_id provided", false, null);
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
    public SubscriptionEntity subscribeAccept(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
        if(user_id == null){
            return new SubscriptionEntity();
        }
        if(author_id == null){
            return new SubscriptionEntity();
        }

        SubscriptionEntity subs = new SubscriptionEntity(user_id, author_id);
//        subscriptionDAO.insertData(subs);
        return subs;
    }

    @WebMethod
    public SubscriptionEntity subscribeReject(@WebParam(name = "user_id") Integer user_id, @WebParam(name = "author_id") Integer author_id){
        if(user_id == null){
            return new SubscriptionEntity();
        }
        if(author_id == null){
            return new SubscriptionEntity();
        }

        SubscriptionEntity subs = new SubscriptionEntity(user_id, author_id);
//        subscriptionDAO.insertData(subs);
        return subs;
    }
}
