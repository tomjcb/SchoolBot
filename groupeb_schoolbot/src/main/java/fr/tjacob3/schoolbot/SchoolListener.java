package fr.tjacob3.schoolbot;

import fr.tjacob3.schoolbot.command.CommandMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SchoolListener implements EventListener {

    private final CommandMap commandMap;

    public SchoolListener(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void onEvent(Event event){
        if(event instanceof MessageReceivedEvent) onMessage((MessageReceivedEvent)event);
        else if(event instanceof  ReadyEvent) onReady((ReadyEvent)event);
    }

    private void onMessage(MessageReceivedEvent event){
        if(event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        String message = event.getMessage().getContentRaw();
        if(message.startsWith(commandMap.getTag())){
            message = message.replace(commandMap.getTag(), "");
            if(commandMap.commandUser(event.getAuthor(), message, event.getMessage())){
                if(event.getTextChannel() != null && event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)){
                    event.getMessage().delete().queue();
                }
            }
        }

    }

    private void onReady(ReadyEvent event){
        User owner = event.getJDA().getUserById("246734124121849857");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(!owner.hasPrivateChannel()) owner.openPrivateChannel().complete();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("[" + dtf.format(now) + "] ", null);
        eb.setColor(Color.cyan);
        eb.setDescription("Schoolbot correctement démarré");
        ((UserImpl)owner).getPrivateChannel().sendMessage(eb.build()).queue();
    }
}
