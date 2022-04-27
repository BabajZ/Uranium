package me.alpha432.oyvey.features.modules.render.Enviroments;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.modules.render.SkyColor;

public class Enviroments extends Module {




    private static Enviroments INSTANCE = new Enviroments();


    public Enviroments(){super("Enviroments","",Category.RENDER,true,false,false);}


    private void setInstance() {
        INSTANCE = this;
    }
    public static Enviroments getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Enviroments();
        return INSTANCE;
    }
}
