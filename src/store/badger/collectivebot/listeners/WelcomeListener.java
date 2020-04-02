package store.badger.collectivebot.listeners;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import store.badger.collectivebot.main.Main;

public class WelcomeListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent evt) {
        Member mem = evt.getMember();
        YamlConfiguration config = Main.getCon();
        String tag = mem.getUser().getAsTag();
        String avatarURL = mem.getUser().getAvatarUrl();
        String memberCOunt = String.valueOf(evt.getGuild().getMembers().size());
        String title = config.getString("WelcomeMessage.Title");
        String titleLink = config.getString("WelcomeMessage.TitleLink");
        String desc = config.getString("WelcomeMessage.Description");
        String footer = config.getString("WelcomeMessage.Footer");
        String footerIMG = config.getString("WelcomeMessage.FooterIMG");
        String thumbnail = config.getString("WelcomeMessage.Thumbnail");
        boolean isEnabled = config.getBoolean("Enables.WelcomeMessage");
        if (isEnabled) {
            // It's enabled, send welcome message
        }
    }
}
