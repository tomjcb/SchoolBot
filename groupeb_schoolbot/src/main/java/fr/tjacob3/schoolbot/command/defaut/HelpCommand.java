package fr.tjacob3.schoolbot.command.defaut;

import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import fr.tjacob3.schoolbot.command.CommandMap;
import fr.tjacob3.schoolbot.command.SimpleCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;

import java.awt.*;

public class HelpCommand {

    private final CommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Command(name="help",type= ExecutorType.USER,description = "Affiche la liste des commandes ou l'explication d'une commande.", usage = "$help |OU| $help NomCommande")
    private void help(User user, MessageChannel channel, Message msg){
        EmbedBuilder builder = new EmbedBuilder();

        if(msg.getContentRaw().equals("$help")) {

            builder.setTitle("Liste des commandes");
            builder.setColor(Color.cyan);

            for (SimpleCommand command : commandMap.getCommands()) {
                if (command.getExecutorType() == ExecutorType.CONSOLE) continue;

                builder.addField(command.getName(), command.getDescription() + "\n __Usage:__ " + command.getUsage(), false);
            }

            if (!user.hasPrivateChannel()) user.openPrivateChannel().complete();
            ((UserImpl) user).getPrivateChannel().sendMessage(builder.build()).queue();

            channel.sendMessage(user.getAsMention() + ", veuillez regarder vos Message privés.").queue();
        }

        else{
            String help = msg.getContentRaw().replace("$help ", "");
            boolean found = false;
            for(SimpleCommand command : commandMap.getCommands()){
                if(command.getName().equals(help)){
                    found = true;
                    builder.setTitle("Commande `$" + command.getName() + "`" );
                    builder.setColor(Color.cyan);
                    builder.addField("Description", command.getDescription(), false);
                    builder.addField("Usage", command.getUsage(), false);
                    channel.sendMessage(builder.build()).queue();
                }
            }
            if(!found){
                builder.setTitle("Commande `$" + help + "` introuvable." );
                builder.setColor(Color.red);
                channel.sendMessage(builder.build()).queue();
            }
        }
    }
}
