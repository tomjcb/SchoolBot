package fr.tjacob3.schoolbot;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.awt.*;
import java.util.ArrayList;

public class DevoirCommands implements EventListener {
    public void onProut(){

    }
    String  currentID = "";
    String matiere;
    String date;
    String contenu;
    boolean isMatiere = false;
    boolean isDate = false;
    boolean isContenu = false;
    boolean isAddDev = false;
    ArrayList<Message> msgList = new ArrayList<>();
    @Override
    public void onEvent(Event event) {
        if(event instanceof MessageReceivedEvent) {
            MessageReceivedEvent msg = (MessageReceivedEvent) event;
            String input = msg.getMessage().getContentRaw();
            boolean isFound = input.indexOf("!addtest") != -1 ? true : false;

            if(isContenu && msg.getAuthor().getId().equals(currentID)){
                contenu = msg.getMessage().getContentRaw();
                isContenu = false;
                for(Message m : msgList){
                    m.delete().complete();
                }
                msg.getMessage().delete().complete();
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(matiere, null);
                eb.setColor(Color.cyan);
                eb.setDescription("Pour le " + date.replace("_", " "));
                eb.addField("Devoir", contenu, false);

                msg.getChannel().sendMessage(eb.build()).queue();
                /*if(!msg.getAuthor().hasPrivateChannel()) msg.getAuthor().openPrivateChannel().complete();
                User user = msg.getAuthor();
                ((UserImpl)user).getPrivateChannel().sendMessage(eb.build()).complete();*/

                System.out.println(matiere);
                System.out.println(date);
                System.out.println(contenu);
            }

            if(isDate && msg.getAuthor().getId().equals(currentID)){
                date = msg.getMessage().getContentRaw();
                isDate = false;
                msg.getChannel().sendMessage("Devoir à faire ?").queue();
                isContenu = true;
            }

            if(isMatiere && msg.getAuthor().getId().equals(currentID)){
                matiere = msg.getMessage().getContentRaw();
                isMatiere = false;
                msg.getChannel().sendMessage("Date ?").queue();
                isDate = true;
            }

            if(isFound){
                isAddDev = true;
                System.out.println("sexe");
                msg.getChannel().sendMessage("Matière du devoir à faire ?").queue();
                isMatiere = true;
                currentID = msg.getAuthor().getId();
            }

            if(isAddDev && msg.getAuthor().getId().equals(currentID) || msg.getAuthor().getId().equals("666998708067631115")){
                msgList.add(msg.getMessage());
            }



        }
        if(event instanceof PrivateMessageReceivedEvent){
            PrivateMessageReceivedEvent msg = (PrivateMessageReceivedEvent)event;
            if(!msg.getAuthor().hasPrivateChannel() && !msg.getAuthor().getId().equals("666998708067631115")) msg.getAuthor().openPrivateChannel().complete();
            User user = msg.getAuthor();
            ((UserImpl)user).getPrivateChannel().sendMessage("recu").complete();
        }
    }
}
