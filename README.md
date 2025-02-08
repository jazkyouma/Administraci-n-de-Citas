# Administracion-de-Citas
~ Instalacion y configuracion ~  

REQUISITOS PREVIOS
- Java Development Kit (JDK) 8 o superior.
- Sistema operativo Windows, Linux o macOS.
- Consola de comandos o terminal.

PASOS DE INSTALACION 

Descarga del proyecto:
- Clona el repositorio de GitHub:git clone https://github.com/tu_usuario/nombre_repositorio.git 

Compilación:
- Navega al directorio del proyecto: cd nombre_repositorio
- Compila el código fuente: javac SistemaCitas.java

~ Uso del Programa ~

Inicio
- Ejecuta el programa con el siguiente comando: java -jar SistemaCitas.jar

Opciones del Menú
- Agregar Doctor: Introduce el identificador, nombre y especialidad del doctor.
- Agregar Paciente: Introduce el identificador y nombre del paciente.
- Crear Cita: Introduce el identificador, fecha y hora, motivo de la cita, y selecciona el doctor y paciente correspondientes.
- Guardar Datos: Guarda la información de doctores, pacientes y citas en archivos CSV.
- Mostrar Citas: Visualiza todas las citas registradas.
- Salir: Finaliza el programa.

Seguridad
- El programa cuenta con un sistema de autenticación para administradores. Introduce el ID de administrador y la contraseña correspondiente para acceder.

Validaciones
- Si faltan archivos de datos en la carpeta db, el programa los regenerará automáticamente.

~ Creditos ~
Jan Jahaziel Estrada Vazquez   

~ Licencia ~
IntelliJ IDEA 2024 3.2  
