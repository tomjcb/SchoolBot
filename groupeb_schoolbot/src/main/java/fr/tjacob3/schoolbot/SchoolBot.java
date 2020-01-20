package fr.tjacob3.schoolbot;

import fr.tjacob3.schoolbot.command.CommandMap;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;

import java.util.Scanner;

import static fr.tjacob3.schoolbot.Emojis.Emojis.*;

public class SchoolBot implements Runnable {

    private final JDA jda;
    private final CommandMap commandMap = new CommandMap(this);


    private final Scanner scanner = new Scanner(System.in);

    private boolean running;

    public SchoolBot() throws LoginException {
        jda = (JDA) new JDABuilder(AccountType.BOT).setToken("NjY2OTk4NzA4MDY3NjMxMTE1.XiH_EA.d8S9I6UIzCRS-Z8gHHafS1Md9LQ").buildAsync();
        jda.addEventListener(new SchoolListener(commandMap));

        System.out.println("bot Online.");
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public JDA getJda() {
        return jda;
    }

    @Override
    public void run() {
        running = true;
        while(running){
            if(scanner.hasNextLine()) commandMap.commandConsole(scanner.nextLine());
        }

        scanner.close();
        System.out.println("SchoolBot mit au repos");
        jda.shutdown();
        System.exit(0);
    }

    public static void main(String[] args) throws LoginException {
        try{
            SchoolBot schoolBot = new SchoolBot();
            new Thread(schoolBot, "bot").start();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }


}
