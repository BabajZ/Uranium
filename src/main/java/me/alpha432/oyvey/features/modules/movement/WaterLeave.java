package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WaterLeave extends Module {



    public WaterLeave() {
        super("WaterLeave", "Change hands view", Category.PLAYER, true, false, false);
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent e) {
        int motion = 10;

        final BlockPos blockPos = new BlockPos(mc.player.posX, mc.player.posY - 0.1, mc.player.posZ);
        final Block block = mc.world.getBlockState(blockPos).getBlock();

        if (block instanceof BlockLiquid) {
            if (mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY + 0.01, mc.player.posZ)).getBlock() == Block.getBlockById(9) && mc.player.isInsideOfMaterial(Material.AIR)) {
                mc.player.motionY = 0.08;
            } if (mc.player.fallDistance > 0.0f && mc.player.motionY < 0.01) {
                mc.player.motionY = motion;
            }
        }
    }


}
