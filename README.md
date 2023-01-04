## Simple Railway/SpringBoot App
### How to use
1. Compile the project:
```
mvn compile
```
2. Package the jar. Will name the jar according to artifact group naming.
```
mvn package
```

3. Gets rid of the artifacts, cleans up the Maven project. 
```
mvn clean
```

4. Runs the server locally to test and allows client browsers to fetch the index.html file. 
(Rerun server every time you have new info or files in order to reload changes.)
```
mvn spring-boot:run
```

5. Login to railway, this will launch your browser.
```
railway login
```
6. Init your project to enable railway
```
railway init
```

7. Change the jdk version under the pom.xml (if using java 17). Railway doesn't use jdk 11
```
<properties>
    <java.version>11</java.version>
</properties>
```
8. When provisioning on Railway is completed, upload to the Railway servers.
```
railway up
```

9. Wait a few minutes as Railway builds and deploys our app.

10. Create a custom domain for your app under settings. Now you can access the simple app under that URL.