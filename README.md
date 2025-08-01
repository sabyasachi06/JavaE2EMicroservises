# JavaE2E Microservices
This is an end-to-end microservices implementation with all Devops and logging concepts

1) The accounts microservice is functional now, all the exceptions are handled.
2) The CURD api are listed below.
3) The forward actions are to implement swagger/openapi documentations, customer and loans service implementation.

# List of CURD API and how they should be called in postman.

1) We need to have our h2 console open for visualising the data in the DB (http://localhost:8080/h2-console), later will replace it with my-sql db.
2) Create a new account request.
    
    POST : http://localhost:8080/api/create
    
```JSON
   {
   "name": "Sabyasachi Dash",
   "email": "test@gmail.com",
   "mobileNumber": "0123456789"
   } 
```
3) Get the account details.

    GET : http://localhost:8080/api/fetch?mobileNumber=0123456789
4) Update the account details.
    
    PUT : http://localhost:8080/api/update

```JSON 
  {
   "name": "Sabyasachi Dash",
   "email": "test@gmail.com",
   "mobileNumber": "9999999999",
   "accountsDto": {
   "accountNumber": 1465609714,
   "accountType": "Savings",
   "branchAddress": "123 Main Street, New York"
   },
  }
```        
5) Delete the created account.

    DELETE : http://localhost:8080/api/delete?mobileNumber=999999999

# Swagger API document generation.
1) In order to generate the documentation we need to add the swagger UI dependency in the pom.
 ```xml
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.8.1</version>
   </dependency>
```
# Implemented cards and loans microservices, running in 8090 and 9000 ports respectively.

# To generate the docker images again, use the below maven command.
```yaml
mvn compile jib:dockerBuild
```

# To start the mysql docker container locally, use the below command. (NOTE : here -e means environment variables)
```
docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
```
# To access the mysql from a client, we can download sqlectron, locally in our system.

# Check for which network your containers are running using the below command.
```
docker network ls
```

# Check you docker container is attached to which network id of the above output.
```yaml
docker inspect containerId 
```
