package me.alpha432.oyvey.features.modules.Chat;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Client.TextUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.SoundEvents;

import java.util.HashSet;
import java.util.Set;

/*
Module by _CatsAreGood_
 */

public class FoodFinder
        extends Module {
    private final Set<Entity> cow = new HashSet<Entity>();
    private final Set<Entity> pig = new HashSet<Entity>();
    private final Set<Entity> chicken = new HashSet<Entity>();
    public Setting<Boolean> Chat = this.register(new Setting<Boolean>("Chat", true));
    public Setting<Boolean> Sound = this.register(new Setting<Boolean>("Sound", true));

    public FoodFinder() {
        super("FoodFinder", "", Category.CHAT, true, false, false);
    }

    @Override
    public void onEnable() {
        this.cow.clear();
        this.pig.clear();
        this.chicken.clear();
    }

    @Override
    public void onUpdate() {
        for (Entity entity : FoodFinder.mc.world.getLoadedEntityList()) {
            if (!(entity instanceof EntityCow) || this.cow.contains(entity)) continue;
            if (this.Chat.getValue()) {
                Command.sendMessage(TextUtil.YELLOW + "Uranium found food: " + entity.getPosition().getX() + "x, " + entity.getPosition().getY() + "y, " + entity.getPosition().getZ() + "z.");
            }else
                this.cow.add ( entity );
            if ( ! this.Sound.getValue ( ) ) continue;
            FoodFinder.mc.player.playSound ( SoundEvents.ENTITY_IRONGOLEM_ATTACK , 1.0f , 1.0f );

            if (!(entity instanceof EntityPig) || this.pig.contains(entity)) continue;
            if (this.Chat.getValue()) {
                Command.sendMessage(TextUtil.YELLOW + "Uranium found food: " + entity.getPosition().getX() + "x, " + entity.getPosition().getY() + "y, " + entity.getPosition().getZ() + "z.");
            }else
                this.pig.add ( entity );
            if ( ! this.Sound.getValue ( ) ) continue;
            FoodFinder.mc.player.playSound ( SoundEvents.ENTITY_IRONGOLEM_ATTACK , 1.0f , 1.0f );

            if (!(entity instanceof EntityChicken) || this.chicken.contains(entity)) continue;
            if (this.Chat.getValue()) {
                Command.sendMessage(TextUtil.YELLOW + "Uranium found food: " + entity.getPosition().getX() + "x, " + entity.getPosition().getY() + "y, " + entity.getPosition().getZ() + "z.");
            }else
                this.chicken.add ( entity );
            if ( ! this.Sound.getValue ( ) ) continue;
            FoodFinder.mc.player.playSound ( SoundEvents.ENTITY_IRONGOLEM_ATTACK , 1.0f , 1.0f );
        }
    }
}