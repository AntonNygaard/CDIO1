import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplS133967 implements IUserDAOS133967, Serializable{
    private static final long serialVersionUID = 4545864587995944260L;
    /*
    private int	userId;
    private String userName;
    private String ini;
    private List<String> roles;
    //TODO Add relevant fields

    public UserDAOImplS133967() {
        this.roles = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIni() {
        return ini;
    }
    public void setIni(String ini) {
        this.ini = ini;
    }

    public List<String> getRoles() {
        return roles;
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
    /*
    public boolean removeRole(String role){
        return this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "UserDAOImplS133967 [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
    }
    */

    @Override
    public UserDTOS133967 getUser(int userId) throws DALException {
        UserDTOS133967 user = new UserDTOS133967();
        //user.setUserId(userId);

        String sql = "SELECT user.id AS id, user.first_name AS name, user.ini AS ini" +
                " FROM user" +
                " WHERE user.id = ?";
        UserDTOS133967 returnedUser = querryUserWithoutRoles(sql, user, userId);

        sql = "SELECT role_user.user_role AS role" +
              " FROM role_user" +
              " WHERE role_user.user_id = ?";
        user = querryUserRoles(sql, user);
        return user;
    }

    @Override
    public List<UserDTOS133967> getUserList() throws DALException {
        String sql = "SELECT user.id AS id" +
                " FROM user";
        List<Integer> ids = querryUserIds(sql);
        List<UserDTOS133967> users = new ArrayList<>();
        for(Integer id : ids){
            try {
                UserDTOS133967 user = getUser(id);
                users.add( user );
            } catch (DALException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void createUser(UserDTOS133967 user) throws DALException {
        String sql = "INSERT INTO user (id, first_name, ini)" +
                "VALUES ( ? , ? , ?)";
        updateIndhold(sql, user.getUserId(), user.getUserName(), user.getIni());

        String sql4 = "INSERT INTO role_user (user_id, user_role)" +
                "VALUES ( ? , ? )";
        for (String role : user.getRoles()){
            updateIndhold(sql4, user.getUserId(), role);
        }
    }

    @Override
    public void updateUser(UserDTOS133967 user) throws DALException {
        String sql = "UPDATE user" +
                    " SET first_name = ?" +
                    " WHERE id = ?";
        updateIndhold(sql, user.getUserName(), user.getUserId());

        //DELETE FROM role_user WHERE  user_id = 1;
        String sql2 = "DELETE FROM role_user WHERE user_id = ?";
        updateIndhold(sql2, user.getUserId());

        String sql3 = "INSERT INTO role_user (user_id, user_role)" +
                "VALUES ( ? , ? )";
        for (String role : user.getRoles()){
            updateIndhold(sql3, user.getUserId(), role);
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        //DELETE FROM role_user WHERE  user_id = 1;
        String sql = "DELETE FROM role_user WHERE user_id = ?";
        updateIndhold(sql, userId);

        //DELETE FROM user WHERE id = 1
        String sql3 = "DELETE FROM user WHERE id = ?";
        updateIndhold(sql3, userId);
    }

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s133967";

    //  Database credentials
    static final String USER = "s133967";
    static final String PASS = "8JPOJuQcgUpUVIVHY4S2H";

    private UserDTOS133967 querryUserWithoutRoles(String sql, UserDTOS133967 userDTOS133967, int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            pstmt = conn.prepareStatement(sql);
            //String sql = "SELECT id, first, last, age FROM Student";
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String firstName = rs.getString("name");
                String ini = rs.getString("ini");

                userDTOS133967.setUserId(id);
                userDTOS133967.setUserName(firstName);
                userDTOS133967.setIni(ini);
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
        return userDTOS133967;
    }

    private UserDTOS133967 querryUserRoles(String sql, UserDTOS133967 userDTOS133967) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userDTOS133967.getUserId());
            ResultSet rs = pstmt.executeQuery();

            //STEP 5: Extract data from result set
            List<String> roles = new ArrayList<>();
            while(rs.next()){
                //Retrieve by column name
                String role = rs.getString("role");
                roles.add(role);
            }
            userDTOS133967.setRoles(roles);
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
        return userDTOS133967;
    }

    private void updateIndhold(String sql, int param01, String param02, String param03) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Updating records in the table...");
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, param01);
            pstmt.setString(2, param02);
            pstmt.setString(3, param03);
            pstmt.executeUpdate();

            //System.out.println("Updating records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
    }
    private void updateIndhold(String sql, int param01, String param02) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Updating records in the table...");
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, param01);
            pstmt.setString(2, param02);
            pstmt.executeUpdate();

            //System.out.println("Updating records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
    }
    private void updateIndhold(String sql, String param01, int param02) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Updating records in the table...");
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, param01);
            pstmt.setInt(2, param02);
            pstmt.executeUpdate();

            //System.out.println("Updating records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
       // System.out.println("Goodbye!");
    }

    private void updateIndhold(String sql, int param01) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Updating records in the table...");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, param01);
            pstmt.executeUpdate();

            //System.out.println("Updating records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
    }

    public void addRolesToRoleTable(List<String> roles) {

        for(String role : roles){
            String sql = "INSERT INTO role (name)" +
                    "VALUES ( ? )";
            updateIndhold(sql, role);
        }


    }

    private void updateIndhold(String sql, String param01) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Inserting records into the table...");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, param01);
            pstmt.executeUpdate();

            //System.out.println("Inserted records into the table...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye from adding a role");
    }

    private List<Integer> querryUserIds(String sql) {
        Connection conn = null;
        Statement stmt = null;
        List<Integer> ids = new ArrayList<>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                ids.add(id);
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye!");
        return ids;
    }

    public boolean isInUse(String ini) {
        String sql = "SELECT ini FROM user";
        List<String> inits = queryStringParams(sql, "ini");
        return inits.contains(ini);
    }

    private List<String> queryStringParams(String sql, String key) {
        Connection conn = null;
        Statement stmt = null;
        List<String> params = new ArrayList<>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //String sql = "SELECT name FROM role";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String param = rs.getString(key);
                params.add(param);
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //System.out.println("Goodbye! key: " + key);
        return params;
    }

    public List<String> queryAllRoles() {
        String sql = "SELECT name FROM role";
        return queryStringParams(sql, "name");
    }
}