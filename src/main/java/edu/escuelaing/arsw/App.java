package edu.escuelaing.arsw;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.context.annotation.ComponentScan;


/**
 * Hello world!
 *
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"edu.escuelaing.arsw"})
//@EnableAutoConfiguration(exclude = {JndiConnectionFactoryAutoConfiguration.class, DataSourceAutoConfiguration.class,
//HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
//@EnableTransactionManagement
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);

    }
}
