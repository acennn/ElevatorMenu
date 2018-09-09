import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {

    private static final Logger LOGGER = Logger.getLogger(Service.class.getName());

    /***
     * Create elevators parsing the loaded data.
     * @param elevatorsDataList
     * @return
     */
    public static ArrayList<Elevator> createElevators(ArrayList<String> elevatorsDataList) {
        int numberLinesElevators = Integer.parseInt(elevatorsDataList.get(0));
        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 1; i <= numberLinesElevators; i++) {
            double weightCapacity = 0;
            int personCountCapacity = 0;
            int currentFloor = 0;
            try {
                String[] elevatorData = elevatorsDataList.get(i).split(" ");
                weightCapacity = Double.parseDouble(elevatorData[0]);
                personCountCapacity = Integer.parseInt(elevatorData[1]);
                currentFloor = Integer.parseInt(elevatorData[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Wrong elevator data or format causing parsing error", e);
            }
            Elevator currentElevator = new Elevator(weightCapacity, personCountCapacity, currentFloor);
            LOGGER.log(Level.INFO, "Successfully created elevators from input data");
            elevators.add(currentElevator);
        }
        return elevators;
    }

    /***
     * Create persons parsing the loaded data.
     * @param personsDataList
     * @return
     */
    public static ArrayList<Person> createPersons(ArrayList<String> personsDataList) {
        int numberLinesElevators = Integer.parseInt(personsDataList.get(0));
        int numberLinesPersons = Integer.parseInt(personsDataList.get(numberLinesElevators + 1));
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = numberLinesElevators + 2; i <= numberLinesPersons + numberLinesElevators + 1; i++) {
            double weight = 0;
            int startingFloor = 0;
            int desiredFloor = 0;
            try {
                String[] personData = personsDataList.get(i).split(" ");
                weight = Double.parseDouble(personData[0]);
                startingFloor = Integer.parseInt(personData[1]);
                desiredFloor = Integer.parseInt(personData[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Wrong person data or format causing parsing error", e);
            }
            Person currentPerson = new Person(weight, startingFloor, desiredFloor);
            LOGGER.log(Level.INFO, "Successfully created persons from input data");
            persons.add(currentPerson);
        }
        return persons;
    }

    /***
     * Find the best elevator for a person for the current iteration.
     * @param elevators
     * @param person
     * @return
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
                LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
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
            LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
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
                        LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                    if (elevator.isElevatorUP() && personInElevator.getDesiredFloor() < person.getStartingFloor()) {
                        LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                    if (!elevator.isElevatorUP() && personInElevator.getDesiredFloor() > person.getStartingFloor()) {
                        LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
                        return elevator;
                    }
                }
            }
        }
        if (sameDirectionFreeSpaceWeightElevators.size() > 0) {
            Elevator elevator = sameDirectionFreeSpaceWeightElevators.getFirst();
            LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
            return elevator;
        }
        if (emptyElevators.size() > 0) {
            Elevator elevator = emptyElevators.getFirst();
            LOGGER.log(Level.INFO, String.format("Elevator found elevatorId:%d is appropriate for personId:%d", elevator.getElevatorId(), person.getPersonId()));
            return elevator;
        }
        LOGGER.log(Level.INFO, String.format("Not found elevator for the person in current iteration personId:%d", person.getPersonId()));
        return null;
    }

    /***
     * Check if no persons have been loaded on the current iteration.
     * @param persons
     * @param personsCount
     */
    public static void checkIfNoElevatorAvailable(ArrayList<Person> persons, int personsCount) {
        if (persons.size() == personsCount) {
            throw new RuntimeException("No elevator available take the stairs :) ");
        }
    }

    /***
     * Creating the string for report and adding some comment data at the beginning.
     * @return
     */
    public static StringBuilder startReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n");
        report.append("Created report on: ");
        report.append(String.valueOf(new Timestamp(System.currentTimeMillis())));
        report.append("\n");
        report.append("\n");
        LOGGER.log(Level.INFO, String.format("Started creating the report and added first info rows"));
        return report;
    }

    /***
     * Loading all possible persons for one iteration and adding log to the report
     * @param elevators
     * @param persons
     * @param loadedPersonsToRemove
     * @param report
     * @param personsCount
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
     * @param persons
     * @param report
     * @param counter
     * @param elevator
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
     * @param elevators
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
     * Elevator weight person count and floor are getting changed according to added person desiredFloor and weight.
     * @param personToLoad
     *
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
