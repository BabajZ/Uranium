package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class test extends Module {




    public test() {
        super("Sprint", "Fake lag.", Category.MOVEMENT, true, false, false);
    }



    @Override
    public void onTick() {
        mc.player.setSprinting(true);
       }


    @Override
    public void onDisable() {
    }

}

