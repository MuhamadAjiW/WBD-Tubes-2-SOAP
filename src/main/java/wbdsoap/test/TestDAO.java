package wbdsoap.test;

import org.hibernate.SessionFactory;
import wbdsoap.daos.GenericDAO;

// TODO: Delete when done
public class TestDAO extends GenericDAO<TestEntity> {
    public TestDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
