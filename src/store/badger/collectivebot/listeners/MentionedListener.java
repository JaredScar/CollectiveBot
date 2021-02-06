package store.badger.collectivebot.listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import store.badger.collectivebot.api.API;
import store.badger.collectivebot.main.Main;

public class MentionedListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        if (evt.getChannelType() == ChannelType.TEXT) {
            for (Member mentioned : evt.getMessage().getMentionedMembers()) {
                if (mentioned.getUser().getAsMention().equals(Main.getJDA().getSelfUser().getAsMention())) {
                    // They mentioned the bot
                    if (evt.getMember() != null) {
                        evt.getTextChannel().sendMessage(API.getInformationMessage(evt.getMember()).build()).submit();
                    }
                }
            }
        }
    }
}
