package me.alpha432.oyvey.features.modules.client;


import io.netty.buffer.Unpooled;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoForge extends Module {
    public NoForge() {
        super("NoForge", "Prevents client from sending forge signature", Category.CLIENT,true,false,false);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (!mc.isIntegratedServerRunning()) {
            if (event.getPacket().getClass().getName().equals("net.minecraftforge.fml.common.network.internal.FMLProxyPacket")) {
                event.setCanceled(true);
            }
        }
    }
}
