package reader;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class mechReader {

    public static ArrayList<mech> read() throws Exception {

        String filePath = "src/main/resources/mechs.json";
        ArrayList<mech> mechs = new ArrayList<mech>();

        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            Root result = gson.fromJson(br, Root.class);
            if (result != null) {
                mechs = result.getMechs();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return mechs;
    }
}

