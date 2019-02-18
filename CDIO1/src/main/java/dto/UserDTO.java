package dto;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName;                
	private String ini;
	private String cpr;
	private String password;
	private List<String> roles;
	//TODO Add relevant fields
	
	public UserDTO() {
		this.roles = new ArrayList<>();
	}

    public void mysqlConnection(int choice, String column, int intInput, int userID_find, String stringInput) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            switch (choice) {
                case 1: String addSQL_ID = String.format("INSERT INTO users (%s) VALUES ('%s');", column, intInput);
                    statement.executeUpdate(addSQL_ID);
                case 2: String addSQL_Int = String.format("INSERT INTO users (%s) VALUES ('%s') WHERE userid='%s';", column, intInput, userID_find);
                    statement.executeUpdate(addSQL_Int);
                case 3: String addSQLstring = String.format("INSERT INTO users (%s) VALUES ('%s') WHERE userid='%s';", stringInput);
                    statement.executeUpdate(addSQLstring);
                case 4: String updateSQL = String.format("UPDATE test SET indhold = '%s' WHERE id = '%s';");
                    statement.executeUpdate(updateSQL);
                case 5: String selectFromSql = String.format("SELECT %s FROM users WHERE userid='%s'",column, userID_find);
                    ResultSet printFromSql = statement.executeQuery(selectFromSql);
                    while (printFromSql.next()){
                        System.out.println(printFromSql.getString(1) + ": " + printFromSql.getString(2));
                    }
                case 6: String removeSQL = String.format("");
                case 7: ResultSet resultSet = statement.executeQuery("SELECT 'roles' FROM users");
                    System.out.println("Rollerne i database");
                    while (resultSet.next()){
                        System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
                    }
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();

        }
    }

	public int getUserId() {
	    // TODO
		return userId;
	}
	public void setUserId(int userId) {
		mysqlConnection(1,"userId",userId,-1,null);
	}
	public void getUserName(int userId) {
		mysqlConnection(5,"userName",-1,userId,null);
	}
	public void setUserName(int userId, String userName) {
		mysqlConnection(3,"userName",-1,userId,userName);
	}
	public void getIni(int userId) {
        mysqlConnection(5,"ini",-1,userId,null);
	}
	public void setIni(int userId, String ini) {
        mysqlConnection(3,"ini",-1,userId,ini);
	}
    public void getCpr(int userId) {
        mysqlConnection(5,"cpr",-1,userId,null);
    }
    public void setCpr(int userId, String cpr) {
        mysqlConnection(3,"cpr",-1,userId,cpr);
    }
    public void getPassword(int userId) {
        mysqlConnection(5,"userPassword",-1,userId,null);
    }
    public void setPassword(int userId,String password) {
        mysqlConnection(3,"userPassword",-1,userId,password);
    }
    public void getRoles() {
        mysqlConnection(7,null,-1,-1,null);
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public void addRole(String role){
		this.roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
	}
	
	
	
}
