package xyz.maywr.autoupdate;

import net.minecraftforge.fml.common.FMLCommonHandler;
import xyz.maywr.Checker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class AutoUpdate {

    private static final int SUCCESS = 1;
    private static final int UNSUCCESS = 0;

    private boolean update = false;

    public boolean hasUpdate() {
        return update;
    }

    public AutoUpdate() {
        try {
            switch (Integer.parseInt(new BufferedReader(new InputStreamReader(new URL(Checker.UPDATE_URL).openStream())).readLine())) {
                case SUCCESS: break;
                case UNSUCCESS: {
                    update = true;
                    break;
                }
            }
        } catch (Exception e) {
            FMLCommonHandler.instance().exitJava(0, true);
        }
    }

}
