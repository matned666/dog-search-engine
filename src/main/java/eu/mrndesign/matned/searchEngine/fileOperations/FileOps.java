package eu.mrndesign.matned.searchEngine.fileOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOps {

    private static String path = "src\\main\\java\\searchEngine\\fileOperations\\data\\sessions.txt";

    public static void writeStringToFile(String value) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(path);
        out.println(value);
        out.close();
    }

    public static String readStringFromFile() throws FileNotFoundException {
        StringBuilder temp = new StringBuilder();
        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            temp.append(sc.nextLine());
        }
        return temp.toString();
    }

}
