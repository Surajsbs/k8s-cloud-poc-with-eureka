FROM java:8
EXPOSE 9001
ADD target/user-service-app.jar /home/user-service-app.jar 
ENTRYPOINT ["java"]
CMD ["-jar", "/home/user-service-app.jar"]