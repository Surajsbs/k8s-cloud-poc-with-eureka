FROM java:8
EXPOSE 9002
ADD target/department-service-app.jar /home/department-service-app.jar 
ENTRYPOINT ["java"]
CMD ["-jar", "/home/department-service-app.jar"]