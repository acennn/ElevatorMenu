package dto;

import java.util.Objects;

public class ElevatorDTO {

    private double weightCapacity;
    private int personCountCapacity;
    private int currentFloor;

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

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public String toString() {
        return "dto.ElevatorDTO{" +
                "weightCapacity=" + weightCapacity +
                ", personCountCapacity=" + personCountCapacity +
                ", currentFloor=" + currentFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElevatorDTO that = (ElevatorDTO) o;
        return Double.compare(that.weightCapacity, weightCapacity) == 0 &&
                personCountCapacity == that.personCountCapacity &&
                currentFloor == that.currentFloor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weightCapacity, personCountCapacity, currentFloor);
    }
}
