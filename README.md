# wiresto-rest-api

## Installing
Requires Java 8
```
mvn clean install
```

## Starting App
System property 'http.port' is optional. Default HTTP por is 5000
```
java -Dhttp.port=5000 -jar wiresto-rest-api.jar
```

## Running the tests
Example
```
http://localhost:5000/wiresto/menus

http://localhost:5000/wiresto/menus/{menuId}

http://localhost:5000/wiresto/menus/{menuId}/items

http://localhost:5000/wiresto/menus/{menuId}/items?groupBy=price
```


## See WIKI for more implementation details.
