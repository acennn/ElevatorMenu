import java.util.Objects;

public class Person {

    private static int globalPersonId = 0;
    private int personId;
    private double weight;
    private int startingFloor;
    private int desiredFloor;

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

    public static void setGlobalPersonId(int globalPersonId) {
        Person.globalPersonId = globalPersonId;
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
        return "Person{" +
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



