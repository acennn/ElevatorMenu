## ElevatorMenu
----------------------------------------------------------------------------------------------------------------------------------------------------
*model.Elevator menu is an app that could select the best elevator from many, for person who wants to move from floor "A" to
floor "B".*
### Getting Started
----------------------------------------------------------------------------------------------------------------------------------------------------
**Step 1:**  
*Clone the project from: https://github.com/acennn/ElevatorMenu.git*  
**Step 2:**  
*Import the project with [IntelliJ IDEA](https://www.jetbrains.com/idea/) using [Gradle](https://gradle.org/).*
### The model.Elevator has these fields:
----------------------------------------------------------------------------------------------------------------------------------------------------
    int elevatorId; - it is getting generated automatically.
    double weightCapacity; - populated from the input file.
    int personCountCapacity; - populated from the input file.
    double currentWeight; - it is getting generated automatically.
    ArrayList<model.Person> loadedPersons; - it is getting generated automatically.
    int currentFloor; - populated from the input file.
    boolean isElevatorUP; - it is getting generated automatically.
    int destinationFloor; - it is getting generated automatically.
### The model.Person has these fields:
----------------------------------------------------------------------------------------------------------------------------------------------------
    int personId; -it is getting generated automatically.
    double weight; - populated from the input file.
    int startingFloor; - populated from the input file.
    int desiredFloor; - populated from the input file.
### Input file:
  ----------------------------------------------------------------------------------------------------------------------------------------------------

**Space** *delimited file. If you put **\#** (Number sign) as first sign on the row it is going to be skipped.*  
**Step 1:**  
*On the first row you put as **integer** the count of elevators that you would like to create (in the example it is 3).*  
**Step 2:**  
*Then on the next rows you put data for every elevator that you would like to create,
(**weightCapacity(double)** **personCountCapacity(int)** **currentFloor(int)**) you have to add it for each elevator on a **different row.***  
**Step 3:**  
*Then you put on the next row as **integer** the count of people that you would like to create (in the example it is 10).*  
**Step 4:**  
*Then on the next rows you put data for every person that you would like to create,
(**weight(double)** **startingFloor(int)** **desiredFloor(int)**) you have to add it for each person on a **different row.***  

### *Don't put a free space or tabs before or after the rows, or it is going to cause error.*

### Example for input file:

----------------------------------------------------------------------------------------------------------------------------------------------------

\# ELEVATORS  
\# Count Elevators  
3  
\# WeightCapacity PersonCountCapacity currentFloor  
500 2 0  
100 3 3  
500 3 3  
\# PERSONS  
\# Count Persons  
10  
\# Weight StartingFloor DesiredFloor  
120 3 77  
120 3 -3  
120 3 6  
20 3 5  
20 2 5  
20 3 5  
20 5 9  
20 3 5  
20 3 5  
20 2 5  

----------------------------------------------------------------------------------------------------------------------------------------------------

**Step 5:**  
*Once you have valid data as it is shown in the example, you have to the find **inputFile**
(**src/main/resources/elevatorsPersonsDataInput.txt**) here you could put your data.*  
**Step 6:**  
*Then, when you go to the **Main.class** and run the **main()** the result data is going to be populated in to the **resultFile** (**src/main/resources/fileElevatorsPersonsDataReport.txt**).*  
**Step 7:**  
*If you would like to use an external **input file** you have to go to the **Main.class** and change the* ***fileNameElevatorsPersonsDataInput:***
        **String fileNameElevatorsPersonsDataInput = "src/main/resources/elevatorsPersonsDataInput.txt";**  
*the string has to be all together with the correct path like this* **"C:\testFolder\testInputFile.txt"**.  

*If you would like to use an external **output file** you have to go to the **Main.class** and change the* ***fileNameElevatorsPersonsDataReport:***
       **String fileNameElevatorsPersonsDataReport = "src/main/resources/fileElevatorsPersonsDataReport.txt";**  
*the string has to be all together with the correct path like this* **"C:\testFolder\testOutputFile.txt".**

### Output file:
----------------------------------------------------------------------------------------------------------------------------------------------------

Created report on: 2018-09-09 16:07:34.611  

ElevatorId:2 loaded PersonId:22  
ElevatorId:2 loaded PersonId:27  
ElevatorId:1 loaded PersonId:29  
Iteration completed  

ElevatorId:3 loaded PersonId:15  
ElevatorId:3 loaded PersonId:16  
ElevatorId:3 loaded PersonId:18  
Iteration completed  

----------------------------------------------------------------------------------------------------------------------------------------------------

*On the first row there is the **date of the created report.**
On the next rows **ElevatorId** and the **PersonId** of the person that has been put on the elevator.*  

*On the next row once the elevators can't put any more persons the iteration is completed (**Iteration completed**) and they have to take every person to the correct floor and once they load off the elevators are going to take other people for next iteration.
All iterations  end once all the people have been loaded and taken to the correct floor (if possible, otherwise the rest have to take the stares :) ).*

### Tests
*If you want to run the tests you have to go to **src/test/java/Tests.class** and run **Tests.class**.*

### Built With
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management

### Versioning
*This is **v1.***

### Authors
* **Asen**

## License
*This project is licensed free.*
