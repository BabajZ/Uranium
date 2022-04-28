package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.World.EntityUtil;
import me.alpha432.oyvey.util.Client.MathUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class Aim extends Module {
    public Aim() {
        super("RageAim", "BowAim", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onUpdate() {
        EntityPlayer player = null;
        float tickDis = 100f;
        for (EntityPlayer p : mc.world.playerEntities) {
            if (p instanceof EntityPlayerSP || OyVey.friendManager.isFriend(p.getName())) continue;
            float dis = p.getDistance(mc.player);
            if (dis < tickDis) {
                tickDis = dis;
                player = p;
            }
        }
        if (player != null) {
            Vec3d pos = EntityUtil.getInterpolatedPos(player, mc.getRenderPartialTicks());
            float[] angels = MathUtil.calcAngle(EntityUtil.getInterpolatedPos(mc.player, mc.getRenderPartialTicks()), pos);
            //angels[1] -=  calcPitch(tickDis);
            mc.player.rotationYaw = angels[0];
            mc.player.rotationPitch = angels[1];

        }
    }}


