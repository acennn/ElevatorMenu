import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String fileNameElevatorsPersonsDataInput = "src/main/resources/elevatorsPersonsDataInput.txt";
        String fileNameElevatorsPersonsDataReport = "src/main/resources/fileElevatorsPersonsDataReport.txt";
        ArrayList<String> elevatorsData = ReadWriteFile.readFile(fileNameElevatorsPersonsDataInput);
        ArrayList<String> personsData = ReadWriteFile.readFile(fileNameElevatorsPersonsDataInput);
        ArrayList<Elevator> elevators = Service.createElevators(elevatorsData);
        ArrayList<Person> persons = Service.createPersons(personsData);
        ArrayList<Person> loadedPersonsToRemove = new ArrayList<>();
        StringBuilder report = Service.startReport();

        while (persons.size() > 0) {
            int personsCount = persons.size();
            Service.loadAllPossiblePersonsForOneIterationAndReport(elevators, persons, loadedPersonsToRemove, report, personsCount);
            persons.removeAll(loadedPersonsToRemove);
            Service.checkIfNoElevatorAvailable(persons, personsCount);
            Service.clearUsedElevatorWeightPersonCountFloor(elevators);
        }
        ReadWriteFile.writeFile(fileNameElevatorsPersonsDataReport, report.toString());
    }
}
