# CHALLENGE MERCADOLIBRE

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En alguno de los siguientes lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

•	boolean isMutant(String[] dna); // Ejemplo Java
En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

**No-Mutante**

A T G C G A

C A G T G C

T  T A T T  T

A G A C G G

G C G T C A

T C A  C T G

**Mutante**

A T G C G A

C A G T G C

T T A T G  T

A G A A G G

C C C C T  A

T C A C T  G

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical. Ejemplo (Caso mutante):
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
En este caso el llamado a la función isMutant(dna) devuelve “true”. Desarrolla el algoritmo de la manera más eficiente posible. Desafíos:

### Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por Magneto.

### Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:
POST → /mutant/ { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden

### Nivel 3:

Anexar una base de datos, la cual guarde los ADN’s verificados con la API. Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN: {"count_mutant_dna" : 40, "count_human_dna" : 100, "ratio" : 0.4 } Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo). Test-Automáticos, Code coverage > 80%.



## Configuración




-  Java 1.8



## Implementación y desarrollo




La aplicación se desarrolló en Java 1.8, algunas características de la implementación fueron:
	•	 Spring boot 2
	
	•	 Gradle  
	
	•	 Patrón MVC
	
	•	 Base de datos en memoria H2
	
	•	 JaCoCo
	
	•	 Lombok




## Ejecución




##### 1. Ambiente localAmbiente local

Si se quiere ejecutar la aplicación en un entorno local, se pueden realizar de dos maneras:

###### 1.1 Docker

- Descargue la imagen



    docker pull camilo1184/challenge-meli:challenge

- Ejecute la imagen con el comando:


    docker run -p 8080:8080 camilo1184/challenge-meli:challenge .

1.2 Descargue las fuentes y ejecute el .jar generado desde una consola

- Descargue el proyecto del repositorio y ejecute en consola


    gradlew clean build

- Ejecute el jar generado con el siguiente comando



    java -jar build/libs/ChallengeMeli-0.0.1.jar

###### AMAZON AWS

La aplicación fue desplegada en los servicios de Amazon, generando la url para consumir los servicios:



    http://challengemeli-env.eba-qgnimnum.us-east-2.elasticbeanstalk.com/challenge




## Pruebas





En la carpeta /src/main/resources/postman dentro de las fuentes del proyecto encontramos una colección de postman con la cual se puede probar los consumos de los servicios tanto de forma local como a los servicios hospedados en Amazon AWS







## Servicios implementados




##### -	IsMutant

Request:


    POST 
Request body ADN mutante:
 

     {"dna":["ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}
Response:
  

    200 OK
Request body ADN humano:


      {"dna":["AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"]}
Response:
 

     403 Forbidden
  
##### -	Stats
Request:


    GET
Response: 200 


    {
        "countMutantDna": 9,
        "countHumanDna": 5,
        "ratio": "1.8"
    }





