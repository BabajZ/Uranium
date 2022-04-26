package me.alpha432.oyvey.features.modules.render;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.text.TextComponentString;

public class StrengthDetect extends Module {

    private Set str = Collections.newSetFromMap(new WeakHashMap());
    public static final Minecraft mc = Minecraft.getMinecraft();

    public StrengthDetect() {
        super("StrengthDetect", "bruh", Category.CHAT, true, false, false);
    }

    public void onUpdate() {
        Iterator iterator = StrengthDetect.mc.world.playerEntities.iterator();

        while (iterator.hasNext()) {
            EntityPlayer player = (EntityPlayer) iterator.next();

            if (!player.equals(StrengthDetect.mc.player)) {
                if (player.isPotionActive(MobEffects.STRENGTH) && !this.str.contains(player)) {
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(player.getDisplayNameString() + "Tiene fuerza"));
                    this.str.add(player);
                }

                if (this.str.contains(player) && !player.isPotionActive(MobEffects.STRENGTH)) {
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(player.getDisplayNameString() + "Se ha quedado sin fuerzas"));
                    this.str.remove(player);
                }
            }
        }

    }
}