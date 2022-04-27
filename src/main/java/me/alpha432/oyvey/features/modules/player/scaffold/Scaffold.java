package me.alpha432.oyvey.features.modules.player.scaffold;

import io.netty.channel.epoll.EpollTcpInfo;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.event.events.UpdateWalkingPlayerEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.*;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scaffold extends Module {

    //setting
    private Setting<Boolean> rainbow = register(new Setting<>("Rainbow", false));
    private Setting<Boolean> rotate = register(new Setting<>("Rotates", true));
    private Setting<Boolean> packet = register(new Setting<>("Packet Rotate", true));


    private List<ScaffoldBlock> blocksToRender = new ArrayList<>();
    private BlockPos pos;

    public Scaffold() {
        super("Scaffold", "Places Blocks underneath you.", Module.Category.PLAYER, true, false, false);
    }


    @Override
    public void onTick() {
        pos = new BlockPos(mc.player.posX, mc.player.posY - 1.0, mc.player.posZ);
        if (isAir(pos)) {
            BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, rotate.getValue(), packet.getValue(), mc.player.isSneaking());
            blocksToRender.add(new ScaffoldBlock(BlockUtil.posToVec3d(pos)));
        }
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        if (pos == null) return;
        for (BlockPos block : blocksToRender) {
            if (block == null) return;
            ScaffoldBlock scaffoldBlock = new ScaffoldBlock(BlockUtil.posToVec3d(block));

            RenderUtil.drawBoxESP(
                    block,
                    rainbow.getValue() ? ColorUtil.rainbow(1) : Color.CYAN,
                    1,
                    true,
                    true,
                    getAlphaByOffset(scaffoldBlock) - getAlphaByOffset(scaffoldBlock) * 2
                    );

        }
    }

    private boolean isAir(BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock() == Blocks.AIR;
    }

    private int getAlphaByOffset(BlockPos blockPos) {
        BlockPos playerPos = new BlockPos(mc.player);
        int distance =  Math.round((float)playerPos.getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
        if (distance >= 7) {
            return 0;
        }
        return distance  * 30;
    }

    @Override
    public void onDisable() {
        blocksToRender.clear();
    }
}