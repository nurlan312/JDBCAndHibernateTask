package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Util.getSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully drop table\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully saved\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("delete from users where id = ?");
            session.getTransaction().commit();
            session.close();
            System.out.println("id под номером: " + id + " - успешно удален из таблицы.\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
         try {
             Session session = Util.getSessionFactory().openSession();
             session.beginTransaction();
             List<User> users = session.createQuery("from User").list();
             session.getTransaction().commit();
             session.close();
             System.out.println("Таблица вызвана с помощью ArrayList: \n");
             for (User u : users) {
                 System.out.println(u);
             }
             return users;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Таблица полностью успешно очищена.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
