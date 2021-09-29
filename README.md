[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/eduardoquerido/springboot)

## Requisitos mínimos
- Iniciar o ambiente no [gitpod.io](https://gitpod.io/#https://github.com/eduardoquerido/springboot)

## Rodar projeto
Build
```
mvn spring-boot:run
```
Testes
```
mvn test
```

## Rotas da API Rest
Raiz
```
GET: <base_url>/api
RESPONSE: Bem vindo a API do BOL.com.br!
```

Profile
```
GET: <base_url>/api/profile
RESPONSE: [Profile]

GET: <base_url>/api/profile/:id
RESPONSE: Profile

DELETE: <base_url>/api/profile/:id
RESPONSE: Id
```

Registration
```
GET: <base_url>/api/registration
RESPONSE: [Registration]

GET: <base_url>/api/registration/:id
RESPONSE: Registration

POST: <base_url>/api/registration
BODY: {
  email, password, name, cellphone, audioHash, webGLHash, canvasHash
}
RESPONSE: Registration

DELETE: <base_url>/api/registration/:id
RESPONSE: Id

PUT: <base_url>/api/registration/:id
BODY: {
  email, password, name, cellphone, audioHash, webGLHash, canvasHash
}
RESPONSE: Registration
```

POST/PUT Exemplo API
```
{
  "email": "<email>",
  "password": "<password>",
  "name": "<name>",
  "cellphone": "<celular>",
  "audioHash": "<hashqualquer>",
  "webGLHash": "<hashqualquer>",
  "canvasHash": "<hashqualquer>"
}
```


