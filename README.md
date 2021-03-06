# MecaClick

## Integrantes
- Santiago Lopez Osorio
- Juan Muñoz Delgadillo
- Brayan Felipe Rojas 

## Despliegue, Analisis de codigo e integración continua:
[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](http://mecaclic.herokuapp.com/)
[![Deploy to Azure](https://aka.ms/deploytoazurebutton)](https://mecaclicks.azurewebsites.net/)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a239c365311e4969bab774b0af6f9a13)](https://www.codacy.com/gh/sfjwarriors/warriors?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sfjwarriors/warriors&amp;utm_campaign=Badge_Grade)

[![CircleCI](https://circleci.com/gh/sfjwarriors/warriors.svg?style=svg)](https://app.circleci.com/pipelines/github/sfjwarriors/warriors)

## Descripcion
El objetivo de la aplicación es conectar a proveedores de servicios automotrices y a sus clientes para que los clientes puedan consumir sus productos y servicios desde la comodidad de sus casas, la aplicación permitirá ver todos los proveedores y los servicios y productos que ofrecen. La aplicación permitirá que cuando se pida un servicio se chatee con el proveedor para ultimar detalles o para aclarar cualquier duda que tenga el cliente.

## Diagrama de clases:
![Diagrama de base de datos](https://i.ibb.co/6DXYPWw/model.png)
![modelo2](https://i.ibb.co/p1hFmBS/model2.png)

## Diagrama de Base de Datos:
![Diagrama de base de datos](img/ER.jpg)

## Diagrama de Componentes:
![Diagrama de componentes](img/components.jpg)

## Diagrama de Despliegue:
![Diagrama de despliegue](https://i.ibb.co/KqJmsq6/despliegue.png)

## Historias de Usuario:
![Diagrama casos de uso Mecanico](https://i.ibb.co/hX7B75d/casos-Proyecto.png)
* Como mecanico 
Quiero Agregar y modificar productos
Para Poder tener un catalogo mas amplio y actualizado 

* Como Mecanico
Quiero ver mis ordenes 
Para poder saber cuantos he hecho y cuales podre hacer o no 

* Como Mecanico 
Quiero Ver ganancias obtenidas 
Para Poder Saber si he mejorado mis ganancias con ayuda de la app



![Diagrama casos de uso Usuario Registrado](https://i.ibb.co/LdhyWQb/historia-Usuario.png)

* Como: Usuario registrado.
Quiero: Realizar una cotización de un servicio.
Para: Elegir el servicio de preferencia.
* Como Usuario registrado
Quiero  Pedir un servicio 
Para Poder Solucionar un problema de mi auto o moto 
* Como Usuario Registrado 
Quiero Calificar un servicio
Para Poder realizar una retroalimentacion.
* Como Usuario Registrado.
Quiero saber el historial de servicios
Para: Poder tener un listado de los servicios que he pedido

## Pruebas Unitarias

![Pruebas Unitarias](img/Pruebas.png)
## Requerimiento No Funcional 
### Prueba de Carga con JMeter
> JMeter es una herramienta que nos permite realizar pruebas de carga para analizar y medir el rendimiento de una variedad de servicios, con enfasis en aplicaciones web.
- A continuacion mostramos una tabla con los datos analizados arrojados por la aplicacion y una grafica que nos indica el comportamiento de la aplicacion ante las diferentes peticiones realizadas por los usuarios en un minuto.
![Tabla de analisis](https://media.discordapp.net/attachments/781412743537491969/781413761205534730/unknown.png)

![Grafica de la tabla de analisis](https://media.discordapp.net/attachments/781412743537491969/781412800408584232/unknown.png)

> Ahora decidimos realizar un load balancer ya que queremos asegurar la disponibilidad del sitio web y que pueda responder a las diferentes peticiones por parte de los usuarios a la maxima velocidad posible.

![Configuración Load Balancer](https://media.discordapp.net/attachments/781412743537491969/781412860014100540/unknown.png)

> Despues de realizar la configuracion del load balancer realizamos pruebas de carga con la herramienta JMeter para asegurarnos y comprobar que el escalamiento horizontal nos ayudo a mejorar la disponibilidad y respuesta de nuestra aplicacion web 

![Tabla de analisis](https://media.discordapp.net/attachments/781412743537491969/781412884134625320/unknown.png)
![Grafica de la tabla de analisis](https://media.discordapp.net/attachments/781412743537491969/781413815136813066/unknown.png)

> Logramos observar que con la implementacion de un load balancer nos ayudo a mejorar los tiempos de repsuesta con respecto a las peticiones realizadas y a mejorar la disponibilidad de los recursos para los usuarios. Aunque, sin embargo, seguimos teniendo un porcentaje de error significativo cuando se hacen treinta mil peticiones en un minuto.

## Manual de Usuario
Para ver el manual de Usuario para la aplicacion MecaClick, click [Acá](https://github.com/sfjwarriors/warriors/blob/master/ManualDeUsuario.md)
## Mockups
Para ver los mockups click [Aqui](https://github.com/sfjwarriors/warriors/blob/master/Mockups.pdf)
