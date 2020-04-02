package store.badger.collectivebot.listeners;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import store.badger.collectivebot.main.Main;

public class SuggestionListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        TextChannel chan = evt.getTextChannel();
        String chanID = chan.getId();
        YamlConfiguration config = Main.getCon();
    }
}
