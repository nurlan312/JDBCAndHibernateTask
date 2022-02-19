package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Elnura.", "Kaparova.", (byte) 23.);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        User user2 = new User("Zamira.", "Keldibaeva.", (byte) 32.);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        User user3 = new User("Meerim.", "Baltabaeva.", (byte) 25.);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        User user4 = new User("Venera.", "Adybaeva.", (byte) 21.);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> users = new UserServiceImpl().getAllUsers();
        System.out.println(users);
        for (User u : users) {
            System.out.println(u);
        }

        userService.removeUserById(4L);
        userService.cleanUsersTable();
        userService.dropUsersTable();

        // Hibernate
        System.out.println("*-*-*-*-*-*-*-*-*-*    Hibernate   *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

        userService.createUsersTable();
        User user5 = new User("Mirbek", "Abylov", (byte) 35);
        User user6 = new User("Nurbek", "Kamilov", (byte) 30);
        User user7 = new User("Taalaibek", "Muratov", (byte) 25);
        User user8 = new User("Ulukmyrza", "Talipov", (byte) 30);
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());
        userService.saveUser(user6.getName(), user6.getLastName(), user6.getAge());
        userService.saveUser(user7.getName(), user7.getLastName(), user7.getAge());
        userService.saveUser(user8.getName(), user8.getLastName(), user8.getAge());

        userService.getAllUsers();
        userService.removeUserById(3L);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
