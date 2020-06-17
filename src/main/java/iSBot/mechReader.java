package iSBot;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class mechReader {

    public static ArrayList<mech> read() throws Exception {

        String filePath = "mechs.json";
        ArrayList<mech> mechs = new ArrayList<mech>();


        InputStream inputStream = mechReader.class.getResourceAsStream("/mechs.json");

        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            Root result = gson.fromJson(br, Root.class);
            if (result != null) {
                mechs = result.getMechs();
            }
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

