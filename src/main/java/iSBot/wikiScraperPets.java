package iSBot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class wikiScraperPets {

    static ArrayList<pet> petList = new ArrayList<>();

    public static ArrayList<pet> scrape() throws Exception {
        return getpets();
    }

    private static ArrayList<pet> getpets() {

        try {
            Document doc = Jsoup.connect("https://ironsaga.fandom.com/wiki/Pet_List").get();

            ArrayList<Element> tables = new ArrayList<Element>();

            boolean firstSkipped = false;

            ArrayList<Elements> tds = new ArrayList<Elements>();

            for(Element element : doc.select("tr") ) {
                // Skip the first 'tr' tag since it's the header
                if (!firstSkipped) {
                    firstSkipped = true;
                    continue;
                }

                Elements td = element.select("td");
                pet newPet = new pet();

                newPet.setName(td.get(0).text());
                newPet.setIcon(td.get(1).children().first().attributes().get("href"));
                newPet.setPetEffect(td.get(2).text());
                newPet.setObtained(td.get(3).text());

                petList.add(newPet);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return petList;
    }

    public static Collection<pet> getCollection() {
        return petList;
    }
}
