FROM java:8
EXPOSE 9001
ADD target/emp-service-app.jar /home/emp-service-app.jar 
ENTRYPOINT ["java"]
CMD ["-jar", "/home/emp-service-app.jar"]