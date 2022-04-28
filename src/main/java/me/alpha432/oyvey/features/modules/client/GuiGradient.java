package me.alpha432.oyvey.features.modules.client;

import com.jcraft.jogg.Page;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.client.gui.Gui;

public class GuiGradient extends Module {
    private static GuiGradient INSTANCE;

    public final Setting<Page> page = register(new Setting<>("Page", Page.FirstColor));
    public final Setting<Boolean> rainbow1 = register(new Setting<>("Rainbow", true, yes -> page.getValue().equals(Page.FirstColor)));
    public final Setting<Integer> red1 = register(new Setting<>("Red", 0, 0, 255, yes -> !rainbow1.getValue() && page.getValue().equals(Page.FirstColor)));
    public final Setting<Integer> green1 = register(new Setting<>("Green", 0, 0, 255, yes -> !rainbow1.getValue() && page.getValue().equals(Page.FirstColor)));
    public final Setting<Integer> blue1 = register(new Setting<>("Blue", 0, 0, 255, yes -> !rainbow1.getValue() && page.getValue().equals(Page.FirstColor)));
    public final Setting<Integer> alpha1 = register(new Setting<>("Alpha", 0, 0, 255, yes -> !rainbow1.getValue() && page.getValue().equals(Page.FirstColor)));

    public final Setting<Boolean> rainbow2 = register(new Setting<>("Rainbow", false, yes -> page.getValue().equals(Page.SecondColor)));
    public final Setting<Integer> red2 = register(new Setting<>("Red", 0, 0, 255, yes -> !rainbow2.getValue() && page.getValue().equals(Page.SecondColor)));
    public final Setting<Integer> green2 = register(new Setting<>("Green", 0, 0, 255, yes -> !rainbow2.getValue() && page.getValue().equals(Page.SecondColor)));
    public final Setting<Integer> blue2 = register(new Setting<>("Blue", 0, 0, 255, yes -> !rainbow2.getValue() && page.getValue().equals(Page.SecondColor)));
    public final Setting<Integer> alpha2 = register(new Setting<>("Alpha", 0, 0, 255, yes -> !rainbow2.getValue() && page.getValue().equals(Page.SecondColor)));

    public GuiGradient(){super("GuiGradient","",Category.CLIENT,true,false,false);
        INSTANCE = this;}

    public static GuiGradient getInstance() {
        return INSTANCE;
    }
    private enum Page {
        FirstColor, SecondColor;
    }
}
