package iSBot;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class partReader {

    public static ArrayList<part> read() throws IOException {

        String fileName = "parts.csv";
        ArrayList<part> parts = new ArrayList<part>();

        InputStream inputStream = pilotReader.class.getResourceAsStream("/parts.csv");

        try (//FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(inputStream,
                     StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                if(nextLine.length == 5)
                {
                    part newPart = new part(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4]);
                    parts.add(newPart);
                }
            }

            //remove title row
            parts.remove(0);

        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return parts;
    }
}