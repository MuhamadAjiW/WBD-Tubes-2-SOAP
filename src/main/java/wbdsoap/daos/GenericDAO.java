package wbdsoap.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAO<T> {
    protected final SessionFactory sessionFactory;

    public GenericDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void insertData(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
