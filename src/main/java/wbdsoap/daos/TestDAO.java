package wbdsoap.daos;

import org.hibernate.SessionFactory;
import wbdsoap.models.TestEntity;

// TODO: Delete when done
public class TestDAO extends GenericDAO<TestEntity> {
    public TestDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
