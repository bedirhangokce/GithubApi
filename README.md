# Github API Project

## Introduction

This project is a Java application that retrieves data from the Github API and writes it to a file. It consists of three classes:

- `GithubApi` (main class): the entry point for the application.
- `Connector`: a utility class that makes connections to the Github API.
- `UserInfo`: a class that represents a Github user and their contributions to a repository.

## How to Run

To run the project, follow these steps:

1. Open a command prompt or terminal window.
2. Navigate to the directory where the project files are located.
3. Compile the Java files by running the command: 
```
     javac -cp lib/json-20220924.jar *.java
```
4. Create a JAR file by running the command: 
```
     jar cvfm GithubApi.jar manifest.txt *.class
```
5. Run the JAR file by running the command: 
```
     java -jar GithubApi.jar
```
6. The output will be written to a file named `output.txt` in the same directory as the project files.

## GithubApi Class

The `GithubApi` class is the main class of the application. It retrieves data from the Github API by making HTTP connections using the `Connector` class. It then writes the data to a file using the `BufferedWriter` class.

## Connector Class

The `Connector` class is a utility class that makes HTTP connections to the Github API. It uses the `HttpURLConnection` class to establish connections and retrieve data from the API.

## UserInfo Class

The `UserInfo` class represents a Github user and their contributions to a repository. It has fields for the user's name, location, company, and the number of contributions they have made to a repository.

## Conclusion

This project demonstrates how to retrieve data from the Github API using Java. It also shows how to write the data to a file using the `BufferedWriter` class. By following the instructions above, you can run the project and see the data that is retrieved from the API.