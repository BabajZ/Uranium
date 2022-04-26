package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BHOP  extends Module {

    AxisAlignedBB box = null;

    public BHOP() {
        super("Bhop", "Spoofs The Haste Effect", Category.MOVEMENT, true, false, false);
    }

    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent e) {
        if (mc.player.onGround) {
            mc.player.jump();
        }
    }
}