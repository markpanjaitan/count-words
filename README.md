# Count Words Application Using Spring Boot, Excel, REST API


### Objective
   
```
To create a program that counts Words that processes a List of Strings and applies the following business rules.
```

### Description

```
Business Rules :
- Counts and returns the NUMBER of words (i.e. Strings) that start with "M" or "m"
- Returns all the words longer than 5 characters
```

### UML

![UML_count_words](https://github.com/markpanjaitan/count-words/assets/39697444/ebe469ef-db74-4564-97a8-5765197f4699)

```
Explanation :
- User upload the Excel file which contains list of names
- The system will validate the file
- If valid then system will read the file, count the words, and then return the result to the user
```

### CURL

```
curl --location 'localhost:8081/count-words/upload' \
--form 'file=@"/D:/Data/2023/September/test3.xlsx"'
```

### Response Example

![response_example1](https://github.com/markpanjaitan/count-words/assets/39697444/b0e6c82f-64ee-4bb5-a67d-16062f6173fe)
