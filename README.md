# UserManagement
Microservice to handle users and company related activity , Register , login , update , forgotpassword etc
## clone the repository then switch to UserManagement directory
  Run  below command:
  
       1. 'mvn clean install'
  
       2. 'mvn spring-boot:run'
       
        curl -X POST http://localhost:8080/api/signin -H "Content-Type: application/json" -d '{"username":"abc","password":"test"}'
  
