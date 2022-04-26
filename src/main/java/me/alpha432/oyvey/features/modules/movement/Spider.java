package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public
class Spider extends Module {



    public
    Spider ( ) {
        super ( "+ Spider" , "Modifies sprinting" , Category.MOVEMENT , false , false , false );
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (mc.player.collidedHorizontally) {
            mc.player.motionY = 0.25;
        }
    }
}
