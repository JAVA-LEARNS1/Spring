import com.ld.pojo.Student;
import com.ld.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student)context.getBean("student");

        System.out.println(student.getName());
        System.out.println(student.getAddress().getAddress());
        System.out.println(student.getBooks()[0]);
        System.out.println(student.getCard());
        System.out.println(student.getGames());
        System.out.println(student.getHobbys());
        System.out.println(student.getInfo());
        System.out.println(student.getWife());
    }
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user);
    }
}
