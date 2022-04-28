package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.event.events.JumpEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Client.MessageUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BuildHelper extends Module {

    private final Setting<Boolean> jumpToggle = register(new Setting<>("Off on jump", false));

    public BuildHelper(){super("BuidHalper","",Category.MISC,true,false,false);}

    @Override
    public void onTick () {
        BlockPos pos = new BlockPos(mc.player.posX, mc.player.posY - 1.0, mc.player.posZ);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), mc.world.getBlockState(pos).getBlock() == Blocks.AIR);
    }

    @SubscribeEvent
    public void onJump (JumpEvent event) {
        if(!jumpToggle.getValue()) return;
        MessageUtil.sendMessage("detected jump.. turnin");
        this.disable();
    }
}
