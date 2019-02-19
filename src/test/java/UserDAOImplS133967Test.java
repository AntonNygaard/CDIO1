import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOImplS133967Test {
    UserDAOImplS133967 userDAO = null;

    @Before
    public void beforeEach(){
        userDAO = new UserDAOImplS133967();
    }

    @Test
    public void gevenSetRolesForTheRoleTable_returnRolesWereSetInTheDatabase(){
        List<String> roles = new ArrayList<String>(){{add("programmer");add("designer");
                                                      add("leader");add("none");add("instructor");
                                                      add("seller");add("HR");}};
        userDAO.addRolesToRoleTable(roles);
        List<String> rolesRetrived = userDAO.queryAllRoles();
        for(String role : roles){
            assertEquals(true, rolesRetrived.contains(role));
        }
    }
    @Test
    public void deleteUser() throws IUserDAOS133967.DALException {
        userDAO.deleteUser(3);
    }

    @Test
    public void givenCreateNewUser_returnNewUserWasCreated() throws IUserDAOS133967.DALException {
        UserDTOS133967 user = new UserDTOS133967();
        user.setUserName("Bob");
        user.setIni("bap");
        user.setUserId(4);
        user.addRole("worker");
        user.addRole("boss");

        userDAO.createUser(user);

        UserDTOS133967 returnedUser = userDAO.getUser(user.getUserId());

        assertEquals(user.toString(), returnedUser.toString());

        userDAO.deleteUser(user.getUserId());
    }

}
