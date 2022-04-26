package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.event.events.PacketEventShit;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity extends Module {


    public Velocity() {
        super("Velocity", "cancels velocity packets so you dont get knockback", Category.PLAYER, true, false, false);
    }

    @SubscribeEvent
    public void onPacket(PacketEventShit.Receive event) {
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            if (((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId())
                event.setCanceled(true);
        }
        if (event.getPacket() instanceof SPacketExplosion)
            event.setCanceled(true);
    }
}
