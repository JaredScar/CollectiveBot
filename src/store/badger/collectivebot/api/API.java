package store.badger.collectivebot.api;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;

public class API {
    public static boolean hasRoleAbove(Member mem, Role role) {
        for(Role r : mem.getRoles()) {
            if(r.getPositionRaw() > role.getPositionRaw()) {
                return true;
            }
        }
        return false;
    }
    public static EmbedBuilder getSuccessEmbed(String msg, Member mem) {
        EmbedBuilder embedb = new EmbedBuilder();
        embedb.setTitle("Success", "https://docs.badger.store/badger-software/collectivebot");
        embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
        embedb.setDescription(msg);
        embedb.setColor(Color.GREEN);
        embedb.setThumbnail("https://i.gyazo.com/ac24171c31f5dca015914908e190de29.png");
        return embedb;
    }
    public static EmbedBuilder getSyntaxEmbed(String msg, Member mem) {
        EmbedBuilder embedb = new EmbedBuilder();
        embedb.setTitle("Invalid Syntax", "https://docs.badger.store/badger-software/collectivebot");
        embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
        embedb.setDescription(msg);
        embedb.setColor(Color.RED);
        embedb.setThumbnail("https://i.gyazo.com/ac24171c31f5dca015914908e190de29.png");
        return embedb;
    }
    public static EmbedBuilder getFailureEmbed(String msg, Member mem) {
        EmbedBuilder embedb = new EmbedBuilder();
        embedb.setTitle("Failure", "https://docs.badger.store/badger-software/collectivebot");
        embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
        embedb.setDescription(msg);
        embedb.setColor(Color.RED);
        embedb.setThumbnail("https://i.gyazo.com/ac24171c31f5dca015914908e190de29.png");
        return embedb;
    }
    public static EmbedBuilder getInformationMessage(Member mem) {
        EmbedBuilder embedb = new EmbedBuilder();
        embedb.setTitle("CollectiveBot by Badger", "https://github.com/TheWolfBadger/collectivebot");
        embedb.setFooter(mem.getUser().getAsTag(), mem.getUser().getAvatarUrl());
        embedb.setDescription("CollectiveBot was made by Badger (https://github.com/TheWolfBadger)\n\n"
                + "You can find more information on CollectiveBot over at https://docs.badger.store/badger-software/collectivebot");
        embedb.setColor(Color.MAGENTA);
        embedb.setThumbnail("https://i.gyazo.com/981a86c82860fc5ae5859bcd1bb0df09.gif");
        return embedb;
    }
}
