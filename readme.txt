Asunciones:

1.-La url para hacer el loggeo al servicio no me funciono, incluso trate de obtener un token valido en postman y no me funciono. Entonces, solo consumí un servicio conocido "emulando" el loggin que
     esta harcodeado como omardiazdiaz@gmail.com y pwd 1234.
2.-Al no poder contar con el token (Porque no pude hacer el loggeo), entonces la segunda url no me iba a funcionar, pero como note que la intencion era: Consumir un servicio que respondiera un JSON,
    parsearlo y mostrarlo en una web page. Eso fue lo que hice con otro servicio. Consumi el servicio,obtuve las respuestas de las transacciones y las mostre en una web, la cual, 
    mediante un par de cajas de texto, muestra u oculta las rows pedidas.
3.-En este mismo proyecto, hice el servicio para el utc. Compare los resultados, mostrados por mi web service con la siguiente url: https://time.is/es/GMT+1
4.-Para acceder a este servicio local seria con la url http://localhost:8080/utc?time=8:00:00&zone=3
5.-Para acceder a la web seria esta url: http://localhost:8080/
6.-Aqui mismo podran encontrar el word con la respuesta al primer planteamiento.