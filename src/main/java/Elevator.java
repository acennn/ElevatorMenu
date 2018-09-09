import java.util.ArrayList;
import java.util.Objects;

public class Elevator {

    private static int globalElevatorID = 0;
    private int elevatorId;
    private double weightCapacity;
    private int personCountCapacity;
    private double currentWeight;
    private ArrayList<Person> loadedPersons;
    private int currentFloor;
    private boolean isElevatorUP;
    private int destinationFloor;

    public Elevator(double weightCapacity, int personCountCapacity, int currentFloor) {
        globalElevatorID++;
        this.elevatorId = globalElevatorID;
        this.weightCapacity = weightCapacity;
        this.personCountCapacity = personCountCapacity;
        this.currentWeight = 0;
        this.loadedPersons = new ArrayList<>();
        this.currentFloor = currentFloor;
    }

    public static int getGlobalElevatorID() {
        return globalElevatorID;
    }

    public static void setGlobalElevatorID(int globalElevatorID) {
        Elevator.globalElevatorID = globalElevatorID;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public int getPersonCountCapacity() {
        return personCountCapacity;
    }

    public void setPersonCountCapacity(int personCountCapacity) {
        this.personCountCapacity = personCountCapacity;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public ArrayList<Person> getLoadedPersons() {
        return loadedPersons;
    }

    public void setLoadedPersons(ArrayList<Person> loadedPersons) {
        this.loadedPersons = loadedPersons;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isElevatorUP() {
        return isElevatorUP;
    }

    public void setElevatorUP(boolean elevatorUP) {
        isElevatorUP = elevatorUP;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "elevatorId=" + elevatorId +
                ", weightCapacity=" + weightCapacity +
                ", personCountCapacity=" + personCountCapacity +
                ", currentWeight=" + currentWeight +
                ", loadedPersons=" + loadedPersons +
                ", currentFloor=" + currentFloor +
                ", isElevatorUP=" + isElevatorUP +
                ", destinationFloor=" + destinationFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return elevatorId == elevator.elevatorId &&
                Double.compare(elevator.weightCapacity, weightCapacity) == 0 &&
                personCountCapacity == elevator.personCountCapacity &&
                Double.compare(elevator.currentWeight, currentWeight) == 0 &&
                currentFloor == elevator.currentFloor &&
                isElevatorUP == elevator.isElevatorUP &&
                destinationFloor == elevator.destinationFloor &&
                Objects.equals(loadedPersons, elevator.loadedPersons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elevatorId, weightCapacity, personCountCapacity, currentWeight, loadedPersons, currentFloor, isElevatorUP, destinationFloor);
    }
}
