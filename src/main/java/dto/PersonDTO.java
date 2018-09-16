package dto;

import java.util.Objects;

public class PersonDTO {
    private double weight;
    private int startingFloor;
    private int desiredFloor;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStartingFloor() {
        return startingFloor;
    }

    public void setStartingFloor(int startingFloor) {
        this.startingFloor = startingFloor;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    public void setDesiredFloor(int desiredFloor) {
        this.desiredFloor = desiredFloor;
    }

    @Override
    public String toString() {
        return "dto.PersonDTO{" +
                "weight=" + weight +
                ", startingFloor=" + startingFloor +
                ", desiredFloor=" + desiredFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Double.compare(personDTO.weight, weight) == 0 &&
                startingFloor == personDTO.startingFloor &&
                desiredFloor == personDTO.desiredFloor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, startingFloor, desiredFloor);
    }
}
