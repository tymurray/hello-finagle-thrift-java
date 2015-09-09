#!/usr/bin/env python

import sys
sys.path.append('gen-py')

import hello
from hello import HelloService
from hello.ttypes import HelloMsg

from thrift.transport import TTransport
from thrift.transport import TSocket
from thrift.protocol import TBinaryProtocol

host, port = 'localhost', 5555
text = (' '.join(sys.argv[1:])).strip()
if not text:
    text = 'from Python'

transport = TTransport.TFramedTransport(TSocket.TSocket(host, port))
client = HelloService.Client(TBinaryProtocol.TBinaryProtocol(transport))
transport.open()
try:
    client.ping()
    msg = client.sayHello(HelloMsg(text))
    print(msg)
finally:
    transport.close()
