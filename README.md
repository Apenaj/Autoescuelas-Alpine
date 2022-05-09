# Autoescuelas-Alpine

![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/Login.png)

## Video
https://www.youtube.com/watch?v=t3GtbkhReMg	

## Descripcion
Pagina web para gestionar una autoescuela.

## Funcionalidades
### Publicas:
- Los clientes podran escoger entre los distintos tipos de carnet
- La autoescuela cuenta con bonos de clases.
- Eleccion de turno.
- Cursos de recuperacion de puntos de carnet o clases de recuerdo.
                                      

### Privadas:
- Envio de examenes tipo test a los alumnos  por parte de los profesores
- Los alumnos recibiran un resumen del resultado de la última semana.
- Gestion de clases practicas con vehiculo autopropulsado.
- Gestión de alumnos
- Gestión de profesores
- Gestión de clases
- Gestión de Examenes
- Gestión de Carnets
- Gestión de Vehiculos

### Servicio interno:
- Notificaciones a alumnos
- Notificacion de clases

## Entidades
- Alumnos
- Profesores
- Clase
- Carnet
- Vehiculo

## Diagrama Entidad Relacion
![E/R](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/ER.png)

## Diagrama de navegacion
![DiagramaNavegacion](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/diag_nav_autoescuela.png)

## Diagrama de clases
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/DiagramaClases.png)

## Infraestructura Docker
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/docker.png)

# Documentacion Servicio Interno
El servicio interno consiste en el envio de un correo al gmail cuando se dan de alta un profesor o un alumno nuevo

Si el profesor entra en la pestaña de alta alumno, deberá introducir los datos: Dni, Nombre, Contraseña y Correo. Al dar de alta el usuario se enviara un mensaje a la direccion de correo indicada. Esto sucede cada vez que damos de alta a alguien

## Correo GMAIL (API REST)
En los paquetes de la web principal, tenemos la clase Message con sus atributos to,asunto y body. 
Ademas tenemos el NotificacionService que es el encargado de comunicarse con el servicio interno para enviar el mensaje.

~~~
...
@Service
public class NotificacionService{
	
	
	
	private static final String MAIL_SERVICE_URL = "MAIL_SERVICE_URL";
	
	@Resource
    private Environment environment;
	
	public boolean enviarNotificacion (String direccionCorreo,String mensaje) {
		try {
			Message msg = new Message(direccionCorreo,"Bienvenido a Autoescuela Alpine", mensaje);

			HttpEntity<Message> httpEntity = new HttpEntity<>(msg);
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Void> res =restTemplate.postForEntity( environment.getProperty(MAIL_SERVICE_URL) + "/notificacion", httpEntity, Void.class);

			if(res.getStatusCode() == HttpStatus.CREATED) {
				System.out.println("Enviado correctamente");
				return true;
			}else{
				System.out.println("Error enviando:"+res.getStatusCode());
				return false;
			}
		}catch (Exception e) {
			System.out.println("Error enviando:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
}
~~~

En la aplicacion del servicio interno tenemos tambien la misma clase message que tenemos en el servicio web. Ademas en esta aplicacion estara el controlador de la aplicacion y el enviocorreo que se encarga de realizar las configuracion para enviarlo

~~~
...
@Component
public class EnvioCorreo {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean send(Message mensaje) {
		
		MimeMessage email = sender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(email,true);
			
			//Campos necesarios para mandar el correo 
			helper.setFrom(Message.CORREO_ORIGEN); // dirección origen
			helper.setTo(mensaje.getTo()); // dirección destino
			helper.setSubject(mensaje.getAsunto()); // asunto del correo
			helper.setText(mensaje.getBody(), true); // cuerpo del mensaje
			
			sender.send(email);
			
			return true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}

~~~

Por ultimo para las configuraciones properties se han añadido las siguientes lineas.

WEB

~~~
MAIL_SERVICE_URL = http://localhost:8444
NOMBRE_INSTANCIA = instancia1
~~~

SERVICIO INTERNO

~~~
server.port=8444
spring.mail.host=smtp.gmail.com
spring.mail.properties.mail.smtp.port=587
spring.mail.username=alpinedad2022@gmail.com
spring.mail.password=webDaD22
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.smtp.starttls.required=true
~~~

## Instrucciones de instalacion en VM
Requerimientos preinstalacion:
Se presume que el sistema operativo ya esta instalado con virtualbox.

Mysql server
JAVA

Los 2 archivos jar de la aplicación,servidor y servicio interno compilados desde spring boot  (run as mavel desde el boton derecho del raton encima de cada proyecto)

Como instalar los componentes si no se tiene instalado mysql server y java.

### Mysql server

- Abrimos el terminal de ubuntu(boton derecho raton→terminal.)

- Actualizamos los paquetes: 

~~~
sudo apt update
~~~

- Instalamos mysql server:

~~~
sudo apt install mysql-server
~~~

- Configuramos mysql server:

~~~
sudo mysql_secure_installation
~~~
- 1º pregunta [intro]
- 2º pregunta contraseña=”root”(sin las comillas).
- 3º pregunta y siguientes [intro].

- Cambiamos el metodo de auntenticacion  de root:

~~~
sudo mysql
SELECT user,authentication_string,plugin,host FROM mysql.user;
ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'root';
FLUSH PRIVILEGES;
SELECT user,authentication_string,plugin,host FROM mysql.user;
exit
~~~

### JAVA

- Actualizamos los paquetes:

~~~
sudo apt update
~~~

- Instalamos java:

~~~
sudo apt install default-jre
~~~

- Verificamos la version de la instalación:

~~~
java -version
~~~

- Completando la instalación.

Estos pasos solo debe completarse la primera vez una vez se tiene instalado mysql y java:

~~~
mysql -h localhost -u root -p (introducimos root de contraseña).
CREATE DATABASE autoescuelaalpine;
exit
~~~

### Ejecucion final

Una vez completado lo anterior se recomienda abrirse un editor de texto y crearse un archivo con autoescuelaalpine.sh con el siguiente contenido:


~~~

 #!/bin/bash
 #_*_ENCODING: UTF-8 _*_

 java -jar Inicio-Alpine-0.0.1-SNAPSHOT.jar & java -jar serviciointerno-0.0.1-SNAPSHOT.jar

~~~

De esta manera para ejecutar la app bastaria con hacer ./autoescuelaalpine.sh si diese error de permiso denegado se le puede dar permisos usando el commando “chmod ugo+rwx autoescuelaalpine.sh” al archivo, el .sh debe estar en el mismo directorio que los .jar y ejecutarse desde ese directorio.

Para acceder a la web abrimos el navegador con la direccion https://localhost:8443/

## Capturas de pantalla

![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/Login.png)
Login
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/Principal.jpeg)
Principal
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/AltaAlumno.png)
Da de alta a un alumno, se enviara un correo de notificacion al usuario cuando se registre
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/BusquedaAlumno.png)
Busca un alumno por su D.N.I y muestra los datos
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/AltaCarnet.png)
Añade un carnet especificando el tipo de carnet
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/BusquedaCarnet.png)
Busca si existe un tipo de carnet
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/AltaClase.png)
Añade una nueva clase teorica
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/BusquedaClase.png)
Busca una clase ya existente
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/AltaProfesores.png)
Da de alta un profesor
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/BuscaProfesores.png)
Busca un profesor por su D.N.I y muestra los datos
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/AltaVehiculo.png)
Añade un nuevo vehiculo 
![](https://github.com/Apenaj/Autoescuelas-Alpine/blob/main/img/BuscaVehiculo.png)
Busca un vehiculo por su matricula y muestra los datos


