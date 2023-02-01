# Shop_Backend

### This is shop backend application.
### Application is made in the form of a rest api.
### I also create frontend app for this application: https://github.com/Doman5/Shop_Frontend

## Technologie used
  - java 17
  - spring boot
  - spring JPA
  - spring mail
  - spring security
  - hibernate
  - liquibase
  - Mysql
  - jwt
  - lombok
  - common-io
  - swagger UI

## Functionalities for customer
  - login
  - registry
  - view all products
  - add produc to cart
  - view category with products
  - order product what you have inside cart
  - add review for products
  
## Functionalities for admin
   - view, add, update and delete product
   - upload car photos
   - view, add, update and delete categories
   - view all orders with status change
   - view orders statistic
   - export data to csv file for a specific time period
   
## Another functionalities
   - basic security using spring security
   - jwt token authentication
   - checking photo names
   - email sender with using adapter pattern
   - logged status changes for orders
   - clean old carts
  
 ## How to start aplication
 
 you muss have created database named kursaplikacji working on port 3306 
 ```
 mvn package
 java -jar carRent-0.0.1-SNAPSHOT.jar
 ```
 after this can you view documentation on http://localhost:8080/swagger-ui/index.html
