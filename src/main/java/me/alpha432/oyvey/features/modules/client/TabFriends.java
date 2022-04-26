package me.alpha432.oyvey.features.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class TabFriends extends Module {

    public TabFriends() {
        super("TabFriends", "TabModify", Category.CLIENT,true, false, false);
        INSTANCE = this;
    }
    public static TabFriends INSTANCE;

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String dname;
        String string = dname = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        return OyVey.friendManager.isFriend(dname) ? ChatFormatting.BOLD + ChatFormatting.LIGHT_PURPLE.toString() + dname : dname;
    }
}