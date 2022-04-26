package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChinaHat extends Module {

    public ChinaHat() {
        super("ChinaHat", "Renders people burrowing", Module.Category.RENDER, true, false, false);}


    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d( 0f,  255f, 0f, 30f);
        GL11.glTranslatef(0.0f, ChinaHat.mc.player.height + 0.4f, 0.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        final Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(100012);
        cylinder.draw(0.0f, 0.8f, 0.4f, 30, 1);
        GlStateManager.resetColor();
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

}
