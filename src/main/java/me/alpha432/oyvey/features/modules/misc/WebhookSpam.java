package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.MessageUtil;
import xyz.maywr.DiscordSender;

public class WebhookSpam extends Module {

    public Setting<String> webhook = this.register(new Setting<String>("webhook", "<Webhook to DDOS>", "Sets the state of the DiscordRPC."));
    public Setting<String> message1 = this.register(new Setting<String>("webhook", "<Ur message>", "Sets the state of the DiscordRPC."));
    private final Setting<Integer> amount = this.register(new Setting<Integer>("amount", 50, 1, 300));

    public WebhookSpam(){super("WebhookSpam","",Category.MISC,true,false,false);}

    String IntegrationWebhook = webhook.getValue();
    String msg = message1.getValue();

    public void onEnable(){
        for(int i = 0; i < amount.getValue(); i++) {
            DiscordSender.sendMessage("@everyone" + msg, IntegrationWebhook);
            MessageUtil.sendMessage("Sended spam to webhook");}
    }
}
