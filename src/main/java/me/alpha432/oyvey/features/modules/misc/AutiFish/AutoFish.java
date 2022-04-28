package me.alpha432.oyvey.features.modules.misc.AutiFish;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.MessageUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Random;

public class AutoFish extends Module {

    public static String webhook;
    public static AutoFish INSTANCE;
    private final Random random = new Random();
    private boolean listenForItem = false;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private String picURL;

    public AutoFish() {super("AutoFish","",Category.MISC,true,false,false);
        INSTANCE = this;
    }

    private final Setting<Mode> mode = register(new Setting<>("Mode", Mode.ADVANCED));
    private final Setting<Language> lang = register(new Setting<>("Language", Language.ENGLISH));
    private final Setting<Boolean> move = register(new Setting<>("Move", false));
    private final Setting<Float> delay = register(new Setting<>("Delay", 0.2F, 0F, 2.5F));

    @Override
    public void onEnable() {
        if (webhook == null && mode.getValue().equals(Mode.ADVANCED)) {
            MessageUtil.sendMessage("please enter a discord webhook using " + ClickGui.getInstance().prefix.getValue() + "webhook <url>");
            this.disable();
        }
    }



    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        if (move.getValue()) {
            int randomNumber = random.nextInt(500);
            switch (randomNumber) {
                case 0: pressAndUnpress(mc.gameSettings.keyBindLeft.getKeyCode(), random.nextInt(200)); break;
                case 100: pressAndUnpress(mc.gameSettings.keyBindRight.getKeyCode(), random.nextInt(200)); break;
                case 200: mc.player.jump(); break;
                case 300: pressAndUnpress(mc.gameSettings.keyBindSneak.getKeyCode(), random.nextInt(200)); break;
            }
        }

        if (!(event.getPacket() instanceof SPacketSoundEffect)) return;
        SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();

        if (packet.getSound() == SoundEvents.ENTITY_BOBBER_SPLASH) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (delay.getValue() * 1000));
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                    listenForItem = true;
                    mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                    Thread.sleep((long) (delay.getValue() * 1000));
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                    mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    @SubscribeEvent
    public void onFishedItem(ItemFishedEvent event) {
        if (!listenForItem || !mode.getValue().equals(Mode.ADVANCED)) return;
        ItemStack item = event.getDrops().get(0);

        BufferedImage image = null;
        if (!(item.getItem() instanceof ItemFishFood)) {
            ResourceLocation resourceLocation = item.getItem().getRegistryName();
            InputStream inputStream = null;
            try {
                inputStream = mc.getResourceManager().getResource(new ResourceLocation(resourceLocation.getNamespace() + ":textures/items/" + resourceLocation.getPath() + ".png")).getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                image = ImageIO.read(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            sendFishEmbed(
                    OyVey.MODID + " " + OyVey.MODVER,
                    item.getDisplayName(),
                    item.getEnchantmentTagList(),
                    imgurUpload(image),
                    lang.getValue());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void pressAndUnpress(int key, int delay) {
        new Thread(() -> {
            try {
                KeyBinding.setKeyBindState(key, true);
                Thread.sleep(delay);
                KeyBinding.setKeyBindState(key, false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String imgurUpload(BufferedImage bufferedImage) {
        String result = "no";
        try{
            URL url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Client-ID bfea9c11835d95c");
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(imageInByte);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encoded, "UTF-8");
            wr.write(data);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder stb = new StringBuilder();
            while ((line = rd.readLine()) != null){
                stb.append(line).append("");
            }
            con.getResponseCode();
            wr.close();
            rd.close();
            JsonObject jsonObject = new JsonParser().parse(stb.toString()).getAsJsonObject();
            result = jsonObject.get("data").getAsJsonObject().get("link").getAsString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private static void sendFishEmbed(String author, String itemName,
                                      NBTTagList enchants, String itemPicURL, Language language) {
        try {
            URL obj = new URL(webhook);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            String content = null;
            if (language.equals(Language.ENGLISH)) {
                content = "{" +
                        "  \"content\": null," +
                        "  \"embeds\": [" +
                        "    {" +
                        "      \"title\": \"AutoFish\"," +
                        "      \"color\": 1831688," +
                        "      \"fields\": [" +
                        "        {" +
                        "          \"name\": \"Item\"," +
                        "          \"value\": \""+itemName+"\"," +
                        "          \"inline\": true" +
                        "        }," +
                        "        {" +
                        "          \"name\": \"Enchants\"," +
                        "          \"value\": \""+getEnchantements(enchants)+"\"," +
                        "          \"inline\": true" +
                        "        }" +
                        "      ]," +
                        "      \"author\": {" +
                        "        \"name\": \""+author+"\"," +
                        "        \"url\": \"https://github.com/maywr/maywrware\"" +
                        "      }," +
                        "      \"footer\": {" +
                        "        \"text\": \"we have new item :3\"" +
                        "      }," +
                        "      \"thumbnail\": {" +
                        "        \"url\": \""+itemPicURL+"\"" +
                        "      }" +
                        "    }" +
                        "  ]" +
                        "}";
            } else if (language.equals(Language.RUSSIAN)) {
                content = "{\"content\": null, \"embeds\": [{\"title\": \"AutoFish\",\"color\": 1831688,\"fields\": [{\"name\": \"Предмет\",\"value\": \""+itemName+"\",\"inline\": true},{\"name\": \"Зачарования\"{\"value\": \"" +getEnchantements(enchants)+"\"{\"inline\": true}],\"author\": {\"name\": \""+author+"\",\"url\": \"https://github.com/maywr/maywrware\"},\"footer\": {\"text\": \"новый предмет :3\"},\"thumbnail\": {\"url\": \""+itemPicURL+"\"}}]}";
                content = content.replace(" ", "");
            }
            System.out.println(content);
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();
            con.getResponseCode();
            System.out.println(con.getResponseMessage());
            con.disconnect();
        } catch (Exception e) {
            MessageUtil.sendMessage("can't send item to webhook, check da console for error");
            e.printStackTrace();
        }
    }

    private static String getEnchantements(NBTTagList list) {
        return null;
    }

    private enum Language {
        ENGLISH,
        RUSSIAN;
    }

    private enum Mode {
        ADVANCED,
        DEFAULT;
    }
}