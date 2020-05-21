package onlab.MyFitnessApp;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;

@SpringBootApplication
public class MyFitnessAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFitnessAppApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
				User user1 = new User("user1", "password");
				User user2 = new User("user2", "password");
				User user3 = new User("user3", "password");
				userRepository.save(user1);
				userRepository.save(user2);
				userRepository.save(user3);
		};
	}*/
}
