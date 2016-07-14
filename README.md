# HTTP2 w/ Spring Boot + Undertow

Node, you need to use the alpn version that matches your java version (and update pom.xml accordingly)
https://www.eclipse.org/jetty/documentation/current/alpn-chapter.html#alpn-versions

    $ mvn clean package -Dmaven.test.skip=true
    $ mvn spring-boot:run

Go [https://localhost:8443](https://localhost:8443)

## Reference
* https://github.com/making/demo-http2

