FROM java:8
EXPOSE 8761
ADD target/registry-server-app.jar /home/registry-server-app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/home/registry-server-app.jar"]

