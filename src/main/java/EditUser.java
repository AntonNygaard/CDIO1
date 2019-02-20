import dal.DAO;
import dal.IUserDAO;
import dal.DAO;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class EditUser {
    DAO UserDAO = new DAO();
    Scanner s = new Scanner(System.in);

    public void deleteUser() {
        System.out.println("Indtast id på den bruger du ønsker slettet:");
        try {
            UserDTO deleteUser = UserDAO.getUser(s.nextInt());
            UserDAO.deleteUser(deleteUser.getUserId());
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Fejl");
        }

    }
    public void createUser() {
        UserDTO user = new UserDTO();
        setID(user);
        setUsername(user);
        setIni(user);
        setPassword(user);
        setCpr(user);
        setRole(user);
        try {
            UserDAO.createUser(user);
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Error");
        }
    }
    public void accessUser_input() {
        System.out.println("Indtast userID på den bruger du gerne vil finde information om:");
        try {
            UserDTO getUser = UserDAO.getUser(s.nextInt());
            System.out.println(getUser.toString());
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Error");
        }
    }
    public void updateUser() {
        UserDTO tempUser = new UserDTO();
        System.out.println("Indtast ID på personen du ønsker ændret");
        tempUser.setUserId(s.nextInt());
        System.out.println("-----------------------");
        System.out.println("1 Skift username");
        System.out.println("2 Skift initialer");
        System.out.println("3 Skift cpr");
        System.out.println("4 Skift password");
        System.out.println("5 Skift rolle");

        System.out.println("Indtast valg:");
        String choice;
        int intChoice;
        while (true) {
            choice = s.next();
            if (isThisAnInt(choice)) {
                intChoice = Integer.parseInt(choice);
                if (intChoice > 0 && intChoice < 6) {
                    int correctStringCheck = intChoice;
                    break;
                }
                else {
                    System.out.println("Værdien du indtastede er ikke et gyldigt valg, prøv igen");
                }
            }
            else {
                System.out.println("Indtast venligst en integer værdi");
            }
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
            UserDAO.updateUser(tempUser);
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Fejl");
        }

    }
    public void printAllUsers() {
        try {
            List allUsers = UserDAO.getUserList();
            for (int i=0; i<allUsers.size();i++) {
                System.out.println(allUsers.get(i));
            }
        }
        catch (IUserDAO.DALException e) {
            System.out.println("Fejl");
        }
    }
    public UserDTO setPassword(UserDTO user) {
        System.out.println("Indtast ønskede password:");
        String newUserPassword;
        char newUserPasswordCheck;
        while (true) {
            boolean capitalCheck = false;
            boolean lowerCaseCheck = false;
            boolean numberCheck = false;
            boolean specialCharCheck = false;
            newUserPassword = s.next();
            if (newUserPassword.length()>=6 && newUserPassword.length()<=50) {
                for (int j=0; j < newUserPassword.length(); j++) {
                    newUserPasswordCheck = newUserPassword.charAt(j);
                    if (Character.isDigit(newUserPasswordCheck)) {
                        numberCheck = true;
                    }
                    else if (Character.isUpperCase(newUserPasswordCheck)) {
                        capitalCheck = true;
                    }
                    else if (Character.isLowerCase(newUserPasswordCheck)) {
                        lowerCaseCheck = true;
                    }
                    else if (newUserPasswordCheck == '.' || newUserPasswordCheck == '-' || newUserPasswordCheck == '_' ||
                            newUserPasswordCheck == '+' || + newUserPasswordCheck == '!' || newUserPasswordCheck == '?' || newUserPasswordCheck == '=') {
                        specialCharCheck = true;
                    }
                }
            }
            int requirementCheck = 0;
            if (numberCheck) {
                requirementCheck += 1;
            }
            if (capitalCheck) {
                requirementCheck += 1;
            }
            if (lowerCaseCheck) {
                requirementCheck += 1;
            }
            if (specialCharCheck) {
                requirementCheck += 1;
            }

            if (requirementCheck>=3) {
                break;
            }
            else {
                System.out.println("Indtast venligst et password der opfylder kravene for DTU");
            }
        }

        user.setPassword(newUserPassword);
        return user;
    }
    public UserDTO setID(UserDTO user) {
        System.out.println("Indtast ønskede ID");
        String newUserID = "";
        while(true) {
            newUserID = s.next();
            if (isThisAnInt(newUserID) == true && Integer.parseInt(newUserID) > 10 && Integer.parseInt(newUserID) < 100) {
                break;
            }
            else {
                System.out.println("Indtast venligst en integer mellem 11 og 99");
            }
        }

        user.setUserId(Integer.parseInt(newUserID));
        return user;
    }
    public UserDTO setUsername(UserDTO user) {
        System.out.println("Indtast ønskede username (Mellem 4 og 20 tegn, kun bogstaver og tal)):");
        String newUserUsername;
        while (true) {
            newUserUsername = s.next();
            char newUserUsernameCheck;
            if (newUserUsername.length()>=4 && newUserUsername.length()<=20) {
                for (int j = 0; j < newUserUsername.length(); j++) {
                    newUserUsernameCheck = newUserUsername.charAt(j);
                    if (Character.isDigit(newUserUsernameCheck) || Character.isLetter(newUserUsernameCheck)) {
                        continue;
                    } else {
                        System.out.println("Dit username må kun indeholde tal og bogstaver!");
                        setUsername(user);
                    }
                }
                break;
            }
            else {
                System.out.println("Indtast venligst et username mellem 4 og 20 tegn");
            }
        }
        user.setUserName(newUserUsername);
        return user;
    }
    public UserDTO setIni(UserDTO user) {
        System.out.println("Indtast ønskede initialer:");
        String newUserIni;
        while (true) {
            newUserIni = s.next();
            if (isThisAString(newUserIni) == true && newUserIni.length()>=2 && newUserIni.length()<=4) {
                break;
            }
            else {
                System.out.println("Vælg venligst initialer der kun består af bogstaver og er mellem 2 og 4 tegn");
            }
        }

        user.setIni(newUserIni);
        return user;
    }
    public UserDTO setCpr(UserDTO user) {
        System.out.println("Indtast ønskede CPR:");
        String newUserCpr;
        while (true) {
            newUserCpr = s.next();
            if (isThisAnInt(newUserCpr) == true && newUserCpr.length() == 10) {
                break;
            }
            else {
                System.out.println("Vælg venligst et cpr nummer der kun består af tal og er præcis 10 tegn langt");
            }
        }
        user.setCpr(newUserCpr);
        return user;
    }
    public UserDTO setRole(UserDTO user) {
        System.out.println("Vælg rolle på brugeren:");
        List<String> roleList = UserDAO.getRolesList();
        for (int i=0; i<roleList.size(); i++) {
            System.out.println((i+1) + " " + roleList.get(i));
        }
        int choice = -100;
        while(choice >= roleList.size() || choice < 1) {
            choice = s.nextInt();
            if (choice > roleList.size()|| choice < 0 ) {
                System.out.println("Vælg venligst et tal mellem 1 og " + roleList.size());
            }
        }
        user.setRole(roleList.get(choice-1));
        return user;
    }
    // Fundet online
    public boolean isThisAnInt(String s) {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    // Fundet online
    public boolean isThisAString(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}
