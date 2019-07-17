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
        System.out.println(users);
        return new HashSet<>(users);
    }

    public Set<User> usersWithDefaultPassword(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User where enabled = 1 and username <> 'administrator' and password = '5f4dcc3b5aa765d61d8327deb882cf99'").list();
        System.out.println(users);
        return new HashSet<>(users);
    }

    public void restoreDefaultPassword(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update("User", user);
        System.out.println(user);
    }

//    public Groups groups(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<GroupData> result = session.createQuery( "from GroupData" ).list();
//        session.getTransaction().commit();
//        session.close();
//        return new Groups(result);
//    }
//
//    public Users users(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<UserData> result = session.createQuery( "from UserData where deprecated = '0000-00-00 00:00:00'" ).list();
//        session.getTransaction().commit();
//        session.close();
//    return new Users(result);
//    }
//
//    public Groups userInGroups(UserData user) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        List<UserData> result = session.createQuery(
//                "from UserData where id = " + user.getId() ).list();
//        session.getTransaction().commit();
//        session.close();
//        return result.iterator().next().getGroups();
//    }
}
