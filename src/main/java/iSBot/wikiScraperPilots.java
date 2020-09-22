package iSBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class wikiScraperPilots {

    static ArrayList<pilot> pilots = new ArrayList<pilot>();

    public static ArrayList<pilot> scrape() throws Exception {

        System.out.println("Getting pilots");

        List<Entry> pilotNames = getPilotNames();

        return getPilotInfo(pilotNames);

    }

    private static ArrayList<pilot> getPilotInfo(List<Entry> pilotNames) throws Exception {


        int pilotDescIndex = 8;
        int pilotSkill1 = 15;
        int pilotSkill2 = 16;
        int pilotSkill3 = 17;
        int pilotSkill4 = 18;

        try {
            for (Entry pilot : pilotNames) {

                String url = "https://ironsaga.fandom.com" + pilot.getDetailsUrl();

                Document doc = Jsoup.connect(url).get();

                boolean firstSkipped = false;

                ArrayList<Elements> tds = new ArrayList<Elements>();

                for(Element element : doc.select("tr") ) {
                    // Skip the first 'tr' tag since it's the header
                    if (!firstSkipped) {
                        firstSkipped = true;
                        continue;
                    }

                    Elements td = element.select("td");
                    tds.add(td);
                }

                pilot newPilot = new pilot();
                newPilot.setPilotName(pilot.getName());
                newPilot.setImgUrl(pilot.getPortraitUrl());

                newPilot.setPilotDescription(tds.get(pilotDescIndex).text());
                newPilot.setSkill1(tds.get(pilotSkill1).get(0).text());
                newPilot.setSkill1Description(tds.get(pilotSkill1).get(2).text());

                newPilot.setSkill2(tds.get(pilotSkill2).get(0).text());
                newPilot.setSkill2Description(tds.get(pilotSkill2).get(2).text());

                newPilot.setSkill3(tds.get(pilotSkill3).get(0).text());
                newPilot.setSkill3Description(tds.get(pilotSkill3).get(2).text());

                newPilot.setSkill4(tds.get(pilotSkill4).get(0).text());
                newPilot.setSkill4Description(tds.get(pilotSkill4).get(2).text());

                pilots.add(newPilot);

                }

        } catch (IOException e) {
            e.printStackTrace();
        }

    return pilots;
    }


    public static List<Entry> getPilotNames() throws Exception
    {
        List<Entry> entries = new ArrayList<>();        // All entries are saved here

        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://ironsaga.fandom.com/wiki/Pilot_List").get();

            boolean firstSkipped = false;                   // Used to skip first 'tr' tag

            for( Element element : doc.select("tr") )       // Select all 'tr' tags from document
            {
                // Skip the first 'tr' tag since it's the header
                if( !firstSkipped )
                {
                    firstSkipped = true;
                    continue;
                }

                Entry tableEntry = new Entry();
                Elements td = element.select("td");         // Select all 'td' tags of the 'tr'

                // Fill your entry
                tableEntry.setName(td.get(0).text());
                tableEntry.setDetailsUrl(td.get(0).children().first().attributes().get("href"));
                String href = td.get(1).children().first().attributes().get("href");
                tableEntry.setPortraitUrl(href);

                entries.add(tableEntry);                    // Finally add it to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");
        return entries;
    }

    public static Collection<pilot> getCollection() {
        return pilots;
    }

    public static class Entry
    {
        private String name;
        private String portraitUrl;

        public String getDetailsUrl() {
            return detailsUrl;
        }

        public void setDetailsUrl(String detailsUrl) {
            this.detailsUrl = detailsUrl;
        }

        private String detailsUrl;

        public Entry(String name) {
            this.name = name;
        }

        public Entry() {
        }

        public String getPortraitUrl() {
            return portraitUrl;
        }
        public void setPortraitUrl(String url) {
            this.portraitUrl = url;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}



