# Desarrollo de Framework web para servicios Rest y archivos estáticos
El objetivo del taller es la implementación de un framework web, donde implementamos servicios Rest expresados en funciones lambda mejorando las peticiones para servicios y archivos dinámicos.
## Objetivos 
* Método GET: Implementar el método para definir servicios Rest usando funciones lambda
* Extraer parámetros de la petición: Implementar una función que extraiga los parámetros y utilizarlos en servicios REST
* Especificación de archivos estáticos: Implementar un método que permita extraer archivos de una ruta definida
## PreRequisitos
Para el proyecto debemos configurar un entorno maven para armar el proyecto, para esto hay diferentes opciones como visual studio, intellij, etc. Si se quiere trabajar sobre estos se debe configurar e instalar maven para su funcionamiento, pero, para este laboratorio y facilitarme este proceso utilicé Netbeans el cual me ahorra ese proceso, puede instalar el ambiente de desarrollo en su página oficial [aquí](https://netbeans.apache.org/front/main/index.html). También debemos tener java instalado y git para poder clonar el repositorio actual.
## Instalación
1. Vamos a clonar el repositorio de git con el siguiente comando
```bash
git clone https://github.com/Fataltester/Taller-2-AREP.git
```
2. Como estamos utilizando Netbeans simplemente abrimos el proyecto en el ambiente y ejecutamos el archivo "WebApplication", una vez iniciado, en la consola aparecerá "Listo para recibir ..."

3. Procedemos a ir al navegador e insertar la siguiente dirección
```bash
http://localhost:35000/....
```
Ya dependiendo la solicitud si es de archivo estático o servicio Rest ya definido se completa la URI

Para consultar tenemos:
Servicios REST:
```bash
http://localhost:35000/pi
http://localhost:35000/hello?name=juan
```
Para archivos estáticos:
```bash
http://localhost:35000/cat.jpg
http://localhost:35000/index.html
http://localhost:35000/response.js
http://localhost:35000/style.css
```

## Arquitectura
Tenemos una arquitectura por capas, donde cada capa tiene las siguientes funciones

Capa de red -> donde se inicializa el server socket.
Capa de request/parsing handling -> donde manejamos HTTPRequest y HTTPResponse para control de peticiones.
Capa de aplicación -> donde estan contenidos los REST (service)
Capa de recursos estáticos -> donde servimos los archivos estáticos

## Reporte de pruebas
Tener en cuenta que el servidor debe estar ejecutandosé

<img width="804" height="225" alt="image" src="https://github.com/user-attachments/assets/9e784f0d-7b89-4f42-8a49-e824cbd4e6c2" />

Tenemos las siguientes pruebas
* testHelloServiceWithQueryParam -> que el servicio hello pueda leer un parámetro y usarlo en la respuesta.
* testPiService -> que el servicio pi responda con el valor esperado que es el valor de pi.
* testQueryParamExtractionMultipleValues -> que se puedan manejar varios parámetros en el query.
* testStaticFileHtml -> que el servidor pueda obtener un archivo .html.
* testStaticFileNotFound -> al pedir un archivo que no exista, notifique el caso.
## De aceptación
index.html

<img width="1241" height="441" alt="image" src="https://github.com/user-attachments/assets/d7841ea6-2b16-4fa5-b7c9-874f0ae3da24" />

cat.jpg

<img width="1237" height="703" alt="image" src="https://github.com/user-attachments/assets/6c619476-9400-45b5-bca4-9911de24c344" />

servicio rest hello:

<img width="315" height="65" alt="image" src="https://github.com/user-attachments/assets/3d3033e4-1fb9-4a56-bfa5-7b930f40980f" />

servicio rest pi:

<img width="332" height="69" alt="image" src="https://github.com/user-attachments/assets/7c98054e-a386-41ae-85c1-807025486619" />

### Contruido con 
[Maven](https://maven.apache.org) Maven

[Netbeans](https://netbeans.apache.org/front/main/index.html) Netbeans 

[Git](https://git-scm.com) Git

### Autor
Juan David Martínez Mendez - [Fataltester](https://github.com/Fataltester)


