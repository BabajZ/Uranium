package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AutoFirework extends Module {

    public Setting<Boolean> autostart = this.register(new Setting<Boolean>("Autostart", true));
    public Setting<Boolean> fly = this.register(new Setting<Boolean>("Fireworks", true));

    public AutoFirework() {
        super("AutoFirework", "", Category.MOVEMENT, true, false, false);
    }

    public void onEnable() {
        //Autostart
        if (autostart.getValue()) {
            if (mc.player.onGround) {
                mc.player.jump();
                mc.timer.tickLength = 200f;
            }
        }
    }

    public void onDisable(){
        mc.timer.tickLength = 50f;
    }
}
