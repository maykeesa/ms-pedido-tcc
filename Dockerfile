FROM maven as build

WORKDIR /pedido
COPY . .

RUN mvn clean package -Dspring.profiles.active=dev

FROM ghcr.io/graalvm/jdk-community:17

WORKDIR /pedido
COPY --from=build /pedido/target/ms-pedidos-0.0.1-SNAPSHOT.jar ./app.jar

CMD java -jar app.jar