namespace java zdavep.hello

struct HelloMsg {
  1: required string name
}

service HelloService {
  HelloMsg sayHello(1:HelloMsg msg),
  void ping()
}
