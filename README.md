# Flights

To search the flights between source and destination

All the flights will be displayed in ASC order on duration to reach to destination from source

To search the flights

hit the url http://localhost:8080/flights?src=HYDD&dest=DEL

where src and dest are taking in RequestParam.

In case of no flights found betwenn source and destination, application will throw NOT_FOUND HttpStatus instead of NO_CONTENT, ths is because of to display exception message to the user.
