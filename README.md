# Interfaz Calculadora
Este repositorio contiene una aplicación básica de una calculadora desarrollada en dos versiones diferentes de interfaces de usuario, separadas en dos ramas diferentes. La funcionalidad principal de ambas versiones es la misma, pero se ha implementado utilizando dos enfoques distintos.

## Versiones
### XML
Versión que utiliza los archivos XML tradicionales para definir la interfaz de usuario.
La rama del repositorio que contiene esta versión e implementa *ConstraintLayout* se denomina **xml_cl**, mientras que la que implementa *LinearLayout* se denomina **xml_ll** (_desactualizada_).

### Jetpack Compose
Versión que utiliza la nueva librería Jetpack Compose para construir la interfaz de usuario de forma declarativa.
La rama del repositorio que contiene esta versión se denomina **compose**.

## Diseño
### Layout
Los botones de la calculadora se han dispuesto en una matriz de **6x4** en modo vertical y **4x6** en modo apaisado, optimizando así el uso del espacio disponible en ambas orientaciones. En el modo apaisado, se ha escogido una distribución más amplia que mejora la interacción al ofrecer botones más grandes y fácilmente accesibles.

<img src="https://github.com/user-attachments/assets/1955daa4-e12c-430d-a51c-d96527c44e9c" alt="Layout" width="800">

### Colores
[Paleta de colores](https://coolors.co/000000-021e2c-219ebc-8ecae6-fb8500-ffb703-ffffff) utilizada en los diferentes componentes de la interfaz de la calculadora: 

<img src="https://github.com/user-attachments/assets/43128fb8-bd49-47a7-affb-59727afed69e" alt="Palette" width="800" style="border: 2px solid black;">

## Funcionalidad
### Botones
Los botones son completamente funcionales, de forma que se imprimen los caracteres en su campo de texto correspondiente y se calcula la operación al hacer clic en el icono de igualar. El campo de operación se puede resetear mediante el botón AC o eliminar caracter a caracter mediante el botón de eliminar. Se mantiene el estado de los textos al cambiar la orientación del dispositivo, puesto que se guarda el estado de la vista.
### Librería 
La implementación de la funcionalidad del cálculo de las operaciones se ha llevado a cabo mediante la librería [exp4j](https://github.com/fasseg/exp4j).
