package wbdsoap.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    public List<SubscriptionEntity> selectByUser(Integer user_id){
        try (Session session = this.sessionFactory.openSession()) {
            String hql = "FROM SubscriptionEntity t WHERE t.user_id = :user_id";
            Query query = session.createQuery(hql);
            query.setParameter("user_id", user_id);
            return query.getResultList();
        }
    }

    public List<SubscriptionEntity> selectByAuthor(Integer author_id){
        try (Session session = this.sessionFactory.openSession()) {
            String hql = "FROM SubscriptionEntity t WHERE t.author_id = :author_id";
            Query query = session.createQuery(hql);
            query.setParameter("author_id", author_id);
            return query.getResultList();
        }
    }
}
