package iSBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class wikiScraperMech {

    private static ArrayList<mech> mechList = new ArrayList<>();;
    int index = 0;

    public ArrayList<mech> scrape() throws Exception {

        System.out.println("Getting Mechs");

        List<Entry> mechNames = getMechNames();

        return getMechInfo(mechNames);
    }

    public static ArrayList<mech> getCollection()
    {
        return mechList;
    }

    private String getUrl(Entry mech)
    {
        String url = "https://ironsaga.fandom.com" + mech.getDetailsUrl();
        return url;
    }

    private Document getMechInfoPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }

    private ArrayList<Elements> getTDs(Document doc)
    {
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

        return tds;
    }

    private mech parseMechTds(ArrayList<Elements> tds, Entry mech)
    {
        mech newMech = new mech();

        newMech.setName(mech.getName());
        newMech.setImgUrl(mech.getSpriteUrl());
        try{
            newMech.setRank(tds.get(2).get(1).text());
        }
        catch(Exception e)
        {}
        try{
            newMech.setHp(tds.get(4).get(1).text());
        }
        catch(Exception e)
        {}
        try{
            newMech.setWeight(tds.get(5).get(1).text());
        }
        catch(Exception e)
        {}
        try{
            newMech.setSize(tds.get(6).get(1).text());
        }
        catch(Exception e)
        {}
        try{
            newMech.setVelocity(tds.get(7).get(1).text());
        }
        catch(Exception e)
        {}
        try{
            newMech.setVelocity(tds.get(7).get(1).text());
        }
        catch(Exception e)
        {}

        return newMech;
    }

    private ArrayList<weapon> parseWeaponTds(ArrayList<Elements> tds)
    {
        index = 14;
        ArrayList<weapon> weaponList = new ArrayList<>();
        try {
            while (!tds.get(index).text().equals("Special Abilities")) {
                weapon newWeapon = new weapon();
                try {
                    newWeapon.setName(tds.get(index).get(0).text());
                    newWeapon.setType(tds.get(index).get(1).text());
                    newWeapon.setDamageString(tds.get(index).get(2).text());
                    newWeapon.setAttribute(tds.get(index).get(3).text());
                } catch (Exception e) {
                }

                weaponList.add(newWeapon);
                index++;
            }
        }
        catch(Exception e)
        {}
        return weaponList;
    }

    private ArrayList<uniqueAbility> parseUniqueAbilities(ArrayList<Elements> tds)
    {
        index = index + 2;

        ArrayList<uniqueAbility> uAList = new ArrayList<>();
        while(index < tds.size())
        {
            uniqueAbility newUA = new uniqueAbility();
            try {
                newUA.setName(tds.get(index).get(0).text());
                newUA.setCooldown(tds.get(index).get(1).text());
            }
            catch (Exception e) {}

            uAList.add(newUA);
            index++;
        }
        return uAList;
    }

    private ArrayList<mech> getMechInfo(List<Entry> mechNames) {

        for (Entry mech : mechNames) {
            try {
                String url = getUrl(mech);

                Document doc = getMechInfoPage(url);

                ArrayList<Elements> tds = getTDs(doc);

                mech newMech = parseMechTds(tds, mech);

                ArrayList<weapon> weaponList = parseWeaponTds(tds);
                ArrayList<uniqueAbility> uAList = parseUniqueAbilities(tds);

                newMech.setWeapons(weaponList);
                newMech.setUniqueAbility(uAList);

                mechList.add(newMech);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Done");
        return mechList;
    }

    private List<Entry> getMechNames() throws Exception {
        List<Entry> entries = new ArrayList<>();        // All entries are saved here

        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://ironsaga.fandom.com/wiki/Mech_List").get();

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
                try {
                    String href = td.get(1).children().first().attributes().get("href");
                    tableEntry.setSpriteUrl(href);
                }
                catch(Exception e)
                {
                    //bury no sprite error for now.
                }


                entries.add(tableEntry);                    // Finally add it to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public class Entry
    {
        private String name;
        private String spriteUrl;

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

        public String getSpriteUrl() {
            return spriteUrl;
        }
        public void setSpriteUrl(String url) {
            this.spriteUrl = url;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}


