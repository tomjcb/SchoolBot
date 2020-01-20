package fr.tjacob3.schoolbot.command.defaut;

import fr.tjacob3.schoolbot.SchoolBot;
import fr.tjacob3.schoolbot.command.Command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommandDefault {

    private final SchoolBot schoolBot;

    private final String imgPath = "src\\main\\java\\fr\\tjacob3\\schoolbot\\img\\";

    public CommandDefault(SchoolBot schoolBot){
        this.schoolBot = schoolBot;
    }

    @Command(name="stop",type= ExecutorType.CONSOLE)
    private void stop(){
        schoolBot.setRunning(false);
    }

    @Command(name="changelog", type = ExecutorType.CONSOLE)
    private void changelog(){
        TextChannel changelog = schoolBot.getJda().getTextChannelById("668639225729777667");
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Changelog V1");
        builder.setColor(Color.cyan);

        String ajouts = "+ Commande `$help` \n" +
                "+ Commande `$monsieur` \n" +
                "+ Commande `$gngn` (le retour) \n" +
                "+ Commande `$F`";

        builder.addField("Ajouts :", ajouts, false);

        changelog.sendMessage(builder.build()).queue();
    }

    @Command(name="monsieur",type=ExecutorType.USER,description = "Fouzi Sama")
    private void monsieur(User user, MessageChannel channel){
        channel.sendMessage("Tout est fichier !!").complete();
    }

    @Command(name="F",type=ExecutorType.USER,description = "Press F to pay respect.")
    public void pressF(User user, MessageChannel channel){
        channel.sendFile(new File(imgPath + "pressf.gif")).queue();
    }

    @Command(name = "gngn",type = ExecutorType.USER, description = "Get gngn by the Bot (Usage $gngn YourTextHere)")
    public void gngn(User user, MessageChannel channel, Message msg){
        String baseSentance = msg.getContentRaw();
        String sentence = baseSentance.replace("$gngn", "");
        int lenSentance = sentence.length();
        String newSentance = "";
        int space = 0;

        for(int i =0; i < lenSentance; i++){
            if(sentence.charAt(i) == ' '){
                space++;
                if(space == 2) space = 0;
            }
            if(space == 0){
                if((i % 2) == 0){
                    newSentance = newSentance + String.valueOf(sentence.charAt(i)).toUpperCase();
                }
                else if((i % 2) == 1){
                    newSentance = newSentance + sentence.charAt(i);
                }
            }
            else if(space == 1){
                if((i % 2) == 1){
                    newSentance = newSentance + String.valueOf(sentence.charAt(i)).toUpperCase();
                }
                else if((i % 2) == 0){
                    newSentance = newSentance + sentence.charAt(i);
                }
            }
        }
        System.out.println(newSentance);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imgPath + "spongebob.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(25f));
        g.setColor(Color.black);
        g.drawString(newSentance, 35, 40);
        g.dispose();

        try {
            ImageIO.write(image, "png", new File(imgPath + "test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        channel.sendFile(new File(imgPath + "test.png")).queue();
    }

}
