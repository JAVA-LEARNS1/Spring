import com.ld.dao.UserDaoImpl;
import com.ld.dao.UserDaoOracleImpl;
import com.ld.service.UserService;
import com.ld.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        //用户实际调用的是业务层，dao层不需要接触
        //UserService userService = new UserServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoOracleImpl());
        userService.getUser();
    }
}
