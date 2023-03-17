import domain.validator.FriendshipValidator;
import domain.validator.UserValidator;
import repository.file.FriendshipFileRepository;
import repository.file.UserFileRepo;
import service.Network;
import ui.Console;

public class Main
{
    public static void main(String[] args)
    {
        UserFileRepo repoUser = new UserFileRepo("data/Users.csv", new UserValidator());
        FriendshipFileRepository repoFriendship = new FriendshipFileRepository("data/Friendship.csv", new FriendshipValidator(repoUser));
        Network network = new Network(repoFriendship, repoUser);
        Console console = new Console(network);
        console.run();
    }
}