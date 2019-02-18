<<<<<<< HEAD:CDIO1/src/main/java/dto/UserDTO.java
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
=======
package dto;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private List<String> roles;
	//TODO Add relevant fields


	public UserDTO() {
		this.roles = new ArrayList<>();
	}

	public int getUserId(int userId) {
	    // TODO´´
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            String getSQL_ID = String.format("SELECT userid FROM users WHERE userid='%s'",userId);
            ResultSet SQL_ID = statement.executeQuery(getSQL_ID);
            while (SQL_ID.next()){
                int get_userID = SQL_ID.getInt("userid");
                return get_userID;
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
        return 0;
	}
	public void setUserId(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_ID = String.format("INSERT INTO users (userid) VALUES ('%s');", userId);
            statement.executeUpdate(addSQL_ID);

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
	public String getUserName(int userId) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                    + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
                Statement statement = connection.createStatement();
                String getSQL_UserName = String.format("SELECT userName FROM users WHERE userid='%s'",userId);
                ResultSet SQL_UserName = statement.executeQuery(getSQL_UserName);
                while (SQL_UserName.next()){
                    String get_UserName = SQL_UserName.getString("userName");
                    return get_UserName;
                }

            } catch (SQLException e) {
                //Remember to handle Exceptions gracefully! Connection might be Lost....
                e.printStackTrace();
            }
            return "Fejl";
	}
	public void setUserName(int userId, String userName) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_ID = String.format("UPDATE users SET userName='%s' WHERE userid='%s';", userName,userId);
            statement.executeUpdate(addSQL_ID);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
	public String getIni(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            String getSQL_Ini = String.format("SELECT ini FROM users WHERE userid='%s'",userId);
            ResultSet SQL_Ini = statement.executeQuery(getSQL_Ini);
            while (SQL_Ini.next()){
                String get_Ini = SQL_Ini.getString("ini");
                return get_Ini;
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
        return "Fejl";
	}
	public void setIni(int userId, String ini) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_Ini = String.format("UPDATE users SET ini='%s' WHERE userid='%s';", ini,userId);
            statement.executeUpdate(addSQL_Ini);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
	}
    public String getCpr(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            String getSQL_Cpr = String.format("SELECT cpr FROM users WHERE userid='%s'",userId);
            ResultSet SQL_Cpr = statement.executeQuery(getSQL_Cpr);
            while (SQL_Cpr.next()){
                String get_Cpr = SQL_Cpr.getString("cpr");
                return get_Cpr;
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();

        }
        return "Fejl";
    }
    public void setCpr(int userId, String cpr) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_Cpr = String.format("UPDATE users SET cpr='%s' WHERE userid='%s';",cpr, userId);
            statement.executeUpdate(addSQL_Cpr);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
    public String getPassword(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            String getSQL_Password = String.format("SELECT password FROM users WHERE userid='%s'",userId);
            ResultSet SQL_Password = statement.executeQuery(getSQL_Password);
            while (SQL_Password.next()){
                String get_Password = SQL_Password.getString("userPassword");
                return get_Password;
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();

        }
        return "Fejl";
    }
    public void setPassword(int userId,String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_Password = String.format("UPDATE users SET userPassword='%s' WHERE userid='%s';", password,userId);
            statement.executeUpdate(addSQL_Password);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }
    public void getRoles() {
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

	public String toString(int userId) {
		return "UserDTO [userId=" + getUserId(userId) + ", userName=" + getUserName(userId) + ", ini=" + getIni(userId) + "]";
	}



}
>>>>>>> Fikset diverse:CDIO1/src/dto/UserDTO.java
