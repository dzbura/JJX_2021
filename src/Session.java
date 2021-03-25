import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Session {
    public Util.status sessionStat;
    public Map<String, List<CitizenWrapper>> sessionData;
    public String currentCity;
    public Scanner scnr = new Scanner(System.in);

    public Session(Util.status initialStat){
        this.sessionStat = initialStat;
        this.sessionData = new HashMap<String, List<CitizenWrapper>>();
    }

    public void dispatchNextAction() {
        switch (this.sessionStat) {
            case MENU:
                menuAction();
            case INPUT_CITY:
                inputCityAction();
            case INPUT_NAME:
                inputNameAction();
            case VIEW_ALL:
                viewAllAction();
            case EXIT:
                exitAction();
            case SWITCH_CITIES:
                    switchCitiesAction();
        }
    }

    public void menuAction() {
        String userInput = "";
        while (!Util.inputToStatusMap.containsKey(userInput)) {
            System.out.println("\n Please type: "
                    + "\n c if you want to enter a new city");
            if (!sessionData.isEmpty()) {
                System.out.println(" v if you want to view data entered so far"
                        + "\n q if you want to save&exit"
                        + "\n s if you want to switch cities");
            } else {
                System.out.println(" q if you want to exit");
            }
            if (currentCity != null) {
                System.out.print(" n if you want to add a new citizen to current city"
                        + "\nCurrent city is: " + currentCity + "\n");
            }
            userInput = scnr.nextLine();
        }
        this.sessionStat = Util.inputToStatusMap.get(userInput);
        dispatchNextAction();
    }

    public void inputCityAction() {
        System.out.println("Enter city name\n");
        String userInput = scnr.nextLine();
        if (sessionData.keySet().contains(userInput)) {
            System.out.println("City already exists. Switching to " + userInput);
        } else {
            this.sessionData.put(userInput, new ArrayList<CitizenWrapper>());
        }
        this.currentCity = userInput;
        this.sessionStat = Util.status.MENU;
        dispatchNextAction();
    }

    public void inputNameAction() {
        System.out.println("Enter citizen name");
        String userInput = scnr.nextLine();
        if (!userInput.isBlank()) {
            CitizenWrapper citizen = new CitizenWrapper(userInput);
            inputPeselAction(citizen);
        } else {
            dispatchNextAction();
        }
    }

    public void viewAllAction() {
        System.out.println(Util.getPrettySessionData(sessionData));
        this.sessionStat = Util.status.MENU;
        dispatchNextAction();
    }

    public void exitAction() {
        try {
            System.out.println("Enter name of file to save\n");
            String userInput = scnr.nextLine();
            FileWriter fileWriter = new FileWriter(userInput + ".txt", false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Util.getPrettySessionData(sessionData) + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "\nFile was not saved.");
        } finally {
            System.exit(-1);
        }
    }

    public void inputPeselAction(CitizenWrapper citizen) {
        System.out.println("Enter pesel");
        String userInput = scnr.nextLine();
        citizen.pesel = userInput;
        if (citizen.validatePesel()) {
            CitizenWrapper duplicateCitizen = getDuplicateCitizen(citizen);
            if (duplicateCitizen != null) {
                System.out.println("Citizen with the same pesel found. " + duplicateCitizen.name + " entry overwritten.");
                Collections.replaceAll(this.sessionData.get(currentCity), duplicateCitizen, citizen);
            } else {
                System.out.println("Citizen saved successfully");
                (this.sessionData.get(currentCity)).add(citizen);
            }
        } else {
            System.out.println("An invalid PESEL was provided. Citizen was not added.");
            this.sessionStat = Util.status.MENU;
        }
        this.sessionStat = Util.status.MENU;
        dispatchNextAction();
    }

    public void switchCitiesAction() {
        System.out.println("to which city do you want to switch?");
        System.out.println(sessionData.keySet());
        String userInput = scnr.nextLine();
        if (!userInput.isBlank()) {
            currentCity = userInput;
            this.sessionStat = Util.status.MENU;
        }
        dispatchNextAction();
    }

    public CitizenWrapper getDuplicateCitizen(CitizenWrapper citizen) {
        for (CitizenWrapper cw : this.sessionData.get(currentCity)) {
            if (cw.pesel.equals(citizen.pesel)) {
                return cw;
            }
        }
        return null;
    }
}
