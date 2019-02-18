import dal.DAO;

import java.util.Scanner;

public class Menu {
    public void showMenu() {
        DAO dao = new DAO();
        Scanner s = new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("1 Tilføj bruger");
        System.out.println("2 Se bruger");

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
                System.exit(0);
                break;
            default:
                showMenu();
        }
    }
}
