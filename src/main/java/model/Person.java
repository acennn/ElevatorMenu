package model;

import java.util.Objects;

public class Person {

    private static int globalPersonId = 0;
    private int personId;
    private final double weight;
    private final int startingFloor;
    private final int desiredFloor;

    public Person(double weight, int startingFloor, int desiredFloor) {
        globalPersonId++;
        this.personId = globalPersonId;
        this.weight = weight;
        this.startingFloor = startingFloor;
        this.desiredFloor = desiredFloor;
    }

    public static int getGlobalPersonId() {
        return globalPersonId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public double getWeight() {
        return weight;
    }

    public int getStartingFloor() {
        return startingFloor;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    @Override
    public String toString() {
        return "model.Person{" +
                "personId=" + personId +
                ", weight=" + weight +
                ", startingFloor=" + startingFloor +
                ", desiredFloor=" + desiredFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                Double.compare(person.weight, weight) == 0 &&
                startingFloor == person.startingFloor &&
                desiredFloor == person.desiredFloor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, weight, startingFloor, desiredFloor);
    }
}



