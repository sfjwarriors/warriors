package edu.escuelaing.arsw;


import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.persistence.UserPersistence;
import edu.escuelaing.arsw.services.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    public UserService userService;
    @Test
    public void test() {
        /*try {
            //userService.register("santiago", "lopez", "santiago@mail.com", "1234", "USER", "Carrera 123", "img", 10000000);
            //userService.login("santi@mail.com", "1234");
        } catch (UserServiceException e) {
            e.printStackTrace();
        }*/
        ;
    }
}
