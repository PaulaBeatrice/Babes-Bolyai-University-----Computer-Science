package ui;

import domain.Friendship;
import domain.User;
import domain.validator.ValidationException;
import service.Community;
import service.Network;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import static utils.Constants.DATE_TIME_FORMATTER;

public class Console {
    private Network network;
    private Community community;
    public Console(Network network)
    {
        this.network = network;
        this.community = new Community(network);
    }

    void printMenu()
    {
        System.out.println("********************************************************");
        System.out.println("0. Exit");
        System.out.println("1. Add User");
        System.out.println("2. Delete User");
        System.out.println("3. Add Friendship");
        System.out.println("4. Delete Friendship");
        System.out.println("5. Print Users");
        System.out.println("6. Print Friendships");
        System.out.println("7. Get Communities");
        System.out.println("8. Get The Most Sociable Community");
        System.out.println("9. Update User");
        System.out.println("10. Update Friendship");
        System.out.println(">>>>> ");
    }

    public void run()
    {
        Scanner scan=new Scanner(System.in);
        label:
        while(true)
        {
            printMenu();
            String cmd = scan.nextLine();
            System.out.println("********************************************************");
            switch (cmd) {
                case "1":
                    addUser();
                    break;
                case "2":
                    deleteUser();
                    break;
                case "3":
                    addFriendship();
                    break;
                case "4":
                    deleteFriendship();
                    break;
                case "5":
                    printUser();
                    break;
                case "6":
                    printFriendship();
                    break;
                case "7":
                    community.connectedComponents();
                    break;
                case "8":
                    community.getLongest();
                    break;
                case "9":
                    updateUser();
                    break;
                case "10":
                    updateFriendship();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("COMANDA INVALIDA!");
                    break;
            }
        }
    }

    void printUser()
    {
        for (User u : network.getUsers())
        {
            System.out.println("Id: " + u.getId() + "; firstName: " + u.getFirstName() + "; lastName: " + u.getLastName() );
        }
    }

    void addUser()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = scan.nextLine();
        System.out.println("Last Name: ");
        String lastName = scan.nextLine();
        try {
            network.addUser(new User(firstName, lastName));
        }
        catch (ValidationException e){ System.out.println("Invalid user!");}
        catch (IllegalArgumentException e) {System.out.println("Invalid arguments!");}
    }

    void deleteUser()
    {
        printUser();
        Scanner scan=new Scanner(System.in);
        System.out.println("Id: ");
        String val = scan.nextLine();
        try {
            Long id =  Long.parseLong(val);
            network.deleteUser(id);
        }
        catch (IllegalArgumentException e) {System.out.println("The id can not contain letters or symbols and can not be empty!!");}
    }

    void addFriendship()
    {
        printUser();
        Scanner scan=new Scanner(System.in);
        System.out.println("Id-ul primului user: ");
        String val = scan.nextLine();
        System.out.println("Id-ul celui de-al doilea user: ");
        String val2 = scan.nextLine();
        try{
            long id1 = 0L;
            long id2 = 0L;
            try{
                 id1 =  Long.parseLong(val);
                 id2 =  Long.parseLong(val2);
            }
            catch(IllegalArgumentException e) {System.out.println("The id can not contain letters or symbols!");}
           // LocalDateTime date = LocalDateTime.parse(LocalDateTime.now(), DATE_TIME_FORMATTER);
            //LocalDateTime data = LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER));
            network.addFriendship(new Friendship(id1, id2, LocalDateTime.now()));
        }
        catch (ValidationException e){System.out.println("The friendship is invalid!");}
        catch (IllegalArgumentException e) {System.out.println("Invalid arguments!");}
    }

    void printFriendship()
    {
        for (Friendship u : network.getFriendships())
        {
            System.out.println("Id: " + u.getId() + "; User1: " + u.getIdUser1() + "; User2: " + u.getIdUser2() + "; friends From: " + u.getFriendsFrom().getYear() + " " + u.getFriendsFrom().getMonth() + " " + u.getFriendsFrom().getDayOfMonth());
        }
    }

    void deleteFriendship()
    {
        printFriendship();
        Scanner scan=new Scanner(System.in);
        System.out.println("Id friendship: ");
        String val = scan.nextLine();
        Long id1 =  Long.parseLong(val);
        try
        {
            network.deleteFriendship(id1);
        }
        catch (IllegalArgumentException e) {System.out.println("Invalid arguments!");}
    }

    void updateUser(){
        Scanner scan=new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = scan.nextLine();
        System.out.println("Last Name: ");
        String lastName = scan.nextLine();
        System.out.println("Id:");
        String id = scan.nextLine();
        try {
            User user = new User(firstName, lastName);
            user.setId(Long.parseLong(id));
            network.updateUser(user);
        }
        catch (ValidationException e){ System.out.println("Invalid user!");}
        catch (IllegalArgumentException e) {System.out.println("Invalid arguments!");}
    }

    void updateFriendship(){
        printFriendship();
        Scanner scan=new Scanner(System.in);
        System.out.println("Id-ul primului user: ");
        String val = scan.nextLine();
        System.out.println("Id-ul celui de-al doilea user: ");
        String val2 = scan.nextLine();
        System.out.println("Id:");
        String id = scan.nextLine();
        try{
            long id1 = 0L;
            long id2 = 0L;
            long frID = 0L;
            try{
                id1 =  Long.parseLong(val);
                id2 =  Long.parseLong(val2);
                frID = Long.parseLong(id);
            }
            catch(IllegalArgumentException e) {System.out.println("The id can not contain letters or symbols!");}
            // LocalDateTime date = LocalDateTime.parse(LocalDateTime.now(), DATE_TIME_FORMATTER);
            //LocalDateTime data = LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER));
            Friendship friendship = new Friendship(id1, id2, LocalDateTime.now());
            friendship.setId(frID);
            network.updateFriendship(friendship);
        }
        catch (ValidationException e){System.out.println("The friendship is invalid!");}
        catch (IllegalArgumentException e) {System.out.println("Invalid arguments!");}
    }
}
