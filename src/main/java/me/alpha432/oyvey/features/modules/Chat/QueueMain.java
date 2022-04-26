package me.alpha432.oyvey.features.modules.Chat;

import me.alpha432.oyvey.features.modules.Module;

public class QueueMain extends Module {

    public QueueMain()
    {
        super ( "QueueMain" , "QueueMain" , Category.CHAT , true , false , false );
    }

    @Override public void onTick(){
        mc.player.sendChatMessage("/queue main");
    }
}
