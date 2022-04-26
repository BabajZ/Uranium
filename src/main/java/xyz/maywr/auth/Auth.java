package xyz.maywr.auth;

import net.minecraftforge.fml.common.FMLCommonHandler;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.maywr.Checker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Auth {

    List<String> hwids = new ArrayList<>();

    public Auth() {
        HWID = DigestUtils.sha256Hex(DigestUtils.sha256Hex(System.getenv("os")
                + System.getProperty("os.name")
                + System.getProperty("os.arch")
                + System.getProperty("user.name")
                + System.getenv("SystemRoot")
                + System.getenv("HOMEDRIVE")
                + System.getenv("PROCESSOR_LEVEL")
                + System.getenv("PROCESSOR_REVISION")
                + System.getenv("PROCESSOR_IDENTIFIER")
                + System.getenv("PROCESSOR_ARCHITECTURE")
                + System.getenv("PROCESSOR_ARCHITEW6432")
                + System.getenv("NUMBER_OF_PROCESSORS")
        ));

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(Checker.HWID_URL).openStream()));
            String l = null;

            while((l = reader.readLine()) != null) {
                hwids.add(l);
            }
        } catch (Exception e) {
            FMLCommonHandler.instance().exitJava(0, true);
        }

        for (String hwid : hwids) {
            if (hwid.equals(this.HWID)) {
                access = true;
            }
        }
    }

    private String HWID = "";
    private boolean access = false;

    public String getHWID() {
        return HWID;
    }

    public boolean hasAccess() {
        return access;
    }

    public List<String> getHwids() {
        return hwids;
    }
}
