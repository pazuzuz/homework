package stqa.pft.mantis.application_manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import stqa.pft.mantis.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Set<User> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User where enabled = 1").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(users);
        return new HashSet<>(users);
    }

    public Set<User> notAdminUsers(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User where enabled = 1 and username <> 'administrator'").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(users);
        return new HashSet<>(users);
    }

    public Set<User> usersWithDefaultPassword(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User where enabled = 1 and username <> 'administrator' and password = '5f4dcc3b5aa765d61d8327deb882cf99'").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(users);
        return new HashSet<>(users);
    }

    public void restoreDefaultPassword(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user.withPassword("5f4dcc3b5aa765d61d8327deb882cf99"));
        session.getTransaction().commit();
        session.close();
        System.out.println(user);
    }
}
