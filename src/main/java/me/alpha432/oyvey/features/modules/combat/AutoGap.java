package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;



import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

public class AutoGap extends Module {

    private final Setting< Integer > health = register ( new Setting( "Delay" , 16 , 1 , 20 ) );


    public AutoGap( ) {
        super ( "+ AutoGap" , "AutoGap" , Category.PLAYER , true , false , false );
    }

    public void onEnable() {

    }

    public void onDisbale() {


        if (wasEating) {
            wasEating = false;
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
        }
    }

    private boolean wasEating = false;

    public void onUpdate() {

        if(mc.gameSettings.keyBindSprint.isKeyDown()) mc.player.setSprinting(true);
        eatGap();


    }


    public void eatGap() {
        if(mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            if(mc.currentScreen == null) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                wasEating = true;
            }else {
                mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
            }
        }
    }
}