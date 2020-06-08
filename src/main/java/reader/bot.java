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


    public static void main(String[] args) throws LoginException, IOException {

        pilotList = reader.read();

        new JDABuilder(AccountType.BOT)
            //token =
            .setToken("***TOKEN***")
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
            String[] user_message_split = user_message.split(" ");

            String prefix = user_message_split[0]; // mech/pilot/part
            String query = user_message_split[1]; // queryterm

            if (prefix.equals("!pilot"))
            {
                for(pilot pilot : pilotList){
                    if(pilot.getName() != null && pilot.getName().contains(query))
                    {
                        createPilotEmbed(pilot);
                        event.getMessage().delete().queue(); // deletes to queue
                        event.getChannel().sendMessage(createPilotEmbed(pilot)).queue();
                    }
                }
            }
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
                .setThumbnail("https://cdn.discordapp.com/attachments/650444799849005056/704542721364459677/Desktop_Screenshot_2020.04.27_-_22.51.09.01.png")
                .setFooter(pilot.pilotDescription);

        return builder.build();
    }
}