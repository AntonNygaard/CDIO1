import java.sql.*;
import java.util.*;

public class MainS133967 {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/testschema";//"jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s133967";

    //  Database credentials
    static final String USER = "root";//"s133967";
    static final String PASS = "";//"8JPOJuQcgUpUVIVHY4S2H";

    private static void showUserMenue() {
        System.out.format("To show the users in the database enter s/S: \n");
        System.out.format("To insert a user to the database enter i/I: \n");
        System.out.format("To update a user from the database enter u/U: \n");
        System.out.format("To delete a user from the database enter d/D: \n");
        System.out.format("To exit press q/Q: \n");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {
        showUserMenue();

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        while( !choice.trim().equalsIgnoreCase("q") ){
            if(choice.trim().equalsIgnoreCase("s")){
                UserDAOImplS133967 userRepo = new UserDAOImplS133967();
                List<UserDTOS133967> users = new ArrayList<>();
                try {
                    users = userRepo.getUserList();
                } catch (IUserDAOS133967.DALException e) {
                    e.printStackTrace();
                }
                for(UserDTOS133967 u: users){
                    System.out.format(u.toString() + "\n");
                }
                //querryTestSchema();
            }else if(choice.trim().equalsIgnoreCase("i")){
                UserDAOImplS133967 userRepo = new UserDAOImplS133967();
                UserDTOS133967 user = new UserDTOS133967();
                System.out.format("Enter User details below\n");
                System.out.format("Enter User Id here: ");
                String idStr = in.nextLine().trim();
                int id = -1;
                try {
                    id = Integer.parseInt(idStr);
                }catch (Exception e){
                    e.printStackTrace();
                }
                while (id <= 0){
                    System.out.format("%d was invalid id, try again!\n", id);
                    System.out.format("Enter User Id here: ");
                    idStr = in.nextLine().trim();
                    try {
                        id = Integer.parseInt(idStr);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                user.setUserId(id);

                System.out.format("Enter User first name here: ");
                String firstName = in.nextLine().trim();
                user.setUserName(firstName);

                System.out.format("Enter User initials here: ");
                String ini = in.nextLine().trim();
                while(userRepo.isInUse(ini)){
                    System.out.format("ini: %s is in use, choose another one: ", ini);
                    ini = in.nextLine().trim();
                }
                user.setIni(ini);

                System.out.format("Choose roles, for example: 1,3: \n");
                List<String> roles = userRepo.queryAllRoles();
                String s = "";
                for(int i=0; i <roles.size(); i++) {
                    s += i + " " + roles.get(i) + ", ";
                }
                s = s.substring(0,s.length()-", ".length());
                System.out.format("%s:\n",s);
                String rolesStr = in.nextLine().trim();
                List<String> indexesStr = new ArrayList<>( Arrays.asList(rolesStr.split(",")));
                Set<String> newRoles = new TreeSet<>();
                for (String index : indexesStr) {
                    try {
                        newRoles.add(roles.get(Integer.parseInt(index)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                user.setRoles(new ArrayList<>(newRoles));
                try {
                    userRepo.createUser(user);
                } catch (IUserDAOS133967.DALException e) {
                    e.printStackTrace();
                }
            }else if(choice.trim().equalsIgnoreCase("u")) {
                UserDAOImplS133967 userRepo = new UserDAOImplS133967();
                UserDTOS133967 user = new UserDTOS133967();

                String idStr;
                int id = -1;
                boolean isUserInDB = false;
                do{
                    if( id<=0 || !isUserInDB) {
                        System.out.format("Enter User Id here: ");
                        idStr = in.nextLine().trim();
                        try {
                            id = Integer.parseInt(idStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (id <= 0) {
                            System.out.format("%d was invalid id, try again!\n", id);
                        }
                    }
                    if(id > 0){
                        try {
                            user = userRepo.getUser(id);
                        } catch (IUserDAOS133967.DALException e) {
                            e.printStackTrace();
                        }
                        if(user.getUserId() <= 0){
                            System.out.format("The id %d was not found in the database, try again!\n", id);
                        }else {
                            isUserInDB = true;
                        }
                    }
                }while (id <= 0 || !isUserInDB);

                String updateChoice = "";
                while (!updateChoice.equalsIgnoreCase("q")){
                    System.out.format("Update User first name, n/N\n");
                    System.out.format("Update User roles, r/R\n");
                    System.out.format("Quit and update the User, u/U\n");
                    System.out.format("Quit without updating the User, q/Q\n");
                    System.out.format("\nUser status: %s\n",user.toString());
                    updateChoice = in.nextLine().trim();
                    if(updateChoice.equalsIgnoreCase("n")){
                        System.out.format("Enter new User first name here: ");
                        String firstName = in.nextLine().trim();
                        user.setUserName(firstName);
                    }else if (updateChoice.equalsIgnoreCase("r")){
                        System.out.format("Choose roles, for example: 1,5,2: \n");
                        List<String> roles = userRepo.queryAllRoles();
                        String s = "";
                        for(int i=0; i <roles.size(); i++) {
                            s += i + " " + roles.get(i) + ", ";
                        }
                        s = s.substring(0,s.length()-", ".length());
                        System.out.format("%s:\n",s);
                        String rolesStr = in.nextLine().trim();
                        List<String> indexesStr = new ArrayList<>( Arrays.asList(rolesStr.split(",")));
                        Set<String> newRoles = new TreeSet<>();
                        for (String index : indexesStr) {
                            try {
                                newRoles.add(roles.get(Integer.parseInt(index)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        user.setRoles(new ArrayList<>(newRoles));
                    }else if(updateChoice.equalsIgnoreCase("u")){
                        try {
                            userRepo.updateUser(user);
                            updateChoice = "q";
                        } catch (IUserDAOS133967.DALException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.format("Updated user: %s !\n", user.toString());
                /*
                int maxId = queryMaxId();
                if(maxId != -1)
                    insertIndhold(in.nextLine().trim(), maxId);
                else
                    System.out.format("Error maxId is -1\n");
                */
            }else if(choice.trim().equalsIgnoreCase("d")){
                System.out.format("Enter id for User to be deleted from the database: ");
                int idToBeDeleted = -1;
                String input = "";
                try{
                    input = in.nextLine().trim();
                    idToBeDeleted = Integer.parseInt(input.trim());
                }catch (Exception e){
                    System.out.format("%s was not an integer, try again!\n", input);
                }
                if(idToBeDeleted < 1)
                    System.out.format("%d was invalid id, try again!\n", idToBeDeleted);
                else{
                    UserDAOImplS133967 userRepo = new UserDAOImplS133967();
                    try {
                        userRepo.deleteUser(idToBeDeleted);
                    } catch (IUserDAOS133967.DALException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
                System.out.format("%s was not an option, try again!\n", choice.trim());
            showUserMenue();
            choice = in.nextLine();
        }
        System.out.format("You've pressed q/Q. Goodbye!\n");
        in.close();
        //createTableStudents();
        //addStudents();
        //querryStudents();
    }
        /*
        public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        showMenue();
        String choice = in.nextLine();
        while( !choice.trim().equalsIgnoreCase("q") ){
            if(choice.trim().equalsIgnoreCase("1"))
                querryTestSchema();
            else if(choice.trim().equalsIgnoreCase("i")){
                System.out.format("Enter indhold to be inserted here: ");
                int maxId = queryMaxId();
                if(maxId != -1)
                    insertIndhold(in.nextLine().trim(), maxId);
                else
                    System.out.format("Error maxId is -1\n");
            }else if(choice.trim().equalsIgnoreCase("d")){
                System.out.format("Enter id for indhold to be deleted from the database: ");
                int idToBeDeleted = -1;
                String input = "";
                try{
                    input = in.nextLine().trim();
                    idToBeDeleted = Integer.parseInt(input.trim());
                }catch (Exception e){
                    System.out.format("%s was not an integer, try again!\n", input);
                }
                if(idToBeDeleted < 1)
                    System.out.format("%d was invalid id, try again!\n", idToBeDeleted);
                else
                    deleteRecord(idToBeDeleted);
            }
            else
                System.out.format("%s was not an option, try again!\n", choice.trim());
            showMenue();
            choice = in.nextLine();
        }
        System.out.format("You've pressed q/Q. Goodbye!\n");
        in.close();


        //createTableStudents();
        //addStudents();
        //querryStudents();
    }*/

    private void deleteRecord(int idToBeDeleted) {
        int maxId = -1;
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "DELETE FROM test " +
                         "WHERE id = " + idToBeDeleted;
            stmt.executeUpdate(sql);

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
        System.out.println("Goodbye!");
    }

    private static void insertIndhold(String indholdText, int maxId) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            int nextId= queryMaxId() + 1;

            String sql = "INSERT INTO test (id, indhold)" +
                    "VALUES (" + nextId + "," + "'" + indholdText + "'" + ")";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

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
        System.out.println("Goodbye!");

    }

    private static int queryMaxId() {
        int maxId = -1;
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT MAX(id) AS lastId FROM test";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                maxId  = rs.getInt("lastId");
                System.out.format("maxId: %d\n",maxId);
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
        System.out.println("Goodbye!");

        return maxId;
    }



    private static void showMenue() {
        System.out.format("To show the indhold of the database enter 1: \n");
        System.out.format("Insert indhold to the database enter i/I: \n");
        System.out.format("Delete indhold from the database enter d/D: \n");
        System.out.format("To exit press q/Q: \n");
    }

    /*
    1. Skriv programmet om, så det forbinder til din database.
    - Kontroller at det kan hente dine data fra sidste uge (fra test tabellen).
    2.Udvid programmet så man kan indsætte en ny post i databasen
    - Brug Scanner til at indlæse fra konsollen
    - Brug INSERT INTO til at indsætte de data som brugeren har indtastet
    3. Udvid programmet så det
    - Viser hvilke data der er i 'test' tabellen
    - Viser en menu der giver mulighed for at indsætte eller slette data i databasen.
    * */
    private static void querryTestSchema() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, indhold FROM test";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String indhold = rs.getString("indhold");

                //Display values
                System.out.format("Id: %d, Indhold: %s\n",id, indhold);

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
        System.out.println("Goodbye!");
    }


    private static void querryStudents() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, first, last, age FROM Student";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.format("Id: %d, Age: %d, First: %s, Last: %s.\n",id, age, first, last);
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
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
        System.out.println("Goodbye!");
    }

    private static void addStudents() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Student " +
                    "VALUES (100, 'Lily', 'Larsen', 1)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Student " +
                    "VALUES (101, 'Pernille', 'Larsen', 34)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Student " +
                    "VALUES (102, 'Kaloyan', 'Penov', 36)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Student " +
                    "VALUES(103, 'Bill', 'Gates', 70)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

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
        System.out.println("Goodbye!");
    }

    private static void createTableStudents() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE Student " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
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
        System.out.println("Goodbye!");
    }
}
