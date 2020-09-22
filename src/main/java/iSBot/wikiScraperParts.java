package iSBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class wikiScraperParts {

    static ArrayList<part> partList = new ArrayList<>();


    public static ArrayList<part> scrape() throws Exception {
         return getPartInfo();
    }

    private static ArrayList<part> getPartInfo() {
        System.out.println("Getting parts");
        try {
            Document doc = Jsoup.connect("https://ironsaga.fandom.com/wiki/Parts_List").get();

            ArrayList<Element> tables = new ArrayList<Element>();

            for( Element element : doc.getElementsByTag("table"))
            {
                tables.add(element);
            }

            //Purples
            createParts("Core", "purple", tables.get(0).getElementsByTag("td"));
            createParts("Shell", "purple", tables.get(1).getElementsByTag("td"));
            createParts("Support", "purple", tables.get(2).getElementsByTag("td"));
            createParts("Armor", "purple", tables.get(3).getElementsByTag("td"));
            createParts("Coat", "purple", tables.get(4).getElementsByTag("td"));

            //Blues
            createParts("core", "blue", tables.get(5).getElementsByTag("td"));
            createParts("shell", "blue", tables.get(6).getElementsByTag("td"));
            createParts("support", "blue", tables.get(7).getElementsByTag("td"));
            createParts("armor", "blue", tables.get(8).getElementsByTag("td"));
            createParts("coat", "blue", tables.get(9).getElementsByTag("td"));

            //Greens
            createParts("core", "green", tables.get(10).getElementsByTag("td"));
            createParts("shell", "green", tables.get(11).getElementsByTag("td"));
            createParts("support", "green", tables.get(12).getElementsByTag("td"));
            createParts("armor", "green", tables.get(13).getElementsByTag("td"));
            createParts("coat", "green", tables.get(14).getElementsByTag("td"));

            //Whites
            createParts("core", "white", tables.get(15).getElementsByTag("td"));
            createParts("shell", "white", tables.get(16).getElementsByTag("td"));
            createParts("support", "white", tables.get(17).getElementsByTag("td"));
            createParts("armor", "white", tables.get(18).getElementsByTag("td"));
            createParts("coat", "white", tables.get(19).getElementsByTag("td"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");
        return partList;
    }

    private static void createParts(String partType, String quality, Elements parts)
    {
        for(int i = 0; i < parts.size(); i = i + 4)
        {
            part newPart = new part();
            newPart.setPartType(partType);
            newPart.setQuality(quality);
            newPart.setName(parts.get(i).text());
            newPart.setImgUrl(parts.get(i+1).children().first().attributes().get("href"));
            newPart.setPartEffect(parts.get(i+2).text());
            newPart.setObtained(parts.get(i+3).text());

            partList.add(newPart);

        }

    }

    public static Collection<part> getCollection() {

        return partList;
    }
}
