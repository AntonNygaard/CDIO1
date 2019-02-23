import java.util.Scanner;

public class Menu {
    Scanner s = new Scanner(System.in);
    EditUser editUser = new EditUser();
    public void showMenu() {
        System.out.println("-----------------------");
        System.out.println("Tast 1 for at tilføje en bruger");
        System.out.println("Tast 2 for at se en bruger");
        System.out.println("Tast 3 for at redigere en bruger");
        System.out.println("Tast 4 for at slette en bruger");
        System.out.println("Tast 5 for at see alle brugere");
        System.out.println("Tast 6 for at gå ud af programmet");

        System.out.print("Indtast valg: ");
        String menuItem;
        int menuItemFinal;
        while (true) {
            menuItem = s.next();
            if (editUser.isThisAnInt(menuItem)) {
                menuItemFinal = Integer.parseInt(menuItem);
                if (menuItemFinal >= 1 && menuItemFinal <= 6) {
                    break;
                }
            }
            System.out.println(menuItem + " er ikke en mulighed. Indtast venligst et tal mellem 1 og 6.");
        }
        System.out.print("\n");

        switch (menuItemFinal) {
            case 1:
                editUser.createUser();
                break;
            case 2:
                editUser.accessUser();
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