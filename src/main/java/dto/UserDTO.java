package dto;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private List<String> roles;


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
            String getSQL_Password = String.format("SELECT userPassword FROM users WHERE userid='%s'",userId);
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
    public String getRoles(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")){
            Statement statement = connection.createStatement();
            String getSQL_Roles = String.format("SELECT roles FROM users WHERE userid='%s'",userId);
            ResultSet SQL_Roles = statement.executeQuery(getSQL_Roles);
            while (SQL_Roles.next()){
                String get_Roles = SQL_Roles.getString("roles");
                return get_Roles;
            }

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();

        }
        return "Fejl";
	}
	public void setRoles(String role, int userID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String addSQL_Role = String.format("UPDATE users SET roles='%s' WHERE userid='%s';",role, userID);
            statement.executeUpdate(addSQL_Role);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
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
		return "UserDTO [userId=" + getUserId(userId) + ", userName=" + getUserName(userId) + ", ini=" + getIni(userId) + ", cpr=" + getCpr(userId) + ", password=" + getPassword(userId) + ", rolle=" + getRoles(userId) + "]";
	}

	public void deleteUser(int userID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            String deleteSQL_user = String.format("DELETE FROM users WHERE userid='%s'",userID);
            statement.executeUpdate(deleteSQL_user);
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }

    public void seeAllUsers() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s170429?"
                + "user=s170429&password=4PPt5j8rsEIUnrBq8G0iE")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getString(4) + ", " + resultSet.getString(5));
            }
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
        }
    }



}
