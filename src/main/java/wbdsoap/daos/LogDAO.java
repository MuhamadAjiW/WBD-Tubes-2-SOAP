package wbdsoap.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import wbdsoap.models.LogEntity;
import wbdsoap.models.TestEntity;

public class LogDAO {
    private final SessionFactory sessionFactory;

    public LogDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void insertData(LogEntity logEntity){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(logEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
