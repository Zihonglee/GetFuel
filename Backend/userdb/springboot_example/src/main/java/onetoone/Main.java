package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import onetoone.Users.User;
import onetoone.Users.UserRepository;


@SpringBootApplication
//@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines

    @Bean
        CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com","1234");
            User user2 = new User("Jane", "jane@somemail.com","456");
            User user3 = new User("Justin", "justin@somemail.com","789");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }

}
