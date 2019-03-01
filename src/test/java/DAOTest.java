import dal.DAO;
import dal.IUserDAO;
import dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DAOTest {

    @Test
    public void createUserTest() {
        UserDTO userTest = new UserDTO();
        DAO dao = new DAO();
        userTest.setUserName("TestUserName");
        Assert.assertEquals("TestUserName", userTest.getUserName());
        userTest.setCpr("0123456789");
        Assert.assertEquals("0123456789",userTest.getCpr());
        userTest.setIni("TUN");
        Assert.assertEquals("TUN",userTest.getIni());
        userTest.setUserId(99);
        Assert.assertEquals(99,userTest.getUserId());
        userTest.setPassword("TestPassword!_0");
        Assert.assertEquals("TestPassword!_0",userTest.getPassword());
        userTest.setRole("Admin");
        Assert.assertEquals("Admin",userTest.getRole());
        try{
            dao.createUser(userTest);
        }
        catch (IUserDAO.DALException e) {
        }
        try {
            UserDTO userTestEquals = dao.getUser(99);
            Assert.assertEquals(userTest.getUserName(),userTestEquals.getUserName());
            Assert.assertEquals(userTest.getIni(),userTestEquals.getIni());
            Assert.assertEquals(userTest.getPassword(),userTestEquals.getPassword());
            Assert.assertEquals(userTest.getRole(),userTestEquals.getRole());
            Assert.assertEquals(userTest.getCpr(),userTestEquals.getCpr());
            Assert.assertEquals(userTest.getUserId(),userTestEquals.getUserId());
        }
        catch (IUserDAO.DALException e) {

        }
        try {
            dao.deleteUser(99);
        }
        catch (IUserDAO.DALException e) {

        }
    }
}
