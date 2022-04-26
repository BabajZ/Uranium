package me.alpha432.oyvey.features.modules.Chat;


import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;

public class WeaknessLog extends Module {

public WeaknessLog () {super("WeaknessLog", "logs when weaknessed", Category.CHAT, true, false, false );}

    public void onUpdate() {
        if (mc.player == null || mc.world == null) return;
        if (WeaknessLog.mc.player.isPotionActive ( MobEffects.WEAKNESS ) ) {
            WeaknessLog.mc.player.connection.sendPacket ( new SPacketDisconnect ( new TextComponentString ( "weakness" ) ) );
            toggle();
        }
    }
}