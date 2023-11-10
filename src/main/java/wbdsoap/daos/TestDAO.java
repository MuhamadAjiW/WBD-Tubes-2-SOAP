package wbdsoap.daos;

import org.hibernate.SessionFactory;
import wbdsoap.classes.GenericDAO;
import wbdsoap.models.TestEntity;

public class TestDAO extends GenericDAO<TestEntity> {
    public TestDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
