package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.event.events.PacketEventShit;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomTime extends Module {

    private static CustomTime INSTANCE;

    public Setting<Integer> time = this.register(new Setting("Time", 0, 0, 23000));



    public CustomTime() {
        super("CustomTime", "Allows you to change ingame time", Category.RENDER, true, false, false);
        this.setInstance();
    }



    private void setInstance() {
        CustomTime.INSTANCE = this;
    }
    @Override
    public void onUpdate() {

            mc.world.setWorldTime((long)this.time.getValue());
        }

    @SubscribeEvent
    public void onPacketReceive(PacketEventShit.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            event.setCanceled(true);
        }
    }

}
