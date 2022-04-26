package me.alpha432.oyvey.features.modules.render;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.events.UpdateEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraftforge.client.event.RenderSpecificHandEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class ViewModel extends Module {


    public Setting<Float> X = this.register(new Setting("X", 0f, -10f, 10f));
    public Setting<Float> Y = this.register(new Setting("Y", 0f, -10f, 10f));
    public Setting<Float> Z = this.register(new Setting("Z", 0f, -10f, 10f));
    public Setting<Float> X1 = this.register(new Setting("ScaleX", 7f, -50f, 50f));
    public Setting<Float> Y1 = this.register(new Setting("ScaleY", 7f, -50f, 50f));
    public Setting<Float> Z1 = this.register(new Setting("ScaleZ", 7f, -50f, 50f));
    public Setting<Float> G2 = this.register(new Setting("Angle", 0f, -100f, 100f));
    public Setting<Float> X2 = this.register(new Setting("RotateX", 0f, -100f, 100f));
    public Setting<Float> Y2 = this.register(new Setting("RotateY", 0f, -100f, 100f));
    public Setting<Float> Z2 = this.register(new Setting("RotateZ", 0f, -100f, 100f));

    public ViewModel() {
        super("ViewModel", "Change hands view", Category.RENDER, true, false, false);
    }

    @SubscribeEvent
    public void onRenderArms(final RenderSpecificHandEvent event) {
        glTranslatef(X.getValue(), Y.getValue(), Z.getValue());
        glScalef(X1.getValue(), Y1.getValue(), Z1.getValue());
        glRotatef(G2.getValue(), X2.getValue(), Y2.getValue(), Z2.getValue());
    }
}