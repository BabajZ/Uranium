package me.alpha432.oyvey.features.modules.combat;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.Player.InventoryUtil;
import net.minecraft.item.ItemExpBottle;

public class EXPFast extends Module {
    public EXPFast() {
        super("EXPFast", "", Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (EXPFast.fullNullCheck()) {
            return;
        }
        if (InventoryUtil.holdingItem(ItemExpBottle.class)) {
            EXPFast.mc.rightClickDelayTimer = 0;
        }
    }
}