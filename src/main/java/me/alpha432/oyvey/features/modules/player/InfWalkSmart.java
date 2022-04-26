package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class InfWalkSmart extends Module {



    public InfWalkSmart() {
        super("InfWalkSmart", "", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("#thisway 1000000");
        mc.player.sendChatMessage("#path");
    }

    @Override
    public void onDisable() {
        mc.player.sendChatMessage("#stop");
    }

}

