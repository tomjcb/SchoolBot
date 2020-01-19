package fr.tjacob3.schoolbot.command.defaut;

import fr.tjacob3.schoolbot.SchoolBot;
import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandDefault {

    private final SchoolBot schoolBot;

    public CommandDefault(SchoolBot schoolBot){
        this.schoolBot = schoolBot;
    }

    @Command(name="stop",type= ExecutorType.CONSOLE)
    private void stop(){
        schoolBot.setRunning(false);
    }

    @Command(name="info",type=ExecutorType.ALL)
    private void info(User user, TextChannel channel){
        channel.sendMessage(user.getAsMention() + " dans le channel " + channel.getName()).complete();
    }

}
