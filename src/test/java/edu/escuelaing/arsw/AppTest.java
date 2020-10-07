package edu.escuelaing.arsw;


import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.services.ProductService;
import edu.escuelaing.arsw.services.ServicioService;
import edu.escuelaing.arsw.services.StoreService;
import edu.escuelaing.arsw.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;

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
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deberiaRegistrar() {
        try {
            assertEquals(0 ,userService.findAll().size());
            userService.register("santiago", "lopez", "santiago@mail.com", "1234", "MECA", "Carrera 123", "img", 10000000, 32454234);
            assertEquals(1 ,userService.findAll().size());
            userService.register("juan", "munoz", "juan@mail.com", "1234", "MECA", "Carrera 130", "img", 10000000, 32454432);
            assertEquals(2 ,userService.findAll().size());
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deberiaHacerLogin() {
        try {
            assertEquals(true, userService.login("santiago@mail.com", "1234"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void noDeberiaHacerLoginIncorrectEmail() {
        try {
            userService.login("santi@mail.com", "154234");
        } catch (Exception e) {
            assertEquals("Email doesn't found", e.getMessage());
        }
    }

    @Test
    public void noDeberiaHacerLoginIncorrectPassword() {
        try {
            userService.login("santiago@mail.com", "154asf4");
        } catch (Exception e) {
            assertEquals("Incorrect password", e.getMessage());
        }
    }

    @Test
    public void deberiaCrearProducto() {
        try {
            assertEquals(0, productService.findAll().size());
            productService.register("Aceitee", "Aceite Mobil", 80000, "image aceite", "available", 32);
            assertEquals(1, productService.findAll().size());
        } catch (ProductServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deberiaCrearServicio() {
        try {
//            assertEquals(0, servicioService.findAll().size());
            servicioService.register("Cambio de Aceitee", "Imagen Aceite Mobil", "Cambio de aceite para carro", 100000, "available", 35);
//            assertEquals(1, servicioService.findAll().size());
        } catch (ServicioServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deberiaCrearStore() {
        try {
            assertEquals(1, storeService.findAll().size());
            storeService.registerStore("Donde Alan Brito", 31);
            assertEquals(2, storeService.findAll().size());

        } catch (StoreServiceException | UserServiceException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deberiaObtenerTodosProductos() {
        try {
            assertEquals(2, productService.findAll());
            /*mockMvc.perform(
                    MockMvcRequestBuilders.get("/findproducts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(""))
                    .andExpect(status().isAccepted());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void deberiaListarProductos() {
//        try {
//            productService.findAll();
//        } catch (ProductServiceException e) {
//            e.printStackTrace();
//        }
//    }
}
