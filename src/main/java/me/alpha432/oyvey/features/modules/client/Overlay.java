package me.alpha432.oyvey.features.modules.client;


import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Block.Render.ColorUtil;
import me.alpha432.oyvey.util.Util;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class Overlay extends Module {
    public static final ResourceLocation mark = new ResourceLocation("textures/123.png");

    public Setting<Integer> imageX;

    public Setting<Integer> imageY;

    public Setting<Integer> imageWidth;

    public Setting<Integer> imageHeight;

    private int color;

    public Overlay() {
        super("Mark", "bebralogo", Module.Category.CLIENT, false, false, false);
        this.imageX = register(new Setting("x", Integer.valueOf(0), Integer.valueOf(-500), Integer.valueOf(500)));
        this.imageY = register(new Setting("y", Integer.valueOf(0), Integer.valueOf(-500), Integer.valueOf(500)));
        this.imageWidth = register(new Setting("width", Integer.valueOf(154), Integer.valueOf(0), Integer.valueOf(1600)));
        this.imageHeight = register(new Setting("height", Integer.valueOf(105), Integer.valueOf(0), Integer.valueOf(900)));
    }

    public void renderLogo() {
        int width = ((Integer)this.imageWidth.getValue()).intValue();
        int height = ((Integer)this.imageHeight.getValue()).intValue();
        int x = ((Integer)this.imageX.getValue()).intValue();
        int y = ((Integer)this.imageY.getValue()).intValue();
        Util.mc.renderEngine.bindTexture(mark);
        GlStateManager.color(255.0F, 255.0F, 255.0F);
        Gui.drawScaledCustomSizeModalRect(x - 2, y - 36, 7.0F, 7.0F, width - 7, height - 7, width, height, width, height);
    }

    public void onRender2D(Render2DEvent event) {
        if (!Feature.fullNullCheck()) {
            int width = this.renderer.scaledWidth;
            int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA(((Integer)(ClickGui.getInstance()).red.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).green.getValue()).intValue(), ((Integer)(ClickGui.getInstance()).blue.getValue()).intValue());
            if (((Boolean)this.enabled.getValue()).booleanValue()){
                renderLogo();}
        }
    }
}
