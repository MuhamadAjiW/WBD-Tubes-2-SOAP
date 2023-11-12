package wbdsoap.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import wbdsoap.enums.SubscriptionFilter;
import wbdsoap.enums.SubscriptionStatus;
import wbdsoap.models.SubscriptionEntity;

import javax.persistence.Query;
import java.util.List;

public class SubscriptionDAO extends GenericDAO<SubscriptionEntity> {

    public SubscriptionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<SubscriptionEntity> selectOne(Integer user_id, Integer author_id){
        try (Session session = this.sessionFactory.openSession()) {
            String hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id AND t.user_id = :user_id";
            Query query = session.createQuery(hql);
            query.setParameter("author_id", author_id);
            query.setParameter("user_id", user_id);
            return query.getResultList();
        }
    }

    public List<SubscriptionEntity> selectByUser(Integer user_id, SubscriptionFilter filter){
        try (Session session = this.sessionFactory.openSession()) {
            SubscriptionStatus status = null;
            String hql = "";
            switch (filter){
                case ALL:
                    hql = "FROM SubscriptionEntity t WHERE t.user_id = :user_id";
                    break;
                case ACCEPTONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.user_id = :user_id AND t.status = :filter";
                    status = SubscriptionStatus.ACCEPTED;
                    break;
                case REJECTONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.user_id = :user_id AND t.status = :filter";
                    status = SubscriptionStatus.REJECTED;
                    break;
                case PENDINGONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.user_id = :user_id AND t.status = :filter";
                    status = SubscriptionStatus.PENDING;
                    break;
                case ERROR:
                    throw new IllegalArgumentException("Subscription Filter is error");
            }
            Query query = session.createQuery(hql);
            query.setParameter("user_id", user_id);

            if (filter != SubscriptionFilter.ALL){
                query.setParameter("filter", status);
            }

            return query.getResultList();
        }
    }

    public List<SubscriptionEntity> selectByAuthor(Integer author_id, SubscriptionFilter filter){
        try (Session session = this.sessionFactory.openSession()) {
            SubscriptionStatus status = null;
            String hql = "";
            switch (filter){
                case ALL:
                    hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id";
                    break;
                case ACCEPTONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id AND t.status = :filter";
                    status = SubscriptionStatus.ACCEPTED;
                    break;
                case REJECTONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id AND t.status = :filter";
                    status = SubscriptionStatus.REJECTED;
                    break;
                case PENDINGONLY:
                    hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id AND t.status = :filter";
                    status = SubscriptionStatus.PENDING;
                    break;
                case ERROR:
                    throw new IllegalArgumentException("Subscription Filter is error");
            }
            Query query = session.createQuery(hql);
            query.setParameter("author_id", author_id);

            if (filter != SubscriptionFilter.ALL){
                query.setParameter("filter", status);
            }

            return query.getResultList();
        }
    }

    public int updateStatus(Integer user_id, Integer author_id, SubscriptionStatus status){
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "UPDATE SubscriptionEntity t SET status = :status WHERE t.author_id = :author_id AND t.user_id = :user_id";
            Query query = session.createQuery(hql);
            query.setParameter("author_id", author_id);
            query.setParameter("user_id", user_id);
            query.setParameter("status", status);
            System.out.println("Status: " + status.toString());
            System.out.println("aid: " + author_id.toString());
            System.out.println("uid: " + user_id.toString());

            int rowsUpdated = query.executeUpdate();
            session.getTransaction().commit();
            return rowsUpdated;
        }
    }
}
