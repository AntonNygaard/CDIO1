package dal;

import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class DAO implements IUserDAO {
    UserDTO userdto = new UserDTO();
    Scanner s = new Scanner(System.in);

    @Override
    public void getUser(int userId) {
        userdto.toString(userId);
    }

    @Override
    public List<UserDTO> getUserList() {
        return null;
    }

    @Override
    public void createUser() {
        System.out.println("Indtast userID:");
        int newUserId = s.nextInt();
        userdto.setUserId(newUserId);

        System.out.println("Indtast username:");
        String newUserName = s.next();
        userdto.setUserName(newUserId,newUserName);

        System.out.println("Indtast initialer:");
        String newIni = "abcdefg";
        while(newIni.length() > 4 || newIni.length() < 1) {
            newIni = s.next();
            if (newIni.length() > 4 && newIni.length() < 1) {
                System.out.println("Dine initialer skal være mellem 1 og 4 tegn, prøv igen");
            }
            else {
                userdto.setIni(newUserId,newIni);
            }
        }

        System.out.println("Indtast dit cpr nummer");
        String newCpr = "a";
        while (newCpr.length() != 10) {
            newCpr = s.next();
            if (newCpr.length() != 10) {
                System.out.println("Et cpr nummer er 10 tegn langt, prøv igen");
            }
            else {
                userdto.setCpr(newUserId,newCpr);
            }
        }

        // TODO: Lav random password generator:
        System.out.println("Indtast password:");
        String newPassword = "abc";
        while(newPassword.length() < 6 || newPassword.length() > 50) {
            newPassword = s.next();
            if (newPassword.length() < 6 && newPassword.length() > 50) {
                System.out.println("Dit password skal være mellem 6 og 50 tegn, prøv igen");
            }
            else {
                userdto.setPassword(newUserId,newPassword);
            }
        }


    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}