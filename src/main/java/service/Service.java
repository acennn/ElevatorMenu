package service;

import dto.ElevatorDTO;
import dto.PersonDTO;
import model.Elevator;
import model.Person;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {

    private static final Logger LOGGER = Logger.getLogger(Service.class.getName());

    /***
     *Parsing data from the read file for elevators
     * @param elevatorsPersonsReadData is ArrayList<String> contains all the read data from the input file every line
     * from the input file is represented as a separate string in it.
     * @return is ArrayList<ElevatorDTO> containing all the DTOs with data parsed from the input file for creating elevators.
     */
    public static ArrayList<ElevatorDTO> parseElevatorsData(ArrayList<String> elevatorsPersonsReadData) {
        double weightCapacity;
        int personCountCapacity;
        int currentFloor;
        String[] elevatorData;
        int numberLinesElevators;
        try {
            numberLinesElevators = Integer.parseInt(elevatorsPersonsReadData.get(0).trim());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, String.format("Wrong data or format causing parsing error on row 0 numberLinesElevators: %s", elevatorsPersonsReadData.get(0)), e);
            throw new NumberFormatException(String.format("Wrong data or format causing parsing error on row 0 numberLinesElevators: %s", elevatorsPersonsReadData.get(0)));
        }
        ArrayList<ElevatorDTO> elevatorsDTOs = new ArrayList<>();
        for (int i = 1; i < numberLinesElevators + 1; i++) {
            try {
                elevatorData = elevatorsPersonsReadData.get(i).trim().split(" ");
                weightCapacity = Double.parseDouble(elevatorData[0]);
                personCountCapacity = Integer.parseInt(elevatorData[1]);
                currentFloor = Integer.parseInt(elevatorData[2]);
                ElevatorDTO currentElevatorDTO = new ElevatorDTO();
                currentElevatorDTO.setWeightCapacity(weightCapacity);
                currentElevatorDTO.setPersonCountCapacity(personCountCapacity);
                currentElevatorDTO.setCurrentFloor(currentFloor);
                elevatorsDTOs.add(currentElevatorDTO);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, String.format("Wrong elevator data or format causing parsing error on row %d:", i), e);
                throw new NumberFormatException(String.format("Wrong elevator data or format causing parsing error on row %d:", i));
            }
        }
        return elevatorsDTOs;
    }

    /***
     * Create all the elevators from the parsed data
     * @param elevatorsDTOsList is ArrayList<ElevatorDTO> every DTO in it contains parsed data from the input file
     * weightCapacity, personCountCapacity, currentFloor enough for creating an elevator.
     * @return is ArrayList<Elevator> containing all the created elevators from the input file data
     */
    public static ArrayList<Elevator> createElevators(ArrayList<ElevatorDTO> elevatorsDTOsList) {
        ArrayList<Elevator> elevators = new ArrayList<>();
        double weightCapacity;
        int personCountCapacity;
        int currentFloor;
        for (ElevatorDTO elevatorDTO : elevatorsDTOsList) {
            weightCapacity = elevatorDTO.getWeightCapacity();
            personCountCapacity = elevatorDTO.getPersonCountCapacity();
            currentFloor = elevatorDTO.getCurrentFloor();
            Elevator currentElevator = new Elevator(weightCapacity, personCountCapacity, currentFloor);
            LOGGER.log(Level.INFO, String.format("Successfully created elevator Id:%d weightCapacity:%.2f, personCountCapacity:%d, currentFloor:%d", currentElevator.getElevatorId(), weightCapacity, personCountCapacity, currentFloor));
            elevators.add(currentElevator);
        }
        return elevators;
    }

    /***
     *Parsing data from the read file for persons
     * @param elevatorsPersonsReadData is ArrayList<String> contains all the read data from the input file every line
     * from the input file is represented as a separate string in it.
     * @return is ArrayList<PersonDTO> containing all the DTOs with data parsed from the input file for creating persons.
     */
    public static ArrayList<PersonDTO> parsePersonsData(ArrayList<String> elevatorsPersonsReadData) {
        double weight;
        int startingFloor;
        int desiredFloor;

        String[] personData;
        int numberLinesPersons;
        //the input file is the same for elevators and persons and the persons data start right after the elevators
        // so the first line with data about the persons is the model.Elevator.getCountCreatedElevators()
        int firstLinePersonsDataInInputFile = Elevator.getCountCreatedElevators() + 1;
        try {
            numberLinesPersons = Integer.parseInt(elevatorsPersonsReadData.get(firstLinePersonsDataInInputFile).trim());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, String.format("Wrong data or format causing parsing error on row %d numberLinesPersons: %s", firstLinePersonsDataInInputFile, elevatorsPersonsReadData.get(firstLinePersonsDataInInputFile)), e);
            throw new NumberFormatException(String.format("Wrong data or format causing parsing error on row %d numberLinesPersons: %s", firstLinePersonsDataInInputFile, elevatorsPersonsReadData.get(firstLinePersonsDataInInputFile)));
        }
        ArrayList<PersonDTO> personsDTOs = new ArrayList<>();
        for (int i = firstLinePersonsDataInInputFile + 1; i < firstLinePersonsDataInInputFile + numberLinesPersons + 1; i++) {
            try {
                personData = elevatorsPersonsReadData.get(i).trim().split(" ");
                weight = Double.parseDouble(personData[0]);
                startingFloor = Integer.parseInt(personData[1]);
                desiredFloor = Integer.parseInt(personData[2]);
                PersonDTO currentPersonDTO = new PersonDTO();
                currentPersonDTO.setWeight(weight);
                currentPersonDTO.setStartingFloor(startingFloor);
                currentPersonDTO.setDesiredFloor(desiredFloor);
                personsDTOs.add(currentPersonDTO);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, String.format("Wrong person data or format causing parsing error on row %d:", i), e);
                throw new NumberFormatException(String.format("Wrong person data or format causing parsing error on row %d:", i));
            }
        }
        return personsDTOs;
    }


    /***
     * Create all the persons from the parsed data
     * @param personsDTOsList is ArrayList<PersonDTO> every DTO in it contains parsed data from the input file
     * weight, startingFloor, desiredFloor enough for creating a person.
     * @return is ArrayList<Person> containing all the created elevators from the input file data
     */
    public static ArrayList<Person> createPersons(ArrayList<PersonDTO> personsDTOsList) {
        double weight;
        int startingFloor;
        int desiredFloor;
        ArrayList<Person> persons = new ArrayList<>();
        for (PersonDTO personDTO : personsDTOsList) {
            weight = personDTO.getWeight();
            startingFloor = personDTO.getStartingFloor();
            desiredFloor = personDTO.getDesiredFloor();
            Person currentPerson = new Person(weight, startingFloor, desiredFloor);
            LOGGER.log(Level.INFO, String.format("Successfully created person Id:%d weight:%.2f, startingFloor:%d, desiredFloor:%d", currentPerson.getPersonId(), weight, startingFloor, desiredFloor));
            persons.add(currentPerson);
        }
        return persons;
    }

    /***
     * Find the best elevator for a person for the current iteration.
     * @param elevators is ArrayList<Elevator> containing all the created elevators.
     * @param person is the Person for which we are searching appropriate elevator.
     * @return is Elevator that is appropriate for the given person or null if there is no appropriate one.
     */
    public static Elevator findAppropriateElevatorForPerson(ArrayList<Elevator> elevators, Person person) {
        LinkedList<Elevator> sameDirectionFreeSpaceWeightElevators = new LinkedList<>();
        ArrayList<Elevator> sameDirectionElevatorsFull = new ArrayList<>();
        LinkedList<Elevator> emptySameFloorElevators = new LinkedList<>();
        LinkedList<Elevator> emptyElevators = new LinkedList<>();
        for (Elevator elevator : elevators) {
            boolean isSameElevationElevatorAndPerson = person.getStartingFloor() == elevator.getCurrentFloor();
            boolean isPersonDirectionUP = person.getStartingFloor() < person.getDesiredFloor();
            boolean isSameDirectionElevatorAndPerson = elevator.getLoadedPersons().size() == 0 || isPersonDirectionUP == elevator.isElevatorUP();
            boolean isFreeSpace = elevator.getLoadedPersons().size() < elevator.getPersonCountCapacity();
            boolean isFreeWeight = person.getWeight() + elevator.getCurrentWeight() <= elevator.getWeightCapacity();
            boolean isEmpty = elevator.getLoadedPersons().size() == 0;
            if (isSameElevationElevatorAndPerson && isSameDirectionElevatorAndPerson && isFreeSpace && isFreeWeight) {
                LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                return elevator;
            }
            if (isSameElevationElevatorAndPerson && isEmpty && isFreeSpace && isFreeWeight) {
                emptySameFloorElevators.add(elevator);
            }
            if (isSameDirectionElevatorAndPerson && isFreeSpace && isFreeWeight) {
                sameDirectionFreeSpaceWeightElevators.add(elevator);
            }
            if (isSameDirectionElevatorAndPerson && (!isFreeSpace || !isFreeWeight)) {
                sameDirectionElevatorsFull.add(elevator);
            }
            if (isEmpty && isFreeSpace && isFreeWeight) {
                emptyElevators.add(elevator);
            }
        }
        if (emptySameFloorElevators.size() > 0) {
            Elevator elevator = emptySameFloorElevators.getFirst();
            LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
            return elevator;
        }
        //checking if there is an overloaded elevator that has a person who is going to desired at floor before
        //current person starting floor so it could load the current person if he is in the same direction as elevator.
        if (sameDirectionElevatorsFull.size() > 0) {
            for (Elevator elevator : sameDirectionElevatorsFull
            ) {
                ArrayList<Person> loadedPersons = elevator.getLoadedPersons();
                for (Person personInElevator : loadedPersons
                ) {
                    if (personInElevator.getDesiredFloor() == person.getStartingFloor()) {
                        LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                    if (elevator.isElevatorUP() && personInElevator.getDesiredFloor() < person.getStartingFloor()) {
                        LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                    if (!elevator.isElevatorUP() && personInElevator.getDesiredFloor() > person.getStartingFloor()) {
                        LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                }
            }
        }
        if (sameDirectionFreeSpaceWeightElevators.size() > 0) {
            Elevator elevator = sameDirectionFreeSpaceWeightElevators.getFirst();
            LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
            return elevator;
        }
        if (emptyElevators.size() > 0) {
            Elevator elevator = emptyElevators.getFirst();
            LOGGER.log(Level.INFO, String.format("model.Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
            return elevator;
        }
        LOGGER.log(Level.INFO, String.format("Not found elevator for the person in current iteration personId:%d", person.getPersonId()));
        return null;
    }

    /***
     * Check if no persons have been loaded on the current iteration.
     * @param persons is ArrayList<Person> persons all created persons that are not loaded
     * @param personsCountAfterIteration count of the created  persons that are not loaded after the iteration
     */
    public static void checkIfNoElevatorAvailable(ArrayList<Person> persons, int personsCountAfterIteration) {
        if (persons.size() == personsCountAfterIteration) {
            throw new RuntimeException("No elevator available take the stairs :) ");
        }
    }

    /***
     * Creating the string for report and adding some comment data at the beginning.
     * @return is StringBuilder that will be our report with added first columns names
     */
    public static StringBuilder startReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n");
        report.append("Created report on: ");
        report.append(String.valueOf(new Timestamp(System.currentTimeMillis())));
        report.append("\n");
        report.append("\n");
        LOGGER.log(Level.INFO, "Started creating the report and added first info rows");
        return report;
    }

    /***
     * Loading all possible persons for one iteration and adding log to the report
     * @param elevators is ArrayList<Elevator> containing all the created elevators.
     * @param persons is ArrayList<Person> persons containing all the Persons not loaded before this iteration
     * @param loadedPersonsToRemove is ArrayList<Person>  we are adding to all the loaded persons in current iteration
     *                              so we are going to remove them from the list before the next one.
     * @param report is StringBuilder to which we are adding the data for the report
     * @param personsCount is Int and contains the count of not loaded people before the current iteration.
     */
    public static void loadAllPossiblePersonsForOneIterationAndReport(ArrayList<Elevator> elevators, ArrayList<Person> persons, ArrayList<Person> loadedPersonsToRemove, StringBuilder report, int personsCount) {
        for (int i = 0; i < personsCount; i++) {
            Elevator elevator = Service.findAppropriateElevatorForPerson(elevators, persons.get(i));
            if (elevator != null) {
                loadPerson(elevator, persons.get(i));
                loadedPersonsToRemove.add(persons.get(i));
                addElevatorIdPersonIdToReport(persons, report, i, elevator);
            }
        }
        report.append("Iteration completed\n");
        LOGGER.log(Level.INFO, "Loaded all possible persons for current iteration and added to report");
    }

    /***
     * Add current elevatorId and personId to the report.
     * @param persons is the ArrayList<Person> that contain the person that has to be added to the report
     * @param report  is the StringBuilder which represents our report
     * @param counter is index in the persons ArrayList<Person> of the person that has to be added to the report
     * @param elevator is elevator that has to be added to the report.
     */
    public static void addElevatorIdPersonIdToReport(ArrayList<Person> persons, StringBuilder report, int counter, Elevator elevator) {
        report.append("ElevatorId:");
        report.append(elevator.getElevatorId());
        report.append(" loaded PersonId:");
        report.append(persons.get(counter).getPersonId());
        report.append("\n");
        LOGGER.log(Level.INFO, String.format("Added to the report elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), persons.get(counter).getPersonId()));
    }

    /***
     * Clear used elevators weight person count and change the current floor before next iteration.
     * @param elevators is ArrayList<Elevator> that contain the used elevators in last interaction
     *
     */
    public static void clearUsedElevatorWeightPersonCountFloor(ArrayList<Elevator> elevators) {
        for (Elevator elevator : elevators) {
            if (elevator.getLoadedPersons().size() > 0) {
                elevator.setCurrentFloor(elevator.getDestinationFloor());
                elevator.getLoadedPersons().clear();
                elevator.setCurrentWeight(0);
                LOGGER.log(Level.INFO, String.format("Cleared weight, personCount and set new currentFloor for personId: %d", elevator.getElevatorId()));
            }
        }
    }

    /***
     * model.Elevator weight person count and floor are getting changed according to added person desiredFloor and weight.
     *
     * @param elevator is Elevator that has to load a given person
     * @param personToLoad is a Person that has to be loaded on the given Elevator
     */
    public static void loadPerson(Elevator elevator, Person personToLoad) {
        elevator.getLoadedPersons().add(personToLoad);
        elevator.setCurrentWeight(elevator.getCurrentWeight() + personToLoad.getWeight());
        if (personToLoad.getDesiredFloor() >= personToLoad.getStartingFloor()) {
            elevator.setElevatorUP(true);
        } else {
            elevator.setElevatorUP(false);
        }
        if (elevator.isElevatorUP() && (personToLoad.getDesiredFloor() > elevator.getDestinationFloor())) {
            elevator.setDestinationFloor(personToLoad.getDesiredFloor());
        }
        LOGGER.log(Level.INFO, String.format("Updated wight, personCount and elevator direction for elevatorId:%d because of loading personId:%d", elevator.getElevatorId(), personToLoad.getPersonId()));
    }
}
