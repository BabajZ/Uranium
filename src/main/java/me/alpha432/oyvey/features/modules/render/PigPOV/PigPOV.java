package me.alpha432.oyvey.features.modules.render.PigPOV;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.entity.passive.*;
import net.minecraft.client.renderer.entity.*;

public class PigPOV extends Module
{
    public PigPOV() {
        super("PigPov", "Allows you to change ingame time", Module.Category.RENDER, true, false, false);
    }

    @Override
    public void onEnable() {
        PigPOV.mc.player.eyeHeight = 0.6f;
        PigPOV.mc.getRenderManager().entityRenderMap.put(EntityPig.class, new NoRenderPig(PigPOV.mc.getRenderManager(), PigPOV.mc));
    }

    @Override
    public void onDisable() {
        PigPOV.mc.player.eyeHeight = PigPOV.mc.player.getDefaultEyeHeight();
        PigPOV.mc.getRenderManager().entityRenderMap.put(EntityPig.class, new RenderPig(PigPOV.mc.getRenderManager()));
    }
}