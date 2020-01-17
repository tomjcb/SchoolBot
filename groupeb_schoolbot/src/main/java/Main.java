import Emojis.Emojis;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import Emojis.*;

import static Emojis.Emojis.*;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        MsgCommands cmd = new MsgCommands();
        String token = "NjY2OTk4NzA4MDY3NjMxMTE1.XiH_EA.d8S9I6UIzCRS-Z8gHHafS1Md9LQ";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.addEventListener(new MsgCommands());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(!event.getMessage().getAuthor().getId().equals("666998708067631115")){
            System.out.println("Message reçu de la part de "+ event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        }

        if(event.getMessage().getContentRaw().equals("Monsieur")){
            event.getMessage().addReaction(EMOJI_RIRE).queue();
            event.getMessage().addReaction(EMOJI_CORRECT).queue();
            event.getChannel().sendMessage("TOUT EST FICHIER").queue();
        }
    }


}
