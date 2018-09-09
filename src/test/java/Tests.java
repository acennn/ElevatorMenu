
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Tests {

    private ArrayList<Elevator> setup(String fileNameElevatorsPersons) {
        String path = "src/test/resources/";
        String fileElevatorsPersonsData = new StringBuilder().append(path).append(fileNameElevatorsPersons).toString();
        ArrayList<String> elevatorsData = ReadWriteFile.readFile(fileElevatorsPersonsData);
        ArrayList<String> personsData = ReadWriteFile.readFile(fileElevatorsPersonsData);
        ArrayList<Elevator> elevators = Service.createElevators(elevatorsData);
        ArrayList<Person> persons = Service.createPersons(personsData);
        for (int i = 0; i < persons.size(); i++) {
            Elevator elevator = Service.findAppropriateElevatorForPerson(elevators, persons.get(i));
            if (elevator != null) {
                Service.loadPerson(elevator, persons.get(i));
            }
        }
        return elevators;

    }

    @Test
    public void overloadingWeight() {
        ArrayList<Elevator> elevators = setup("test1OverloadingWeight.txt");
        Assert.assertEquals(3, elevators.get(0).getLoadedPersons().size());
    }

    @Test
    public void overloadingCountPeople() {
        ArrayList<Elevator> elevators = setup("test2OverloadingCountPeople.txt");
        Assert.assertEquals(3, elevators.get(0).getLoadedPersons().size());
    }

    @Test
    public void loadingPersonOnSameElevation() {
        ArrayList<Elevator> elevators = setup("test3LoadingPersonOnSameElevation.txt");
        Assert.assertEquals(1, elevators.get(3).getLoadedPersons().size());
    }

    @Test
    public void personSameElevationAsElevatorFreeWeight() {
        ArrayList<Elevator> elevators = setup("test4PersonSameElevationAsElevatorFreeWeight.txt");
        Assert.assertEquals(2, elevators.get(2).getLoadedPersons().size());
        Assert.assertEquals(1, elevators.get(3).getLoadedPersons().size());
    }

    @Test
    public void personSameElevationAsElevatorNoFreeWeight() {
        ArrayList<Elevator> elevators = setup("test5personSameElevationAsElevatorNoFreeWeight.txt");
        Assert.assertEquals(4, elevators.get(0).getLoadedPersons().size());
        Assert.assertEquals(0, elevators.get(1).getLoadedPersons().size());
    }
}
