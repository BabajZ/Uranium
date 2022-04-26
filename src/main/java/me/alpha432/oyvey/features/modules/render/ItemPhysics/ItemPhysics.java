package me.alpha432.oyvey.features.modules.render.ItemPhysics;


import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class ItemPhysics
        extends Module {
    public static ItemPhysics INSTANCE = new ItemPhysics();
    public final Setting<Float> Scaling = this.register(new Setting<Float>("Scaling", Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(10.0f)));

    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static ItemPhysics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemPhysics();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}