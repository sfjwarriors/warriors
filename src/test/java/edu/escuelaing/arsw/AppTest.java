package edu.escuelaing.arsw;


import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.persistence.UserPersistence;
import edu.escuelaing.arsw.services.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
@AutoConfigureMockMvc
public class AppTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deberiaRegistrar() {
        try {
            userService.register("santiago", "lopez", "santiago@mail.com", "1234", "USER", "Carrera 123", "img", 10000000);
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deberiaHacerLogin() {
        try {
            userService.login("santi@mail.com", "1234");
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void deberiaObtenerTodosProductos() {
//        try {
//            mockMvc.perform(
//                    MockMvcRequestBuilders.get("/findproducts")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(""))
//                    .andExpect(status().isAccepted());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
