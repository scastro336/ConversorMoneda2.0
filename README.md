#ChallengeAlura ConversordeMonedas
-
-----------------------------------Descripcion de MiChallenge---------------------------------------------
Esta aplicacion Java realiza el cambio de Divisas Internacionales en tiempo real utilizando 
una API la cual es Exchangerate API y su URL es https://www.exchangerate-api.com/ la cual me 
brindo una key gratuita para poder hacer las consultas del valor de las Divisas
La aplicacion Java que he realizado muestra por consola un Menú Basico con 3 opciones:
1. Conversor de Monedas
   ----› Esta opcion nos lleva a otro Sub Menu
           Las divisas mas populares son las siguientes:
             1. Dolar (Estadounidense): USD
             2. Euro: EUR
             3. Peso Mexicano: MXN
             4. Dolar Canadiense: CAD
             5. Peso Argentino: ARS
             6. Real Brasileno: BRL
             7. Nuevo Sol Peruano: PEN
             8. Peso Venezolano: VES
             9. Si no has encontrado tu moneda, la puedes ver en la siguiente lista completa:
             Opcion que debemos ingresar por teclado y que nos llevara a 2 Sub Menu
                 Uno de estos Sub Menu va a ser si elegimos entre las opciones del 1 al 8
                 Ya que nos solicitara ingresar la cantidad de Divisas a cambiar, una vez ingresadas
                 solicitara ingresar cual es la divisa destino con otro enlistado exceptuando la divisa
                 elegida anteriormente
                 Si elegimos la opcion 9 nos mostraran todas las divisas disponibles en la API segun su
                 codigo de Moneda
            Una vez terminado la conversion nos llevara de nuevo al Menu principal 
2. Historial de Conversiones
   ----› Esta opcion nos muestra el historial de conversiones si es que se ha realizado algun cambio
         caso contrario nos mostrara un mensaje de que hasta el momento no se a realizado alguno
3. Salir
   ----›  Esta opcion finaliza el programa


------------------------------------------Forma de Uso del Programa----------------------------------------
1. Se muestra el Menu de 3 opciones, se debe elegir alguno
2. Caso de seleccionar Conversor de Monedas, elegir la Opcion conveniente
3. Ingresar monto a cambiar
4. Seleccionar Divisa destino
5. Se optiene el cambio con el valor real de la divisa en tiempo real
6. Se retorna al menu principal y se puede hacer la cantidad de Cambios deseados y revisar el historial de Cambios con la Opcion 2
7. Para salir seleccionar la Opcion 3

Muchas Gracias soy Sixto Castro y fue un gusto haber desarrolado este Challenge del Programa ONE en conjunto con Alura  
