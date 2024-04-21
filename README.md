# Remitly_Internship_Task_2024

## Description

The project checks given JSON file. Input JSON file should be defined as AWS::IAM::Role Policy. *noAsteriskInResources()* method returns false if input JSON is defined correctly and Resource field contains a single asterisk. Returns true otherwise.

## Installation

To clone the repostiory use the following command:
```bash
git clone https://github.com/Axan18/Remitly_Internship_Task.git
```
## Usage

  ### Running
  Run program using command:
  ```bash
  mvn compile exec:java
  ```
  You will have to choose JSON file with JFileChooser GUI.

---

  ### Testing
  Run tests with command:
  ```bash
  mvn test
  ```

---

  ### JAR packing
  To make JAR file use:
  ```bash
  mvn package
  ```

---

### JavaDoc
To generate documentation, following command will be needed:
```bash
mvn javadoc:javadoc
```
