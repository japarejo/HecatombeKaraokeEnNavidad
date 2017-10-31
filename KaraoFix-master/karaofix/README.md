# Problemas con el Karaoke Navidadeño (Inspirado en hechos reales)
Tras unas semanas de arduo trabajo y dificultades, las fiestas navideñas se acercan y el tsunami de tareas parece menguar. Como cada año, te aprestas a preparar el tradicional karaoke navideño para la familia pero... cual es tu sorpresa, que tras instalar la última versión de un conocidísimo programa para dichos menesteres, muchos de los temas estrella parecen no funcionar.
:-S :-S :-S

Tras una minuciosa investigación has descubierto que el problema viene por el fichero que especifica los datos de la canción y su letra. Concretamente ha una serie de campos que antes se soportaban como números en coma flotante, que ahora sólo se soportan como enteros por la última versión del programa de karaoke.

Tu misión, es crear un programa que cree una copia de las canciones del karaoke (almacenadas en el directorio resources/temazos) en el directorio (resources/grandesexitos), cambiando el formato del fichero que especifica los metadatos de la canción (llamado <nombre_cancion>.txt) por una compatible con la última versión del karaoke.

Concretamente cada canción se graba en una carpeta que contiene, el fichero de karaoke de la canción (llamado <nombre_cancion>.txt), y una serie de ficheros que especifican la portada del disco, el videoclip de la canción, y opcionalmente el mp3 de la canción (todos almacenados en ficheros llamados <nombre_cancion>.(jpg|avi|mp3).

Su solución debe copiar los ficheros binarios en una estructura de directorios similar a la original y procesar el fichero de karaoke implementando las modificaciones necesarias para que podamos usar el karaoke en nuestra fiesta.

Concretamente las modificaciones que hay que hacer en los ficheros de karaoke de la canción son:
- Si la línea del fichero comienza con el texto "#GAP:", significa que especifica el tiempo que hay entre el comienzo del video y el comienzo de la reproducción de la letra de la canción. Este valor debe ser un entero, por lo que si el valor especificado en el fichero contiene el carácter ',' hay que redondear el valor al valor entero más cercano.
- Si la linea del fichero comienza con el texto: "#BPM:", significa que especifica la velocidad a la que debe reproducirse (avanzar) la letra de la canción en el karaoke. Este valor debe ser un entero, por lo que si el valor especificado en el fichero contiene el carácter ',' hay que redondear el valor al valor entero más cercano.
- El resto de líneas del fichero debe permanecer inalteradas.

Un ejemplo de fichero de canción sería:
```
#TITLE:Mi carro
#ARTIST:Manolo Escobar
#LANGUAGE:EspaÃ±ol
#EDITION:SingStar Cla ES
#YEAR:1969
#MP3:Manolo Escobar - Mi carro.mp3
#COVER:Manolo Escobar - Mi carro.jpg
#VIDEO:Manolo Escobar - Mi carro.avi
#VIDEOGAP:0
#BPM:219
#GAP:27074
: 0 4 54 Mi
: 4 6 59  ca
: 12 9 59 rro
- 22
: 24 3 58 Me
: 28 2 59  lo
: 30 3 61  ro
: 33 6 62 ba
: 39 3 61 ~
* 42 13 59 ron
- 57
: 62 3 59 Es
: 66 7 62 tan
: 74 9 61 do
- 84
: 86 3 64 De
: 90 3 62  ro
: 93 3 61 me
: 96 5 59 rí
: 101 3 58 ~
: 104 7 54 a
- 113 114
: 125 3 54 Mi
: 129 5 59  ca
: 136 11 59 rro
- 148
: 149 3 58 Me
: 152 2 59  lo
: 155 2 61  ro
: 157 5 62 ba
: 163 3 61 ~
: 166 13 59 ron
- 181
: 188 3 59 A
* 191 7 62 no
: 199 11 61 che
- 211
: 211 4 64 Cuan
: 216 3 62 do
: 220 3 61  dor
: 223 4 59 mí
: 227 2 58 ~
: 229 4 54 a
- 235
```
...
```
: 1571 2 55 Y
: 1574 2 55  por
: 1577 1 55  fin
: 1579 2 55  lo-en
: 1582 3 57 con
: 1587 12 59 tré
- 1601
: 1604 4 61 Sin
: 1609 3 62  a
: 1613 3 64 ta
* 1617 53 66 lajes
E
```

