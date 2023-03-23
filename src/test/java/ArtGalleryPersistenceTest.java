import model.ArtGallery;
import model.ArtWork;
import model.persistence.ArtGalleryPersistence;
import model.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ArtGalleryPersistenceTest {

    ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();


    @Test
    public void testInsert() {
        // Create a mock session object
        Session mockSession = mock(Session.class);

        // Create a mock transaction object
        Transaction mockTransaction = mock(Transaction.class);

        // Configure the session to return the mock transaction
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        // Create a test object
        ArtGallery testObject=new ArtGallery(1, "Le Galerie", "Paris");


        // Set the session factory of the DAO to use the mock session
        HibernateUtil.setSessionFactory(mock(SessionFactory.class));
        when(HibernateUtil.getSessionFactory().openSession()).thenReturn(mockSession);

        // Call the create method with the test object
        artGalleryPersistence.insert(testObject);

        // Verify that the session was used to save the test object
        verify(mockSession, times(1)).save(testObject);

        // Verify that the transaction was committed
        verify(mockTransaction, times(1)).commit();
    }
    @Test
    public void testUpdate() {
        // Create a mock session object
        Session mockSession = mock(Session.class);

        // Create a mock transaction object
        Transaction mockTransaction = mock(Transaction.class);

        // Configure the session to return the mock transaction
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        // Create a test object
        ArtGallery testObject=new ArtGallery(1, "Le Galerie", "Paris");

        // Set the session factory of the DAO to use the mock session
        HibernateUtil.setSessionFactory(mock(SessionFactory.class));
        when(HibernateUtil.getSessionFactory().openSession()).thenReturn(mockSession);

        // Call the update method with the test object
        artGalleryPersistence.update(testObject);

        // Verify that the session was used to update the test object
        verify(mockSession, times(1)).update(testObject);

        // Verify that the transaction was committed
        verify(mockTransaction, times(1)).commit();
    }

    @Test
    public void testReadAll() {
        // Mock the Hibernate session and query
        Session session = mock(Session.class);
        Query<ArtGallery> query = mock(Query.class);
        List<ArtGallery> expectedList = new ArrayList<ArtGallery>();
        when(query.getResultList()).thenReturn(expectedList);
        when(session.createQuery(anyString())).thenReturn(query);

        // Create an Example instance and inject the mock session
        HibernateUtil.setSessionFactory(mock(SessionFactory.class));
        when(HibernateUtil.getSessionFactory().openSession()).thenReturn(session);

        // Call the method being tested
        List<ArtGallery> actualList = artGalleryPersistence.readAll();

        // Verify that the session was opened and closed
        verify(session).close();

        // Verify that the query was created with the correct HQL string

        verify(session).createQuery(eq("from model.ArtGallery"));

        // Verify that the query was executed and returned the expected list
        verify(query).getResultList();
        assertEquals(expectedList, actualList);
    }
    @Test
    public void testDelete() {
        // Create a mock session object
        Session mockSession = mock(Session.class);

        // Create a mock transaction object
        Transaction mockTransaction = mock(Transaction.class);
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);
        int id=1;
        ArtGallery t=new ArtGallery();
        t.setIdGallery(id);
        Class<ArtGallery> type= ArtGallery.class;
        // mock the session factory and openSession() method
        HibernateUtil.setSessionFactory(mock(SessionFactory.class));
        when(HibernateUtil.getSessionFactory().openSession()).thenReturn(mockSession);

        // mock the get() method to return an instance of YourClass with id = 1
        Mockito.when(mockSession.get(type, id)).thenReturn(t);

        // call the delete() method

        artGalleryPersistence.delete(t);

        // verify that session.delete() method was called with the right argument
        Mockito.verify(mockSession).delete(t);

        // verify that the transaction.commit() method was called
        Mockito.verify(mockTransaction).commit();

        // verify that session.close() method was called
        Mockito.verify(mockSession).close();
    }

}