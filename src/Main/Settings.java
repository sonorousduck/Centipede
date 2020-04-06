package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Settings {
    public Dictionary createDictionary() {
        File file = new File("data/settings.txt");
        Dictionary<String, Integer> settings = new Hashtable<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] subdictionary = line.split(":");
                settings.put(subdictionary[0], Integer.parseInt(subdictionary[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Main.Settings file is missing");
        }

        return settings;



    }

}
