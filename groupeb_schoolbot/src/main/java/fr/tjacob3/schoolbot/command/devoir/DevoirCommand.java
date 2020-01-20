package fr.tjacob3.schoolbot.command.devoir;

import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import fr.tjacob3.schoolbot.command.CommandMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;

import java.awt.*;
import java.util.List;

public class DevoirCommand {

    private final CommandMap commandMap;

    public DevoirCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Command(name = "addDev", type = ExecutorType.USER, description = "Ajoute un devoir à faire dans le channel correspondant à votre groupe", usage = "$addDev Matiere, Date, Description")
    private void addDev(User user, MessageChannel channel, Message msg, Guild guild){
        String sentence = msg.getContentRaw().replace("$addDev", "");
        System.out.println(sentence);
        String[] devoir = sentence.split(",", 3);

        if(devoir.length == 3){
            boolean found = false;
            Role groupeB1 = guild.getRoleById("666948526764326932");
            Role groupeB2 = guild.getRoleById("666948617445048320");
            TextChannel chanB1 = guild.getTextChannelById("666950173817044992");
            TextChannel chanB2 = guild.getTextChannelById("666950272303628290");
            List<Member> roles = guild.getMembersWithRoles(groupeB1);
            List<Member> roles2 = guild.getMembersWithRoles(groupeB2);


            System.out.println();

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(devoir[0], null);
            eb.setColor(Color.cyan);
            eb.setDescription("Pour le " + devoir[1]);
            eb.addField("Devoir", devoir[2], false);

            for(Member member : roles){
                if(member.getUser().getId().equals(user.getId())){
                    chanB1.sendMessage(eb.build()).queue();
                    System.out.println("Groupe B1");
                    found = true;
                }
            }
            for(Member member : roles2){
                if(member.getUser().getId().equals(user.getId())){
                    chanB2.sendMessage(eb.build()).queue();
                    System.out.println("Groupe B2");
                    found = true;
                }
            }

            if(!found){
                channel.sendMessage("Vous n'appartenez à aucun groupe. Commande annulée").queue();
                return;
            }

            //channel.sendMessage(eb.build()).queue();
        }
        else{
            channel.sendMessage("Mauvais usage de la commande `$addDev`. \n Usage: $addDev Matiere, Date, Description").queue();
        }
    }

}
