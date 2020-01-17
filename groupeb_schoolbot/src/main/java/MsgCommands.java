import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.awt.*;
import java.sql.SQLOutput;

public class MsgCommands implements EventListener {
    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent)
            System.out.println("SchoolBot Online, and ready to operate");

        if(event instanceof MessageReactionAddEvent){
            System.out.println("reac ajout");
            if(((MessageReactionAddEvent) event).getReactionEmote().getName().equals("❌")){
                ((MessageReactionAddEvent) event).getChannel().sendMessage("Comment ca un refus ?").queue();
                ((MessageReactionAddEvent) event).getGuild().getController().addRolesToMember(((MessageReactionAddEvent) event).getMember(), event.getJDA().getRolesByName("roletest", true)).complete();
                TextChannel txtChan = ((MessageReactionAddEvent) event).getGuild().getTextChannelsByName("dev", true).get(0);
                txtChan.sendMessage("Role test added to " + ((MessageReactionAddEvent) event).getMember().getUser().getName()).queue();
            }
        }

        if(event instanceof MessageReceivedEvent){
            MessageReceivedEvent msg = (MessageReceivedEvent)event;
            String input = msg.getMessage().getContentRaw();
            boolean isFound = input.indexOf("!addDev") !=-1? true: false;

            if(isFound){
                msg.getMessage().delete().complete();
                String arr[] = input.split(" ", 4);
                if(arr.length == 4){
                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle(arr[1], null);
                    eb.setColor(Color.cyan);
                    eb.setDescription("Pour le " + arr[2].replace("_", " "));
                    eb.addField("Devoir", arr[3], false);

                    msg.getChannel().sendMessage(eb.build()).queue();
                }
                else{
                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle("Erreur sur la commande {!addDev}!", null);
                    eb.setColor(Color.red);
                    eb.setDescription("Arguments manquants");
                    eb.addField("Rappel: Usage de la commande", "!addDev Matière Date Description", false);

                    msg.getChannel().sendMessage(eb.build()).queue();
                }
                System.out.println(arr.length);
            }
            isFound = input.indexOf("!aled") !=-1? true: false;
            if(isFound) {
                msg.getMessage().delete().complete();
                String arr[] = input.split(" ", 2);
                EmbedBuilder eb = new EmbedBuilder();
                if(arr.length > 1){
                    switch(arr[1]){
                        case "addDev":
                            eb.setTitle("Usage de la commande addDev", null);
                            eb.setColor(Color.orange);
                            eb.setDescription("!addDev Matière Date Description");
                            break;

                        default:
                            eb.setTitle("Commande inconnue.", null);
                            eb.setColor(Color.red);
                            eb.setDescription("Essayez avec une **VRAIE** commande.");
                    }
                }
                else{
                    eb.setTitle("Erreur sur la commande {!aled}!", null);
                    eb.setColor(Color.red);
                    eb.setDescription("Argument manquant");
                    eb.addField("Rappel: Usage de la commande", "!aled NomDeLaCommande", false);
                }
                msg.getChannel().sendMessage(eb.build()).queue();
            }

        }
    }
}
