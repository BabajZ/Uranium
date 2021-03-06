package me.alpha432.oyvey.mixin.mixins;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Render.class})
abstract class MixinRenderer<T extends Entity> {
    @Shadow
    protected boolean renderOutlines;
    @Shadow
    @Final
    protected RenderManager renderManager;

    MixinRenderer() {
    }

    @Shadow
    protected abstract boolean bindEntityTexture(T var1);

    @Shadow
    protected abstract int getTeamColor(T var1);
}