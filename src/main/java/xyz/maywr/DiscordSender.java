package xyz.maywr;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class DiscordSender {

    /**
     * Method that sends message to a webhook. Took it from old protection
     * @param message message you wanna send
     * @param webhook webhook ur sending a message to
     */
    public static void sendMessage(String message, String webhook) {
        PrintWriter out = null;
        try {
            URL realUrl = new URL(webhook);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            out.print(postData);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method that sends successful message on auth
     * @param HWID user's HWID
     * @param mcName user's name
     * @param webhook discord webhook
     */
    public static void sendDoneMessage(String HWID, String mcName, String webhook) {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String json = "{\"content\":null,\"embeds\":[{\"title\":\""+mcName+" успешно запустил клиент\",\"color\":720640,\"fields\":[{\"name\":\"HWID\",\"value\":\""+HWID+"\",\"inline\":true},{\"name\":\"Время\",\"value\":\""+time+"\",\"inline\":true}]}],\"attachments\":[]}";
        try {
            URL url = new URL(webhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "maywie");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(json.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that sends unsuccessful message on auth
     * @param HWID user's HWID
     * @param mcName user's name
     * @param webhook discord webhook
     */
    public static void sendFailMessage(String HWID, String mcName, String webhook) {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String json = "{\"content\":null,\"embeds\":[{\"title\":\""+mcName+" попытался запустить клиент\",\"color\":16711680,\"fields\":[{\"name\":\"HWID\",\"value\":\""+HWID+"\",\"inline\":true},{\"name\":\"Время\",\"value\":\""+time+"\",\"inline\":true}]}],\"attachments\":[]}";
        try {
            URL url = new URL(webhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "maywie");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(json.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
