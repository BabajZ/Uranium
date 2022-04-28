package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.Block.Render.RenderUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEntity  extends Module {



    public PlayerEntity () {
        super("PlayerEntity", "Spoofs The Haste Effect", Category.RENDER, true, false, false);
    }
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        switch (event.getType()) {
            case TEXT:
                RenderUtils.renderEntity(mc.player, 30, 40, 100);
                break;
            default:
                break;
        }
    }
}
