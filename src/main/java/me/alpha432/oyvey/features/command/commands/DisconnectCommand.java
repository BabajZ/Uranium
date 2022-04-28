package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.util.World.WorldUtil;

/**
 * @author ligmaballz
 */
public class DisconnectCommand extends Command
{
    public DisconnectCommand() {
        super("disconnect");
    }

    @Override
    public void execute(final String[] commands) {
        WorldUtil.disconnectFromWorld(this);
    }
}