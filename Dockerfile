FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/booksharing.jar /booksharing/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/booksharing/app.jar"]
