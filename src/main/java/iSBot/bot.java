package iSBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;

public class bot extends ListenerAdapter
{
    static ArrayList<pilot> pilotList;
    static ArrayList<part> partList;
    static ArrayList<mech> mechList;

    public static void main(String[] args) throws Exception {
        //load in data
        mechList = mechReader.read();
        //pilotList = pilotReader.read();
        pilotList = wikiScraperPilots.scrape();
        partList = partReader.read();

        //JDABuilder.createDefault(args[0]).addEventListeners(new bot()).build();

        JDABuilder.createDefault("NzE5MzI1MzI4NTQzMTg2OTQ1.Xt1x6g.S4qtJfVzYxs1TZDeldPpzpSMDTk").addEventListeners(new bot()).build();

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
                for(pilot pilot : pilotList){
                    if(pilot.getName() != null && pilot.getName().equals(query))
                    {
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(createPilotEmbed(pilot)).queue();
                    }
                }
            }
            else if (prefix.equals("!part"))
            {
                for(part part : partList){
                    if(part.getName() != null && part.getName().toLowerCase().equals(query))
                    {
                        createPartEmbed(part);
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(createPartEmbed(part)).queue();
                    }
                }
            }
            else if (prefix.equals("!mech"))
            {
                for(mech mech : mechList){
                    if(mech.getName() != null && mech.getName().toLowerCase().equals(query))
                    {
                        createMechEmbed(mech);
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(createMechEmbed(mech)).queue();
                    }
                }
            }
        }
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
            weapons.append(weapon.getDamageString());
            weapons.append(" ");
            weapons.append(weapon.getElement());
            weapons.append(" ");
            weapons.append(weapon.getRank());
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

        StringBuilder effects  = new StringBuilder("Effects: ");
        effects.append(part.partEffect);


        EmbedBuilder builder = new EmbedBuilder()
                .addField(part.partType, effects.toString(), false)
                .setColor(setColor(part.quality))
                .setTitle(part.getName())
                .setThumbnail(part.imgUrl);

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