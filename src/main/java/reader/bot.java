package reader;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class bot extends ListenerAdapter
{
    static ArrayList<pilot> pilotList;
    static ArrayList<part> partList;


    public static void main(String[] args) throws LoginException, IOException {

        pilotList = pilotReader.read();
        partList = partReader.read();

        //TO DO replace with JDABuilder.create
        new JDABuilder(AccountType.BOT)
            .setToken("NzE5MzI1MzI4NTQzMTg2OTQ1.Xt2ytg.kudGQjcChJDyf8kAuX0UDSWy7yo")
            .addEventListeners(new bot())
            .setActivity(Activity.playing("Fraga wide chaos"))
            .build();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        System.out.println(event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay() + event.getTextChannel());
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
        }
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