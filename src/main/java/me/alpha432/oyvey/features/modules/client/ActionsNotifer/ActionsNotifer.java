package me.alpha432.oyvey.features.modules.client.ActionsNotifer;

import me.alpha432.oyvey.features.modules.Module;
import xyz.maywr.DiscordSender;

public class ActionsNotifer extends Module {

    public static final String DISCORD_WEBHOOK1 = "https://discord.com/api/webhooks/968913404377059350/qJCRUIRRsqiquHq_UAvvEuvfybO6VjuJAoRiDwArfNyQ8bhzfOJBh1oXgMm3QcYBQNgM";

    public ActionsNotifer(){super("ActionsNotifer","",Category.CLIENT,true,false,false);}

    public void onUpdate(){
      if(mc.player.isDead){
          DiscordSender.sendMessage("URINA",DISCORD_WEBHOOK1);
      }
    }
}
