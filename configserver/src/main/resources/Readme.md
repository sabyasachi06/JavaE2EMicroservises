# Config Server

## First we create config server from spring cloud projects.
The below annotation is used in application class to enable the config server.
```
@EnableConfigServer
```

Then we create a folder in classpath named as config, where we keep all our configurations/yml files.
```
configFolder
```

Next in spring config application.yaml file. Details are as below.

```yaml
spring:
  application:
    name: "configserver"  # this is the application name
  profiles:
     active: native  # Since we have our configurations in classpath so native
  cloud:
    config:
      server:
         native:
           search-locations: "classpath:/config"  # Location of the class path
server:
  port: 8071  # Spring config server port.
```


```
http://localhost:8071/cards/prod
http://localhost:8071/cards/qa
http://localhost:8071/cards/default
```
