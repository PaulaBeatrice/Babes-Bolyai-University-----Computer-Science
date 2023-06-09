
import domain.validator.FriendshipValidator;
import domain.validator.UserValidator;
import repository.database.FriendshipDBRepo;
import repository.database.UserDBRepo;
import service.Network;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        UserDBRepo userDBRepo = new UserDBRepo(new UserValidator());
        FriendshipDBRepo repoFriendship = new FriendshipDBRepo(new FriendshipValidator(userDBRepo));
        Network network = new Network(repoFriendship, userDBRepo);
        Console console = new Console(network);
        console.run();
    }
}