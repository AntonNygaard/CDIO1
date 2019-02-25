import dal.DAO;
import dal.IUserDAO;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner s = new Scanner(System.in);
    DAO dao = new DAO();
    ConsoleView view = new ConsoleView();
    OfficerRequirement requirement = new OfficerRequirement();
    public void showMenu() {
        view.print("-----------------------");
        view.print("Tast 1 for at tilføje en bruger");
        view.print("Tast 2 for at se en bruger");
        view.print("Tast 3 for at redigere en bruger");
        view.print("Tast 4 for at slette en bruger");
        view.print("Tast 5 for at see alle brugere");
        view.print("Tast 6 for at gå ud af programmet");
        String menuItem;
        int menuItemFinal;
        while (true) {
            menuItem = view.getUserInput("Indtast valg: ");
            if (requirement.intWithinBoundaryChecker(menuItem,1,6)) {
                menuItemFinal = Integer.parseInt(menuItem);
                break;
            }
            else view.print(menuItem + " er ikke en mulighed. Indtast venligst et tal mellem 1 og 6.");
        }
        view.print("\n");
        switch (menuItemFinal) {
            case 1:
                createUser();
                break;
            case 2:
                seeUser();
                break;
            case 3:
                editUser();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                printAllUsers();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                showMenu();
        }
    }

    public void createUser() {
        UserDTO newUser = new UserDTO();

        // Input af ID
        newUser = setID(newUser);

        // Input af username
        newUser = setUsername(newUser);

        // Input af initialer
        newUser = setIni(newUser);

        // Input af cpr
        newUser = setCpr(newUser);

        // Input af password
        newUser = setPassword(newUser);

        // Input af rolle
        setRole(newUser);

        try {
            dao.createUser(newUser);
        }
        catch (IUserDAO.DALException e) {
            view.print("Unexpected error");
        }
    }
    public void seeUser() {
        while (true) {
            String editUserInput = view.getUserInput("Indtast ID på den person du gerne vil se:");
            if (requirement.intWithinBoundaryChecker(editUserInput,11,99)) {
                int editUserInputInt = Integer.parseInt(editUserInput);
                try {
                    UserDTO getUser = dao.getUser(editUserInputInt);
                    view.print(getUser.toString());
                }
                catch (IUserDAO.DALException e) {
                }
            }
            else view.print("Ugyldigt ID, prøv igen:");
        }
    }
    public UserDTO setRole(UserDTO user) {
        List<String> roleList = dao.getRolesList();
        for (int i=0; i<roleList.size(); i++) {
            view.print((i+1) + " " + roleList.get(i));
        }
        String choice;
        while(true) {
            choice = view.getUserInput("Vælg rolle på brugeren:");
            if (requirement.intWithinBoundaryChecker(choice,0,roleList.size()-1)) {
                int intChoice = Integer.parseInt(choice);
                user.setRole(roleList.get(intChoice-1));
                break;
            }
            else view.print("Vælg venligst en integer værdi mellem 1 og " + roleList.size());
        }
        return user;
    }
    public void printAllUsers() {
        try {
            List allUsers = dao.getUserList();
            for (int i=0; i<allUsers.size();i++) {
                view.print((String)allUsers.get(i));
            }
        }
        catch (IUserDAO.DALException e) {
        }
        // TODO: Find bedre løsning her -v

    }
    public void deleteUser() {
        while (true) {
            String editUserInput = view.getUserInput("Indtast ID på den bruger du gerne vil slette.");
            if(requirement.intWithinBoundaryChecker(editUserInput,11,99)) {
                int editUserInputInt = Integer.parseInt(editUserInput);
                try {
                    dao.deleteUser(editUserInputInt);
                    view.print("Brugeren er slettet");
                }
                catch (IUserDAO.DALException e) {
                    view.print("Unexpected error");
                }
            }
        }
    }
    public void editUser() {
        UserDTO tempUser = new UserDTO();
        tempUser = setID(tempUser);
        view.print("-----------------------");
        view.print("1 Skift username");
        view.print("2 Skift initialer");
        view.print("3 Skift cpr");
        view.print("4 Skift password");
        view.print("5 Skift rolle");

        String choice;
        int intChoice;
        while (true) {
            choice = view.getUserInput("Indtast valg:");
            if (requirement.intWithinBoundaryChecker(choice,1,5)) {
                intChoice = Integer.parseInt(choice);
                break;
            }
            else view.print("Indtast venligst en gyldig værdi mellem 1 og 5");
        }
        switch (intChoice) {
            case 1: setUsername(tempUser); break;
            case 2: setIni(tempUser); break;
            case 3: setCpr(tempUser); break;
            case 4: setPassword(tempUser); break;
            case 5: setRole(tempUser); break;
            default:
                System.out.println("Error");
        }
        try {
            dao.updateUser(tempUser);
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Fejl");
        }
    }


    public UserDTO setID(UserDTO user) {
        while (true) {
            String newUserID = view.getUserInput("Indtast ID mellem 11 og 99 til den nye bruger");
            if (requirement.IDChecker(newUserID)) {
                user.setUserId(Integer.parseInt(newUserID));
                return user;
            }
            else {
                view.print("Indtastede værdi overholder ikke kravene.");
            }
        }
    }
    public UserDTO setUsername(UserDTO user) {
        while (true) {
            String newUserUsername = view.getUserInput("Indtast et username (mellem 6 og 20 tegn) til den bruger:");
            if (requirement.usernameChecker(newUserUsername)) {
                user.setUserName(newUserUsername);
                return user;
            }
            else view.print("Indtast venligst et username der opfylder kravene");
        }
    }
    public UserDTO setIni(UserDTO user) {
        while(true) {
            String newUserIni = view.getUserInput("Indtast initialer til den nye bruger (kun bogstaver og mellem 2 og 4 tegn");
            if (requirement.iniChecker(newUserIni)) {
                user.setIni(newUserIni);
                return user;
            }
            else view.print("Indtast venligst initialer der opfylder kravene");
        }
    }
    public UserDTO setCpr(UserDTO user) {
        while (true) {
            String newUserCpr = view.getUserInput("Indtast CPR nummer til den nye bruger:");
            if (requirement.cprChecker(newUserCpr)) {
                user.setCpr(newUserCpr);
                return user;
            }
            else view.print("Indtast venligst et rigtigt cpr nummer");
        }
    }
    public UserDTO setPassword(UserDTO user) {
        while (true) {
            String newUserPassword = view.getUserInput("Indtast password til den nye bruger:");
            if (requirement.passwordChecker(newUserPassword)) {
                user.setPassword(newUserPassword);
                return user;
            }
            else view.print("Indtast venligst et password der opfylder DTU's krav");
        }
    }
}