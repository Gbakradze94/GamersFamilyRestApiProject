# GamersFamilyRestApiProject


### Technology Stack
| Component      | Technology                                                  |
|----------------|-------------------------------------------------------------|
| Frontend       | [React](https://reactjs.org/)                               |                    
| Backend (REST) | [SpringBoot](https://projects.spring.io/spring-boot) (Java) |
| Server Build   | Maven(Java)                                                 |

### Build Backend (SpringBoot Java)
```bash
# Maven Build : Navigate to the root folder where pom.xml is present and run:
mvn clean install
```

### Accessing Application
 | Component         | URL                                   | Credentials                                                                           |
|-------------------|---------------------------------------|---------------------------------------------------------------------------------------|
 | Backend           | http://localhost:8080/games           | ``                                                                                    |
 | H2 Database       | http://localhost:8080/h2              | Driver:`org.h2.Driver` <br/> JDBC URL:`jdbc:h2:file:./data/fileDb` <br/> User Name:`` |
| Swagger (API Ref) | http://localhost:8080/swagger-ui.html | 
