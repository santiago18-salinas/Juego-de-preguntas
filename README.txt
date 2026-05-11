README - Sistema de Juego de Trivia ING_III

Guía completa para entender, usar y modificar el juego
1. ¿Qué es este juego?
Es un juego de preguntas y respuestas tipo trivia. Cada jugador elige una cantidad de preguntas a responder y compite por responder correctamente para ganar puntos. Al final, el jugador con más puntos lidera el ranking.



2. Cómo abrir el juego
Requisitos previos
-Tener Java instalado (JDK 8 o superior)
-Sistema operativo: Windows, Mac o Linux
-Archivos del juego descargados y descomprimidos


Métodos para abrir
Método fácil: Doble clic en el archivo ING_III.jar (si tienes Java bien configurado)
Método terminal: Abre una terminal/consola en la carpeta del juego y escribe: java -jar ING_III.jar
Método IDE: Si tienes Eclipse, IntelliJ o NetBeans, importa la carpeta como proyecto y ejecuta Main.java


3. Estructura de carpetas (cómo está organizado)
El proyecto está dividido en carpetas como si fueran "departamentos" de una empresa. Cada uno tiene su trabajo específico:
Carpeta

                       Qué guarda dentro                                                                            Qué pasa si la borras

-administrador/Login y panel de administración para agregar/editar/eliminar preguntas               * Nadie puede entrar a administrar las preguntas

-juego/ La lógica del juego: controla turnos, revisa respuestas, cuenta puntos                      * El juego no funciona, no sabe quién ganó ni cómo jugar

-vista/Todas las ventanas visuales: botones, colores, tablero, pantallas                            * No ves NADA, solo una pantalla en blanco

-modelo/Las plantillas de datos: cómo se ve una Pregunta, un Jugador, etc.                          * Error en todo el sistema, no compila

-historico/Registro de partidas pasadas: quién jugó, quién ganó                                     * Puedes jugar pero no se guarda el historial

-main/ El punto de inicio: arranca todo el sistema                                                  * El juego simplemente no arranca



4. Archivos clave

Dentro de esas carpetas hay archivos especiales que hacen todo el trabajo pesado:

Main.java
Qué es: El interruptor general del juego
Qué hace: Es lo primero que se ejecuta cuando abres el juego. 
Analogía: Es como la llave de la casa: sin ella, no entras.


Pregunta.java
Qué es: La plantilla de una pregunta
Qué guarda: El enunciado de la pregunta, las 4 opciones y cuál es la correcta.


Juego.java
Qué es: El cerebro que controla la partida
Qué hace: Dice de quién es el turno, revisa si la respuesta es correcta, suma puntos, y dice quién ganó.



Historial.txt
Qué es: Un archivo de texto simple
Qué guarda: Todas las partidas jugadas: fecha, nombres de jugadores, quién ganó, puntajes.
Analogía: Es como un diario o bitácora donde se apuntan los resultados.


5. Cómo jugar (paso a paso)

Abre el juego (si hiciste lo de la sección 2, arriba)
Aparece la pantalla de login. Ingresa tu usuario y contraseña si deseas entrar como admin.

Si eres administrador: ves un panel para agregar nuevas preguntas, editar las existentes o borrar las que no sirvan.
Si eres jugador: empieza la partida.

Cada jugador elige la cantidad de preguntas que quiera responder (10, 15, 20).
El juego muestra una pregunta con 4 opciones (A, B, C, D).

Selecciona tu respuesta.
Si aciertas: sumas puntos.
Si fallas: no sumas.

Cuando se acaban las rondas, aparece el puntaje final, y si superaste el record o no.
El resultado se guarda automáticamente en el Historial.


6. Solución de problemas ocurridos.

-Error: No se abre el juego (doble clic no funciona)
Asegúrate de tener Java instalado y JDK.
Asegúrate de tener todos los archivos del proyecto.

-Error: No aparecen preguntas en el juego
Revisa que exista el archivo donde se guardan las preguntas esté dentro de la carpeta y que tenga preguntas adentro.
Nota: Si borraste el archivo de preguntas, necesitas agregar preguntas nuevas desde el panel de administrador.

─────────────────────────────
¡Ahora ve y disfruta el juego! :)


ING_III - Guía del Sistema

hecho por Luis Quintero y Santiago Salinas

