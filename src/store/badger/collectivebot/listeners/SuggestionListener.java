package store.badger.collectivebot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import store.badger.collectivebot.main.Main;

import java.awt.*;
import java.util.List;

public class SuggestionListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        TextChannel chan = evt.getTextChannel();
        String chanID = chan.getId();
        YamlConfiguration config = Main.getCon();
        List<String> suggestionChans = config.getStringList("SuggestionChannels");
        List<String> suggestionRoles = config.getStringList("SuggestionRoles");
        String suggestionAcceptEmoji = config.getString("SuggestionAcceptEmoji");
        String suggestionDenyEmoji = config.getString("SuggestionDenyEmoji");
        String suggestionDeniedEmoji = config.getString("SuggestionDeniedEmoji");
        String suggestionApprovedEmoji = config.getString("SuggestionApprovedEmoji");
        List<Emote> acceptEmoji = evt.getGuild().getEmotesByName(suggestionAcceptEmoji.replaceAll(":", ""), false);
        List<Emote> denyEmoji = evt.getGuild().getEmotesByName(suggestionDenyEmoji.replaceAll(":", ""), false);
        for (String sugChanID : suggestionChans) {
            if (chanID.equals(sugChanID)) {
                // This is a suggestion channel
                evt.getMessage().addReaction(acceptEmoji.get(0)).submit();
                evt.getMessage().addReaction(denyEmoji.get(0)).submit();
            }
        }
    }
    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent evt) {
        TextChannel chan = evt.getChannel();
        String chanID = chan.getId();
        Member mem = evt.getMember();
        YamlConfiguration config = Main.getCon();
        List<String> suggestionRoles = config.getStringList("SuggestionRoles");
        String suggestionDeniedEmoji = config.getString("SuggestionDeniedEmoji").replaceAll(":", "");
        String suggestionApprovedEmoji = config.getString("SuggestionApprovedEmoji").replaceAll(":", "");
        String suggestionApprovedChan = config.getString("SuggestionApprovedChannel");
        String suggestionDeniedChan = config.getString("SuggestionDeniedChannel");
        List<String> suggestionChans = config.getStringList("SuggestionChannels");
        for (String sugChanID : suggestionChans) {
            if (chanID.equals(sugChanID)) {
                boolean hasRole = false;
                for (String role : suggestionRoles) {
                    for (Role userRole : mem.getRoles()) {
                        if (userRole.getId().equals(role)) {
                            hasRole = true;
                        }
                    }
                }
                if (hasRole) {
                    // They have role, move the suggestion
                    if(evt.getReactionEmote().getEmote().getName().equals(suggestionApprovedEmoji)) {
                        // Approved
                        try {
                            String msg = evt.getChannel().retrieveMessageById(
                                    evt.getMessageId()).submit().get().getContentRaw();
                            EmbedBuilder suggest = getSuggestionRes(Color.GREEN, msg, evt.getChannel().retrieveMessageById(
                                    evt.getMessageId()).submit().get(), evt.getMember(), true);
                            evt.getGuild().getTextChannelById(suggestionApprovedChan).sendMessage(evt.getChannel().retrieveMessageById(
                                    evt.getMessageId()).submit().get().getAuthor().getAsMention()).submit();
                            evt.getGuild().getTextChannelById(suggestionApprovedChan).sendMessage(suggest.build()).submit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            evt.getChannel().retrieveMessageById(evt.getMessageId()).submit().get().delete().submit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else
                        if(evt.getReactionEmote().getEmote().getName().equals(suggestionDeniedEmoji)) {
                            // Denied
                            try {
                                String msg = evt.getChannel().retrieveMessageById(
                                        evt.getMessageId()).submit().get().getContentRaw();
                                EmbedBuilder suggest = getSuggestionRes(Color.RED, msg, evt.getChannel().retrieveMessageById(
                                        evt.getMessageId()).submit().get(), evt.getMember(), false);
                                evt.getGuild().getTextChannelById(suggestionDeniedChan).sendMessage(evt.getChannel().retrieveMessageById(
                                        evt.getMessageId()).submit().get().getAuthor().getAsMention()).submit();
                                evt.getGuild().getTextChannelById(suggestionDeniedChan).sendMessage(suggest.build()).submit();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                evt.getChannel().retrieveMessageById(evt.getMessageId()).submit().get().delete().submit();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return;
                }
            }
        }
    }

    public EmbedBuilder getSuggestionRes(Color color, String suggestion, Message msg, Member mem, boolean wasApproved) {
        EmbedBuilder embedb = new EmbedBuilder();
        YamlConfiguration config = Main.getCon();
        String suggestionAcceptEmoji = config.getString("SuggestionAcceptEmoji").replaceAll(":", "");
        String suggestionDenyEmoji = config.getString("SuggestionDenyEmoji").replaceAll(":", "");
        int acceptCount = -1;
        int denyCount = -1;
        String author = msg.getAuthor().getAsTag();
        for (MessageReaction react : msg.getReactions()) {
            if (react.getReactionEmote().getEmote().getName().equals(suggestionAcceptEmoji)) {
                acceptCount = react.getCount() - 1;
            }
            if (react.getReactionEmote().getEmote().getName().equals(suggestionDenyEmoji)) {
                denyCount = react.getCount() - 1;
            }
        }
        if (wasApproved) {
            embedb.setTitle("Approved Suggestion", "https://docs.badger.store/badger-software/collectivebot");
            embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
            embedb.addField("", "", false);
            embedb.addField("**__Suggestion__**", suggestion, false);
            embedb.addField("", "", false);
            embedb.addField("**__Statistics__**", "Suggested by " + author + "\n" + ""
                    + " " + acceptCount + " had accepted this suggestion\n"
                    + denyCount + " had denied this suggestion", false);
            embedb.setColor(color);
            embedb.setThumbnail("https://i.gyazo.com/ac24171c31f5dca015914908e190de29.png");
        } else {
            // Denied
            embedb.setTitle("Denied Suggestion", "https://docs.badger.store/badger-software/collectivebot");
            embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
            embedb.addField("", "", false);
            embedb.addField("**__Suggestion__**", suggestion, false);
            embedb.addField("", "", false);
            embedb.addField("**__Statistics__**",  "Suggested by " + author + "\n" + denyCount + " had denied this suggestion\n"
                    + acceptCount + " had accepted this suggestion", false);
            embedb.setColor(color);
            embedb.setThumbnail("https://i.gyazo.com/ac24171c31f5dca015914908e190de29.png");
        }
        return embedb;
    }
}
