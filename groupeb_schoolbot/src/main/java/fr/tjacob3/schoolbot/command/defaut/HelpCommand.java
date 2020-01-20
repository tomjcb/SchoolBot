package fr.tjacob3.schoolbot.command.defaut;

import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import fr.tjacob3.schoolbot.command.CommandMap;
import fr.tjacob3.schoolbot.command.SimpleCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;

import java.awt.*;

public class HelpCommand {

    private final CommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Command(name="help",type= ExecutorType.USER,description = "affiche la liste des commandes.")
    private void help(User user, MessageChannel channel){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Liste des commandes");
        builder.setColor(Color.cyan);

        for(SimpleCommand command : commandMap.getCommands()){
            if(command.getExecutorType() == ExecutorType.CONSOLE) continue;

            builder.addField(command.getName(), command.getDescription(), false);
        }

        if(!user.hasPrivateChannel()) user.openPrivateChannel().complete();
        ((UserImpl)user).getPrivateChannel().sendMessage(builder.build()).queue();

        channel.sendMessage(user.getAsMention() + ", veuillez regarder vos Message privés.").queue();
    }
}
