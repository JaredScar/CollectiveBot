package store.badger.collectivebot.listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import store.badger.collectivebot.main.Main;

import java.awt.*;

public class WelcomeListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent evt) {
        Member mem = evt.getMember();
        YamlConfiguration config = Main.getCon();
        String tag = mem.getUser().getAsTag();
        String avatarURL = mem.getUser().getAvatarUrl();
        String memberCount = String.valueOf(evt.getGuild().getMembers().size());
        String title = config.getString("WelcomeMessage.Title");
        String titleLink = config.getString("WelcomeMessage.TitleLink");
        String desc = config.getString("WelcomeMessage.Description");
        String footer = config.getString("WelcomeMessage.Footer");
        String footerIMG = config.getString("WelcomeMessage.FooterIMG");
        String thumbnail = config.getString("WelcomeMessage.Thumbnail");
        String channelID = config.getString("WelcomeMessage.Channel");
        String color = config.getString("WelcomeMessage.Color");
        Color colorr = new Color(Integer.parseInt(color.split(" ")[0]), Integer.parseInt(color.split(" ")[1]),
                Integer.parseInt(color.split(" ")[2]));
        boolean isEnabled = config.getBoolean("Enables.WelcomeMessage");
        // Variables: {USER-TAG}, {MEMBER-COUNT}, {USER-AVATAR}
        title = title.replaceAll("\\{USER-TAG}", tag).replaceAll("\\{MEMBER-COUNT}", memberCount)
                .replaceAll("\\{USER-AVATAR}", avatarURL);
        desc = desc.replaceAll("\\{USER-TAG}", tag).replaceAll("\\{MEMBER-COUNT}", memberCount)
                .replaceAll("\\{USER-AVATAR}", avatarURL);
        footer = footer.replaceAll("\\{USER-TAG}", tag).replaceAll("\\{MEMBER-COUNT}", memberCount)
                .replaceAll("\\{USER-AVATAR}", avatarURL);
        footerIMG = footerIMG.replaceAll("\\{USER-TAG}", tag).replaceAll("\\{MEMBER-COUNT}", memberCount)
                .replaceAll("\\{USER-AVATAR}", avatarURL);
        thumbnail = thumbnail.replaceAll("\\{USER-TAG}", tag).replaceAll("\\{MEMBER-COUNT}", memberCount)
                .replaceAll("\\{USER-AVATAR}", avatarURL);
        if (isEnabled) {
            // It's enabled, send welcome message
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(desc);
            eb.setFooter(footer, footerIMG);
            eb.setThumbnail(thumbnail);
            eb.setTitle(title, titleLink);
            eb.setColor(colorr);
            evt.getGuild().getTextChannelById(channelID).sendMessage(eb.build()).submit();
        }
    }
}
