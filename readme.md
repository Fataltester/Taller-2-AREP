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
git clone https://github.com/Fataltester/Taller-1-AREP.git
```
2. Como estamos utilizando Netbeans simplemente abrimos el proyecto en el ambiente y ejecutamos el archivo "WebApplication", una vez iniciado, en la consola aparecerá "Listo para recibir ..."
<img width="1247" height="1035" alt="image" src="https://github.com/user-attachments/assets/a9f80cbf-e0f4-494d-abd3-30c1df290482" />
3. Procedemos a ir al navegador e insertar la siguiente dirección
```bash
http://localhost:35000/src/main/public/index.html
```
Lo que nos llevará a nuestra aplicación web
<img width="1214" height="946" alt="image" src="https://github.com/user-attachments/assets/0f90dda2-2bcf-4807-a8cb-4ab7ef649807" />
## Arquitectura
Estamos ante una arquitectura cliente-servidor, a nivel del carpetado del proyecto tenemos la siguiente estructura
<img width="492" height="370" alt="image" src="https://github.com/user-attachments/assets/16e613e0-56d0-46b2-8048-2917ea00e06d" />

