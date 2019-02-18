import dal.DAO;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {
    DAO dao = new DAO();
    Scanner s = new Scanner(System.in);
    public void showMenu() {
        System.out.println("-----------------------");
        System.out.println("1 Tilføj bruger");
        System.out.println("2 Se bruger");
        System.out.println("3 Rediger bruger");
        System.out.println("4 Slet bruger");
        System.out.println("5 Se alle brugere");
        System.out.println("6 Exit program");

        System.out.print("Indtast valg: ");
        int menuItem = s.nextInt();
        System.out.print("\n");

        switch (menuItem) {
            case 1:
                dao.createUser();
                break;
            case 2:
                System.out.println("Indtast id på brugeren du vil se:");
                int getUserDataID = s.nextInt();
                dao.getUser(getUserDataID);
                break;
            case 3:
                editUser();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                dao.seeAllUsers();
            case 6:
                System.exit(0);
                break;
            default:
                showMenu();
        }
    }

    public void editUser() {
        System.out.println("-----------------------");
        System.out.println("1 Skift username");
        System.out.println("2 Skift initialer");
        System.out.println("3 Skift cpr");
        System.out.println("4 Skift password");

        System.out.println("Indtast valg:");
        int changeChoice = s.nextInt();
        System.out.println("Indtast userID:");
        int userID = s.nextInt();
        System.out.println("\n");

        switch(changeChoice) {
            case 1: dao.updateUser(1,userID);
            case 2: dao.updateUser(2,userID);
            case 3: dao.updateUser(3,userID);
            case 4: dao.updateUser(4,userID);
            default: showMenu();
        }

    }

    public void deleteUser() {
        System.out.println("Indtast userID på den bruger der ønskes slettet:");
        int userID = s.nextInt();
        dao.deleteUser(userID);
    }
}
