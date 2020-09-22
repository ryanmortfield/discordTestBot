package iSBot;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class bot extends ListenerAdapter
{
    static ArrayList<pilot> pilotList;
    static ArrayList<part> partList;
    static ArrayList<mech> mechList;
    static ArrayList<pet> petList;

    public static void main(String[] args) throws Exception {

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    wikiScrapeAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 24, TimeUnit.HOURS);


        JDABuilder.createDefault(args[0]).addEventListeners(new bot()).build();

    }

    private static Role getRole(MessageReceivedEvent event)
    {
        User user = event.getAuthor();
        Guild guild = event.getGuild();
        Member member = guild.getMember(user);
        List<Role> roles = member.getRoles();

        return roles.stream()
                .filter(role -> role.getName().equals("bot-contributor")) // filter by role name
                .findFirst() // take first result
                .orElse(null); // else return null

    }

    private static void wikiScrapeAll() throws Exception
    {
        updateMechs();
        updateParts();
        updatePets();
        updatePilots();
    }

    private static void updateMechs() throws Exception {
        wikiScraperMech mechScraper = new wikiScraperMech();
        mechList = mechScraper.scrape();
        System.out.println("Update");
    }

    private static void updatePilots() throws Exception {
        pilotList = wikiScraperPilots.scrape();
    }

    private static void updatePets() throws Exception {
        petList = wikiScraperPets.scrape();
    }

    private static void updateParts() throws Exception {
        partList = wikiScraperParts.scrape();
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot()) return; // DO NOT DEL,checks if msg is from the bot.
        String user_message = event.getMessage().getContentRaw(); // gets user msg.

        if(user_message.startsWith("!"))
        {
            String[] user_message_split = user_message.split(" ",2);
            String prefix = user_message_split[0]; // mech/pilot/part
            String query = user_message_split[1].toLowerCase(); // queryterm

            if (prefix.equals("!pilot"))
            {
                pilot pilot = fuzzyPilotSearch(query);
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(createPilotEmbed(pilot)).queue();

            }
            else if (prefix.equals("!part"))
            {
                part part = fuzzyPartSearch(query);
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(createPartEmbed(part)).queue();

            }
            else if (prefix.equals("!mech"))
            {
                mech mech = fuzzyMechSearch(query);
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(createMechEmbed(mech)).queue();

            }
            else if (prefix.equals("!pet"))
            {
                pet pet = fuzzyPetSearch(query);
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(createPetEmbed(pet)).queue();
            }
            else if(prefix.equals("!update"))
            {
                if(getRole(event)!= null)
                {
                    if (query.equals("mechs"))
                    {
                        try {
                            updateMechs();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (query.equals("pilots"))
                    {
                        try {
                            updatePilots();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (query.equals("parts"))
                    {
                        try {
                            updateParts();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (query.equals("pets"))
                    {
                        try {
                            updatePets();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(createUpdateEmbed()).queue();
                }
            }
        }
    }



    private mech fuzzyMechSearch(String term)
    {
        BoundExtractedResult<mech> match = FuzzySearch.extractOne(term,wikiScraperMech.getCollection(), x -> x.getName());
        mech matchMech = match.getReferent();

        return matchMech;
    }

    private part fuzzyPartSearch(String term)
    {
        BoundExtractedResult<part> match = FuzzySearch.extractOne(term,wikiScraperParts.getCollection(), x -> x.getName());
        part matchPart = match.getReferent();

        return matchPart;
    }

    private pilot fuzzyPilotSearch(String term)
    {
        BoundExtractedResult<pilot> match = FuzzySearch.extractOne(term,wikiScraperPilots.getCollection(), x -> x.getName());
        pilot matchPilot = match.getReferent();

        return matchPilot;
    }

    private pet fuzzyPetSearch(String term)
    {
        BoundExtractedResult<pet> match = FuzzySearch.extractOne(term,wikiScraperPets.getCollection(), x -> x.getName());
        pet matchPet = match.getReferent();

        return matchPet;
    }


    private StringBuilder buildMechBasicInfo(mech mech)
    {
        StringBuilder basicInfo  = new StringBuilder();
        basicInfo.append("Rank: ");
        basicInfo.append(mech.getRank());
        basicInfo.append(System.getProperty("line.separator"));
        basicInfo.append("Chassis Feature: ");
        basicInfo.append(mech.getChassisFeature());
        basicInfo.append(System.getProperty("line.separator"));
        basicInfo.append("HP: ");
        basicInfo.append(mech.getHp());
        basicInfo.append(System.getProperty("line.separator"));
        basicInfo.append("Size: ");
        basicInfo.append(mech.getSize());
        basicInfo.append(System.getProperty("line.separator"));
        basicInfo.append("Velocity: ");
        basicInfo.append(mech.getVelocity());
        basicInfo.append(System.getProperty("line.separator"));

        return basicInfo;
    }

    private StringBuilder buildMechWeapons(mech mech)
    {
        StringBuilder weapons  = new StringBuilder();

        for (weapon weapon : mech.getWeapons())
        {
            weapons.append(weapon.getName());
            weapons.append(" ");
            weapons.append(weapon.getType());
            weapons.append(" ");
            weapons.append(weapon.getAttribute());
//            weapons.append(weapon.getDamageString());
//            weapons.append(" ");
//            weapons.append(weapon.getElement());
//            weapons.append(" ");
//            weapons.append(weapon.getRank());
            weapons.append(System.getProperty("line.separator"));
        }

        return weapons;
    }

    private StringBuilder buildMechAbilities(mech mech)
    {
        StringBuilder uniqueAbilities = new StringBuilder();

        for (uniqueAbility uniqA : mech.getUniqueAbility()) {

            uniqueAbilities.append(uniqA.getName());
            uniqueAbilities.append(" ");
            uniqueAbilities.append(uniqA.getCooldown());
            uniqueAbilities.append(System.getProperty("line.separator"));

        }

        return uniqueAbilities;
    }

    private MessageEmbed createUpdateEmbed()
    {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("update queued");

        return builder.build();
    }

    private MessageEmbed createMechEmbed(mech mech) {

        StringBuilder basicInfo = buildMechBasicInfo(mech);
        StringBuilder weapons = buildMechWeapons(mech);
        StringBuilder uniqueAbilities = buildMechAbilities(mech);

        EmbedBuilder builder = new EmbedBuilder()
                .addField("Basic Info", basicInfo.toString(), false)
                .addField("Weapons", weapons.toString(), false)
                .addField("Unique Abilities", uniqueAbilities.toString(), false)
                .setColor(Color.ORANGE)
                .setTitle(mech.getName())
                .setThumbnail(mech.getImgUrl());

        return builder.build();

    }

    private MessageEmbed createPartEmbed(part part) {

        EmbedBuilder builder = new EmbedBuilder()
                .addField(part.partType, part.getPartEffect(), false)
                .addField("Obtained", part.getObtained(), false)
                .setColor(setColor(part.quality))
                .setTitle(part.getName())
                .setThumbnail(part.imgUrl);

        return builder.build();

    }

    private MessageEmbed createPetEmbed(pet pet) {
        EmbedBuilder builder = new EmbedBuilder()
                .addField("Effect",pet.getPetEffect(), false)
                .addField("Obtained", pet.getObtained(), false)
                .setColor(Color.BLACK)
                .setTitle(pet.getName())
                .setThumbnail(pet.getIcon());

        return builder.build();
    }

    private Color setColor(String quality)
    {
        switch (quality) {
            case "white":
                return Color.white;
            case "green":
                return Color.green;
            case "purple":
                return Color.magenta;
            default:
                return Color.gray;
        }

    }


    private MessageEmbed createPilotEmbed(pilot pilot)
    {
        StringBuilder skills  = new StringBuilder(pilot.skill1);
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill1Description);
        skills.append(System.getProperty("line.separator"));
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill2);
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill2Description);
        skills.append(System.getProperty("line.separator"));
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill3);
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill3Description);
        skills.append(System.getProperty("line.separator"));
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill4);
        skills.append(System.getProperty("line.separator"));
        skills.append(pilot.skill4Description);


        EmbedBuilder builder = new EmbedBuilder()
                .addField("Skills",skills.toString(), false)
                .setColor(Color.MAGENTA)
                .setTitle(pilot.pilotName)
                .setThumbnail(pilot.imgUrl)
                .setFooter(pilot.pilotDescription);

        return builder.build();
    }
}