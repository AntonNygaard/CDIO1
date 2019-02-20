import java.util.Scanner;

public class Menu {
    Scanner s = new Scanner(System.in);
    EditUser editUser = new EditUser();
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
                editUser.createUser();
                break;
            case 2:
                editUser.accessUser_input();
                break;
            case 3:
                editUser.updateUser();
                break;
            case 4:
                editUser.deleteUser();
                break;
            case 5:
                editUser.printAllUsers();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                showMenu();
        }
    }
}