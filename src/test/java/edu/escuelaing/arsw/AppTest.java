package edu.escuelaing.arsw;


import ch.qos.logback.core.net.SyslogOutputStream;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.*;
import edu.escuelaing.arsw.services.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    private CartService cartService;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void T01deberiaRegistrar() {
        try {
            assertEquals(0 ,userService.findAll().size());
            User user1 = new User("santiago", "lopez", "santiago@mail.com", "1234", "MECA", "Carrera 123", "img", 10000000, 32454234);
            userService.register(user1);
            assertEquals(1 ,userService.findAll().size());
            User user2 = new User("juan", "munoz", "juan@mail.com", "1234", "MECA", "Carrera 130", "img", 10000000, 32454432);
            userService.register(user2);
            assertEquals(2 ,userService.findAll().size());
//            System.out.println(userService.findAll());
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void T2deberiaHacerLogin() {
//        try {
//            assertEquals(true, userService.login("juan@mail.com", "1234"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void T3noDeberiaHacerLoginIncorrectEmail() {
//        try {
//            userService.login("santi@mail.com", "154234");
//        } catch (Exception e) {
//            assertEquals("Email doesn't found", e.getMessage());
//        }
//    }

//    @Test
//    public void T4noDeberiaHacerLoginIncorrectPassword() {
//        try {
//            userService.login("santiago@mail.com", "154asf4");
//        } catch (Exception e) {
//            assertEquals("Incorrect password", e.getMessage());
//        }
//    }

    @Test
    public void T05deberiaCrearStore() {
        try {
//            System.out.println(storeService.findAll());
            assertEquals(2, storeService.findAll().size());
            User user = new User("Daniel", "Hernandez", "daniel@mail.com", "1234", "MECA", "Carrera 250", "img", 14500000, 32458832);
            userService.register(user);
            assertEquals(3, storeService.findAll().size());
        } catch (StoreServiceException | UserServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T06deberiaCrearProducto() {
        try {
            assertEquals(0, productService.findAll().size());
            Product product = new Product("Aceitee", "Aceite Mobil", 80000, "image aceite", "available", 4);
            productService.register(product);
            assertEquals(1, productService.findAll().size());
        } catch (ProductServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T07deberiaCrearServicio() {
        try {
            assertEquals(0, servicioService.findAll().size());
            Servicio servicio = new Servicio("Cambio de Aceitee", "Imagen Aceite Mobil", "Cambio de aceite para carro", 100000, "available", 4);
            servicioService.register(servicio);
            assertEquals(1, servicioService.findAll().size());
        } catch (ServicioServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T08deberiaObtenerTodosProductos() {
        try {
            assertEquals(1, productService.findAll().size());
//            System.out.println(productService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T09deberiaObtenerTodosServicios() {
        try {
            Servicio servicio = servicioService.findAll().get(0);
            assertEquals(1, servicioService.findAll().size());
//            System.out.println(servicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T10deberiaCrearOrden() {
        Date dateI = new java.sql.Date(28/12/2020);
        Date dateF = new java.sql.Date(30/12/2020);
        try {
            assertEquals(0, ordenService.findAll().size());
            Orden orden = new Orden(dateI, dateF, 95000, "aceptado", 4);
            ordenService.createOrden(orden);
            assertEquals(1, ordenService.findAll().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T11deberiaObtenerTodasOrdenes() {
        try {
            assertEquals(1, ordenService.findAll().size());
//            System.out.println(ordenService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T12deberiaAgregarCart() {
        try {
            assertEquals(0, cartService.findAll().size());
            Cart cart = new Cart(Long.valueOf(7),Long.valueOf(9),Long.valueOf(8));
            cartService.addToCart(cart);
            assertEquals(1, cartService.findAll().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T13deberiaObtenerTodosCarts() {
        try {
            assertEquals(1, cartService.findAll().size());
//            System.out.println(cartService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T14deberiaObtenerCartById() {
        try {
            assertEquals(true, cartService.existCartById(10));
//            System.out.println(cartService.existCartById(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T15deberiaEliminarCart() {
        try {
            assertEquals(1, cartService.findAll().size());
            Cart cart = cartService.findAll().get(0);
            cartService.deleteFromCart(cart);
            assertEquals(0, cartService.findAll().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
