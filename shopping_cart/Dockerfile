FROM openjdk:8-alpine
COPY ./target/shopping_cart-0.0.1-SNAPSHOT.jar /shopping_cart/
WORKDIR /shopping_cart
EXPOSE 4000
CMD ["java", "-jar", "shopping_cart-0.0.1-SNAPSHOT.jar"]