package wbdsoap.utils.test;

import org.hibernate.SessionFactory;
import wbdsoap.daos.GenericDAO;
import wbdsoap.utils.test.TestEntity;

// TODO: Delete when done
public class TestDAO extends GenericDAO<TestEntity> {
    public TestDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}