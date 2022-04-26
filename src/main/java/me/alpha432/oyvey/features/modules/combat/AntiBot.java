package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiBot extends Module {

    public AntiBot(){super("AntiBot","",Category.COMBAT,true,false,false);}

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        try {
            for (EntityPlayer entityPlayer : mc.world.playerEntities) {
                if (entityPlayer.isInvisible() && entityPlayer != mc.player) {
                    mc.world.removeEntity(entityPlayer);
                }
            }
        } catch (Exception ex) {
            Command.sendMessage("LOL ERROR");
        }
    }

}
