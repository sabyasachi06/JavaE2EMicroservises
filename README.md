# JavaE2E Microservices
This is an end-to-end microservices implementation with all Devops and logging concepts

1) The accounts microservice is functional now, all the exceptions are handled.
2) The CURD api are listed below.
3) The forward actions are to implement swagger/openapi documentations, customer and loans service implementation.

# List of CURD API and how they should be called in postman.

1) We need to have our h2 console open for visualising the data in the DB (http://localhost:8080/h2-console), later will replace it with my-sql db.
2) Create a new account request.
    
    POST : http://localhost:8080/api/create
    
    JSON : {
   "name": "Sabyasachi Dash",
   "email": "test@gmail.com",
   "mobileNumber": "0123456789"
   } 
3) Get the account details.

    GET : http://localhost:8080/api/fetch?mobileNumber=0123456789
4) Update the account details.
    
    PUT : http://localhost:8080/api/update

    JSON : {
   "name": "Sabyasachi Dash",
   "email": "test@gmail.com",
   "mobileNumber": "9999999999",
   "accountsDto": {
   "accountNumber": 1465609714,
   "accountType": "Savings",
   "branchAddress": "123 Main Street, New York"
   }
   }
5) Delete the created account.

    DELETE : http://localhost:8080/api/delete?mobileNumber=999999999