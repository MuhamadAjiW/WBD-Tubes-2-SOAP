package wbdsoap.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import wbdsoap.models.TestEntity;

public class TestDAO {
    private final SessionFactory sessionFactory;

    public TestDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void insertData(TestEntity testEntity){

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(testEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
