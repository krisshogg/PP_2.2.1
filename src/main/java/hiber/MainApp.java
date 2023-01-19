package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Name1", "lastName1", "lalal1@mail.ru");
      User user2 = new User("Name2", "lastName2", "blablabla2@gmail.com");
      User user3 = new User("Name3", "lastName3", "lalal3@mail.ru");
      User user4 = new User("Name4", "lastName4", "blablabla4@gmail.com");

      Car car1 = new Car("Car1", 11);
      Car car2 = new Car("Car2", 22);
      Car car3 = new Car("Car3", 33);
      Car car4 = new Car("Car4", 44);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserCar("Car2", 22));

      try {
         User userNorFound = userService.getUserCar("Car55", 55);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
