package edu.escuelaing.arsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.escuelaing.arsw"})
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
