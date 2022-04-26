package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.GameType;

public class HightJump  extends Module {

    public Setting<Integer> X = this.register(new Setting("X", 0, 0, 10));

    public HightJump () {
        super("HightJump", "Spoofs The Haste Effect", Category.EXPLOIT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.motionY = X.getValue();
    }
}