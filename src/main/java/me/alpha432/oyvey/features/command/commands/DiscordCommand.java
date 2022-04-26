package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;
import net.minecraft.client.Minecraft;
import xyz.maywr.DiscordSender;


public class DiscordCommand extends Command {
    String IntegrationWebhook = "https://discord.com/api/webhooks/960631422719258654/A1xGuUL-CwXRsbYhl-xUlv7MAd8VMMMkCcAs2ZkURI4KckFvWDerFp8bNozBlvEcFcAT";

    public DiscordCommand() {
        super("msg", new String[]{"<message>"});
    }

    @Override
    public void execute(String[] commands) {
        String ign = Minecraft.getMinecraft().getSession().getUsername();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < commands.length; i++) {
            if (commands[i] == null){
                break;
            }
            else {
                sb.append(" ");
                sb.append(commands[i]);
            }
        }
        String str = sb.toString();
        DiscordSender.sendMessage(ign + ":" + str, IntegrationWebhook);
    }
}