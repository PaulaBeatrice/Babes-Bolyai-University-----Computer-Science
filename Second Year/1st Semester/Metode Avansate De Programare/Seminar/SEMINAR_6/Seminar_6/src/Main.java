import domain.User;
import repository.UserDBRepo;

public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/academic";
        UserDBRepo userDBRepo = new UserDBRepo(URL, "postgres", "postgress");

        User user =new User("Apostol", "Mihai");
        userDBRepo.save(user);
        userDBRepo.findall().forEach(System.out::println);
    }

}