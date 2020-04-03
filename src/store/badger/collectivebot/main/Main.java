package store.badger.collectivebot.main;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.bukkit.configuration.file.YamlConfiguration;
import store.badger.collectivebot.listeners.MentionedListener;
import store.badger.collectivebot.listeners.SuggestionListener;
import store.badger.collectivebot.listeners.WelcomeListener;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static JDA jda;
    public static void main(String[] args) throws LoginException {
        String token = getCon().getString("BotToken");
        JDA jdaa = new JDABuilder(token).addEventListener(new MentionedListener())
                .addEventListener(new WelcomeListener())
                .addEventListener(new SuggestionListener())
                .build();
        System.out.println("BOT IS RUNNING");
        jda = jdaa;
        Timer task = new Timer();
        task.scheduleAtFixedRate(new TimerTask() {
            int displayStatus = 0;
            @Override
            public void run() {
                if (displayStatus == 0) {
                    jda.getPresence().setPresence(Game.of(Game.GameType.DEFAULT,"https://docs.badger.store"), true);
                    displayStatus = 1;
                } else {
                    jda.getPresence().setPresence(Game.of(Game.GameType.DEFAULT, "https://github.com/thewolfbadger"), true);
                    displayStatus = 0;
                }
                //jda.getSelfUser().getManager().setName("CollectiveBot").submit();
            }
        }, 0L, 1000*60*10L); // updates every 10 minutes
    }
    public static YamlConfiguration getCon() {
        YamlConfiguration fig = YamlConfiguration.loadConfiguration(new File("config.yml"));
        return fig;
    }
    public static JDA getJDA() {
        return jda;
    }
}
