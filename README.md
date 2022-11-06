Signup & Tokenized Login -> proxied through consumer-service (dummyService) : port = 8082

Signup -> http://localhost:8082/consumer/add-user <br>
Login -> http://localhost:8082/consumer/login

1) Proxied authentication-service (in which token is created) in consumer-service (dummyService)
2) Then, Proxied admin-service (the login signup one) in consumer-service (dummyService)
3) Manipulated the admin-service URIs according to need (tokenized).