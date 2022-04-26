package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Test extends Module {

    public Test() {
        super("NoGravity", "Makes log4j exploit disappear", Category.PLAYER, true, false, false);
    }



    public void onEnable(){
        mc.player.setNoGravity(true);
    }

    public void onDisable(){
        mc.player.setNoGravity(false);
    }
}