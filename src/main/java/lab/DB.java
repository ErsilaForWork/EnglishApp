package lab;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DB {
    private static final SessionFactory SF = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
           return new Configuration()
                   .configure()
                   .addAnnotatedClass(Word.class)
                   .buildSessionFactory();
        }catch(Exception e) {
            System.out.println("Problem with creating session factory");
            System.err.println(e);
            return null;
        }
    }

    public static void sessionFactoryOff(){
        if(SF != null) {
            SF.close();
        }
    }

    public static void add(Word word) {

        try(Session session = SF.openSession()) {

            Transaction ts = session.beginTransaction();
            session.persist(word);
            ts.commit();

        }catch (Exception e){
            System.out.println("Something wrong in DB class, add method");
            System.err.println(e);
        }

    }

    public static void update(Word word) {

        try(Session session = SF.openSession()) {

            Transaction ts = session.beginTransaction();
            session.merge(word);
            ts.commit();

        }catch (Exception e){
            System.out.println("Something wrong in DB class, update method");
            System.err.println(e);
        }

    }

    public static void delete(Word word) {

        try(Session session = SF.openSession()) {

            Transaction ts = session.beginTransaction();
            session.remove(word);
            ts.commit();

        }catch (Exception e){
            System.out.println("Something wrong in DB class, delete method");
            System.err.println(e);
        }

    }

    public static List<Word> fetchWords() {

        try(Session session = SF.openSession()) {

            String HQL = "from Word";
            Query query = session.createQuery(HQL);
            return query.getResultList();

        }catch (Exception e){
            System.out.println("Something wrong in DB class, fetchWords method");
            System.err.println(e);
            return null;
        }

    }
}
