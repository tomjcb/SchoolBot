package fr.tjacob3.schoolbot.command.devoir;

import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import fr.tjacob3.schoolbot.command.CommandMap;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class DevoirCommand {

    private final CommandMap commandMap;

    public DevoirCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Command(name = "addDev", type = ExecutorType.USER, description = "Permet d'ajouter un devoir à faire")
    private void addDev(User user, MessageChannel channel, Message msg){
        String sentence = msg.getContentRaw().replace("$addDev", "");
        System.out.println(sentence);
        String[] devoir = sentence.split(",", 3);
        System.out.println(devoir[0] + "\n" + devoir[1] + "\n" +  devoir[2]);
    }

}
