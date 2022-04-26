package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AutoTunnel extends Module {


        public Setting<String> state = this.register(new Setting<String>("State", "<HIGHT>", "Sets the state of the DiscordRPC."));
    public Setting<String> state1 = this.register(new Setting<String>("State1", "<WIGHT>", "Sets the state of the DiscordRPC."));
    public Setting<String> state2 = this.register(new Setting<String>("State2", "<DEEP>", "Sets the state of the DiscordRPC."));


    public AutoTunnel() {
            super("AutoTunnel", "Fake lag.", Module.Category.PLAYER, true, false, false);
        }

        @Override
        public void onEnable() {
            mc.player.sendChatMessage("#tunnel "+ state.getValue() + " " + state1.getValue() + " " + state2.getValue());
        }

        @Override
        public void onDisable() {
            mc.player.sendChatMessage("#stop");
        }

    }


