package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements IUserDAO {

    @Override
    public UserDTO getUser(int userId) throws DALException {
        UserDTO userdto = new UserDTO();
        String getUserSQL = String.format("SELECT * FROM users WHERE userid='%s'",userId);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            ResultSet getUser = statement.executeQuery(getUserSQL);
            while (getUser.next()){
                userdto.setUserName(getUser.getString("userName"));
                userdto.setUserId(getUser.getInt("userid"));
                userdto.setIni(getUser.getString("ini"));
                userdto.setCpr(getUser.getString("cpr"));
                userdto.setPassword(getUser.getString("userPassword"));
                userdto.setRole(getUser.getString("role"));
            }
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
        return userdto;
    }
    @Override
    public List<UserDTO> getUserList() throws DALException {
        ArrayList userList = new ArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                userList.add("UserID: " +resultSet.getString(1) + ", Navn: " + resultSet.getString(2) + ", Initialer: " + resultSet.getString(3) + ", CPR: " + resultSet.getString(4) + ", Password: " + resultSet.getString(5));
            }
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public void createUser(UserDTO user) throws DALException {
        String createUserSQL = String.format(
                "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s');",
                user.getUserId(),user.getUserName(),user.getIni(),user.getCpr(),user.getPassword(),user.getRole());
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createUserSQL);
            System.out.println("------------------------------------------------\n" +
                    "Bruger lavet\n" +
                    "------------------------------------------------");
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost...
            System.out.println("------------------------------------------------\n" +
                    "FEJL: UserID allerede taget\n" +
                    "------------------------------------------------");
        }
    }
    @Override
    public void updateUser(UserDTO user) throws DALException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            String column = "";
            String choice = "";
            if (user.getUserName()!= null) {
                column = "userName";
                choice = user.getUserName();
            }
            else if (user.getIni()!= null) {
                column = "ini";
                choice = user.getIni();
            }
            else if (user.getCpr()!= null) {
                column = "cpr";
                choice = user.getIni();
            }
            else if (user.getPassword()!= null) {
                column = "userPassword";
                choice = user.getPassword();
            }
            else if (user.getRole() != null) {
                column = "rolle";
                choice = user.getRole();
            }
            Statement statement = connection.createStatement();
            String update_user = String.format("UPDATE users SET %s='%s' WHERE userid='%s';",column,choice,user.getUserId());
            statement.executeUpdate(update_user);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(int userId) throws DALException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String SQL_String = String.format("DELETE FROM users WHERE userid='%s'",userId);
            statement.executeUpdate(SQL_String);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
    public List getRolesList() {
        ArrayList<String> roleList = new ArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT userrole FROM roles");
            while (resultSet.next()){
                roleList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
        return roleList;
    }
}
