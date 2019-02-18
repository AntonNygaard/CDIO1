package dal;

import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class DAO implements IUserDAO {
    UserDTO userdto = new UserDTO();
    Scanner s = new Scanner(System.in);

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        System.out.println("Indtast userID:");
        int newUserId = s.nextInt();

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
