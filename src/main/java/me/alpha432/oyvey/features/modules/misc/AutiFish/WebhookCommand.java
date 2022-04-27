package me.alpha432.oyvey.features.modules.misc.AutiFish;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.misc.AutiFish.AutoFish;
import me.alpha432.oyvey.util.oyvey.MessageUtil;

import java.awt.*;

public class WebhookCommand extends Command {

    Desktop desktop = Desktop.getDesktop();

    public WebhookCommand() {
        super("webhook");
    }


    @Override
    public void execute(String[] commands) {
        try {
            if (!commands[0].matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")) {
                MessageUtil.sendMessage("kinda seems to be not a webhook bro");
                return;
            }
            AutoFish.webhook = commands[0];
            MessageUtil.sendMessage("webhook changed");
        } catch (Exception e) {
            MessageUtil.sendMessage("something went wrong. check da console");
            e.printStackTrace();
        }
    }
}