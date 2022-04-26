package me.alpha432.oyvey.features.modules.render;


import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Animations extends Module {

    private final Setting mode;
    private final Setting swing;
    private final Setting slow;

    public Animations() {
        super("Animations", "Change animations.", Module.Category.RENDER, true, false, false);
        this.mode = this.register(new Setting("OldAnimations", Animations.Mode.OneDotEight));
        this.swing = this.register(new Setting("Swing", Animations.Swing.Mainhand));
        this.slow = this.register(new Setting("Slow", Boolean.valueOf(false)));
    }

    public void onUpdate() {
        if (!nullCheck()) {
            if (this.swing.getValue() == Animations.Swing.Offhand) {
                Animations.mc.player.swingingHand = EnumHand.OFF_HAND;
            }

            if (this.mode.getValue() == Animations.Mode.OneDotEight && (double) Animations.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9D) {
                Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0F;
                Animations.mc.entityRenderer.itemRenderer.itemStackMainHand = Animations.mc.player.getHeldItemMainhand();
            }

        }
    }

    public void onEnable() {
        if (((Boolean) this.slow.getValue()).booleanValue()) {
            Animations.mc.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 255000));
        }

    }

    public void onDisable() {
        if (((Boolean) this.slow.getValue()).booleanValue()) {
            Animations.mc.player.removePotionEffect(MobEffects.MINING_FATIGUE);
        }

    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        Packet t = send.getPacket();

        if (t instanceof CPacketAnimation && this.swing.getValue() == Animations.Swing.Disable) {
            send.setCanceled(true);
        }

    }

    private static enum Swing {

        Mainhand, Offhand, Disable;
    }

    private static enum Mode {

        Normal, OneDotEight;
    }
}