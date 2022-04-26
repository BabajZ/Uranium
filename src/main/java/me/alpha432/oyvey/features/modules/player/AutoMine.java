package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AutoMine extends Module {

    public Setting<String> state = this.register(new Setting<String>("State", "<block u want to mine>", "Sets the state of the DiscordRPC."));


    public AutoMine() {
        super("AutoMine", "Fake lag.", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("#mine "+ state.getValue());
    }

    @Override
    public void onDisable() {
        mc.player.sendChatMessage("#stop");
    }

}
