package model.persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractPersistence<T> {
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractPersistence() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
    public void insert(Object a){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(a);
        t.commit();
        session.close();
    }

    public void delete(Object a){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(a);
        t.commit();
        session.close();
    }

    public void update(Object a){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(a);
        t.commit();
        session.close();
    }
    public List<T> readAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q=session.createQuery("from "+persistentClass.getName());
        List<T> lista= q.getResultList();
        session.close();
        return lista;
    }
}
