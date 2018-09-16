import dto.ElevatorDTO;
import dto.PersonDTO;
import model.Elevator;
import model.Person;
import readwrite.ReadFile;
import readwrite.WriteFile;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileNameElevatorsPersonsDataInput = "src/main/resources/elevatorsPersonsDataInput.txt";
        String fileNameElevatorsPersonsDataReport = "src/main/resources/fileElevatorsPersonsDataReport.txt";
        ArrayList<String> elevatorsPersonsReadData = ReadFile.readFile(fileNameElevatorsPersonsDataInput);
        ArrayList<ElevatorDTO> elevatorsDTOsFromParsedData = Service.parseElevatorsData(elevatorsPersonsReadData);
        ArrayList<Elevator> elevators = Service.createElevators(elevatorsDTOsFromParsedData);
        ArrayList<PersonDTO> personsDTOsFromParsedData = Service.parsePersonsData(elevatorsPersonsReadData);
        ArrayList<Person> persons = Service.createPersons(personsDTOsFromParsedData);
        ArrayList<Person> loadedPersonsToRemove = new ArrayList<>();
        StringBuilder report = Service.startReport();

        while (persons.size() > 0) {
            int personsCount = persons.size();
            Service.loadAllPossiblePersonsForOneIterationAndReport(elevators, persons, loadedPersonsToRemove, report, personsCount);
            persons.removeAll(loadedPersonsToRemove);
            Service.checkIfNoElevatorAvailable(persons, personsCount);
            Service.clearUsedElevatorWeightPersonCountFloor(elevators);
        }
        WriteFile.writeFile(fileNameElevatorsPersonsDataReport, report.toString());
    }
}
