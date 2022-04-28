package me.alpha432.oyvey.util.World;

import me.alpha432.oyvey.MinecraftInstance;
import me.alpha432.oyvey.features.command.Command;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.BlockPos;


public class WorldUtil implements MinecraftInstance {
    public static void disconnectFromWorld(final Command command) {
        WorldUtil.mc.world.sendQuittingDisconnectingPacket();
        WorldUtil.mc.loadWorld((WorldClient)null);
        WorldUtil.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
    }
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }

}