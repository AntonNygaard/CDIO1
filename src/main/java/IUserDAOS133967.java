import java.util.List;

public interface IUserDAOS133967 {
   UserDTOS133967 getUser(int userId) throws DALException;
    List<UserDTOS133967> getUserList() throws DALException;
    void createUser(UserDTOS133967 user) throws DALException;
    void updateUser(UserDTOS133967 user) throws DALException;
    void deleteUser(int userId) throws DALException;

    public class DALException extends Exception {
        private static final long serialVersionUID = 7355418246336739229L;
        public DALException(String msg, Throwable e) {
            super(msg,e);
        }
        public DALException(String msg) {
            super(msg);
        }
    }

}
