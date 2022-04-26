package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Particles  extends Module {

    AxisAlignedBB box = null;


    public Setting<Boolean> pull8 = this.register(new Setting<Boolean>("Heart", true));
    public Setting<Boolean> pull = this.register(new Setting<Boolean>("Cloud", true));
    public Setting<Boolean> pull1 = this.register(new Setting<Boolean>("Flame", true));
    public Setting<Boolean> pull7 = this.register(new Setting<Boolean>("Smoke", true));
    public Setting<Boolean> pull2 = this.register(new Setting<Boolean>("RedStone", true));
    public Setting<Boolean> pull3 = this.register(new Setting<Boolean>("FireWork", true));
    public Setting<Boolean> pull4 = this.register(new Setting<Boolean>("Portal", true));
    public Setting<Boolean> pull5 = this.register(new Setting<Boolean>("Spit", true));
    public Setting<Boolean> b1 = this.register(new Setting<Boolean>("Slime", true));
    public Setting<Boolean> b2 = this.register(new Setting<Boolean>("DragonBreath", true));
    public Setting<Boolean> b3 = this.register(new Setting<Boolean>("EndRod", true));
    public Setting<Boolean> b4 = this.register(new Setting<Boolean>("Totem", true));
    public Setting<Boolean> b5 = this.register(new Setting<Boolean>("Bubble", true));
    public Setting<Boolean> b6 = this.register(new Setting<Boolean>("SnowBall", true));


    public Particles() {
        super("Particles", "Spoofs The Haste Effect", Category.RENDER, true, false, false);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (b6.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SNOWBALL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (b5.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (b4.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.TOTEM, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (b3.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.END_ROD, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (b2.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (b1.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SLIME, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull8.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.HEART, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.CLOUD, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull1.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.FLAME, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull7.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull2.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.1, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.3, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.4, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.REDSTONE, Particles.mc.player.posX, Particles.mc.player.posY + 0.5, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull3.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, Particles.mc.player.posX, Particles.mc.player.posY + 0.5, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull4.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
            Particles.mc.world.spawnParticle(EnumParticleTypes.PORTAL, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
        if (pull5.getValue()) {
            Particles.mc.world.spawnParticle(EnumParticleTypes.SPIT, Particles.mc.player.posX, Particles.mc.player.posY + 0.2, Particles.mc.player.posZ, 0.0, 0.0, 0.0, new int[0]);
        }
    }

}
