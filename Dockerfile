FROM alpine:3.17.2
RUN apk add --no-cache openjdk17
COPY build/libs/stocks-app-1.0.0.jar /app/
ENTRYPOINT ["java"]
CMD ["-jar", "app/stocks-app-1.0.0.jar"]