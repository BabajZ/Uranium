package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class SmartWalk extends Module {

    public Setting<String> state = this.register(new Setting<String>("State", "<Ur coordinates>", "Sets the state of the DiscordRPC."));


    public SmartWalk() {
        super("SmartWalk", "Fake lag.", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("#goto "+ state.getValue());
    }

    @Override
    public void onDisable() {
        mc.player.sendChatMessage("#stop");
    }

}
