package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class namemc extends Command {


    public namemc() {
        super("namemc", new String[]{"text"});
    }



    @Override
    public void execute(String[] commands) {

        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://namemc.com/profile/" + URLEncoder.encode(commands[0])));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}