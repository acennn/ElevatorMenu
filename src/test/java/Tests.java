import dto.ElevatorDTO;
import dto.PersonDTO;
import model.Elevator;
import model.Person;
import org.junit.Assert;
import org.junit.Test;
import readwrite.ReadFile;
import service.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Tests {

    private ArrayList<Elevator> setup(String fileNameElevatorsPersons) throws NoSuchFieldException, IllegalAccessException, IOException {

        String path = "src/test/resources/";
        String fileElevatorsPersonsData = new StringBuilder().append(path).append(fileNameElevatorsPersons).toString();

        //the field countCreatedElevators is a private and static and there is no setter for it so we need to
        //use a reflection to set it to 0 before the next iteration so the counting to started again from 0 for the next file
        Elevator elevatorForReflectionSetCountCreatedElevators = new Elevator(0.0, 0, 0);
        Field countCreatedElevators = Elevator.class.getDeclaredField("countCreatedElevators");
        countCreatedElevators.setAccessible(true);
        countCreatedElevators.setInt(elevatorForReflectionSetCountCreatedElevators, 0);

        ArrayList<String> elevatorsPersonsReadData = ReadFile.readFile(fileElevatorsPersonsData);
        ArrayList<ElevatorDTO> elevatorsDTOsFromParsedData = Service.parseElevatorsData(elevatorsPersonsReadData);
        ArrayList<Elevator> elevators = Service.createElevators(elevatorsDTOsFromParsedData);
        ArrayList<PersonDTO> personsDTOsFromParsedData = Service.parsePersonsData(elevatorsPersonsReadData);
        ArrayList<Person> persons = Service.createPersons(personsDTOsFromParsedData);
        for (Person person : persons) {
            Elevator elevator = Service.findAppropriateElevatorForPerson(elevators, person);
            if (elevator != null) {
                Service.loadPerson(elevator, person);
            }
        }

        return elevators;
    }

    @Test
    public void overloadingWeight() throws NoSuchFieldException, IllegalAccessException, IOException {
        ArrayList<Elevator> elevators = setup("test1OverloadingWeight.txt");
        Assert.assertEquals(3, elevators.get(0).getLoadedPersons().size());
    }

    @Test
    public void overloadingCountPeople() throws NoSuchFieldException, IllegalAccessException, IOException {
        ArrayList<Elevator> elevators = setup("test2OverloadingCountPeople.txt");
        Assert.assertEquals(3, elevators.get(0).getLoadedPersons().size());
    }

    @Test
    public void loadingPersonOnSameElevation() throws NoSuchFieldException, IllegalAccessException, IOException {
        ArrayList<Elevator> elevators = setup("test3LoadingPersonOnSameElevation.txt");
        Assert.assertEquals(1, elevators.get(3).getLoadedPersons().size());
    }

    @Test
    public void personSameElevationAsElevatorFreeWeight() throws NoSuchFieldException, IllegalAccessException, IOException {
        ArrayList<Elevator> elevators = setup("test4PersonSameElevationAsElevatorFreeWeight.txt");
        Assert.assertEquals(2, elevators.get(2).getLoadedPersons().size());
        Assert.assertEquals(1, elevators.get(3).getLoadedPersons().size());
    }

    @Test
    public void personSameElevationAsElevatorNoFreeWeight() throws NoSuchFieldException, IllegalAccessException, IOException {
        ArrayList<Elevator> elevators = setup("test5personSameElevationAsElevatorNoFreeWeight.txt");
        Assert.assertEquals(4, elevators.get(0).getLoadedPersons().size());
        Assert.assertEquals(0, elevators.get(1).getLoadedPersons().size());
    }

    @Test(expected = NumberFormatException.class)
    public void errorWrongFormatOrDataForNumberElevators() throws NoSuchFieldException, IllegalAccessException, IOException {
        setup("test6ErrorWrongFormatOrDataForNumberElevators.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void errorWrongFormatOrDataForElevators() throws NoSuchFieldException, IllegalAccessException, IOException {
        setup("test7ErrorWrongFormatOrDataForElevators.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void errorWrongFormatOrDataForNumberPersons() throws NoSuchFieldException, IllegalAccessException, IOException {
        setup("test8ErrorWrongFormatOrDataForNumberPersons.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void errorWrongFormatOrDataForPersons() throws NoSuchFieldException, IllegalAccessException, IOException {
        setup("test9ErrorWrongFormatOrDataForPersons.txt");
    }

    @Test(expected = IOException.class)
    public void errorWrongFilenameToRead() throws NoSuchFieldException, IllegalAccessException, IOException {
        setup("wrongFilename");
    }
}
