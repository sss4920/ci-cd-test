FROM amd64/amazoncorretto:17

WORKDIR /app

COPY ./build/libs/*.jar /app/sixseminar-0.0.1-SNAPSHOT.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "sixseminar-0.0.1-SNAPSHOT.jar"]