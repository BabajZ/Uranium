package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class BetterFPS extends Module {
    private boolean focused;
    public BetterFPS(){super("BetterFPS","",Category.CLIENT,true,false,false);}
    public void onEnable(){
       this.focused = true;
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e){
        if(Minecraft.getMinecraft().world == null){
            return;
        }
        if(!Display.isActive() && this.focused){
            this.focused = false;
            Thread th = new Thread(new Runnable(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(500);
                        Minecraft.getMinecraft().gameSettings.limitFramerate = 3;
                        Display.setTitle("[Unfocused] " + Display.getTitle());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.run();

        } else if (Display.isActive() && Minecraft.getMinecraft().gameSettings.limitFramerate == 3){
            this.focused = true;
            Minecraft.getMinecraft().gameSettings.limitFramerate = 260;
            Display.setTitle(Display.getTitle().replace("[Unfocused] ", ""));
        }
    }
    public void onDisable(){
        Minecraft.getMinecraft().gameSettings.limitFramerate = 260;
    }
}
