import com.my.commands.WhatIsMyIp;
import org.junit.Ignore;
import org.junit.Test;

public class CommandTest {

    @Ignore
    @Test
    public void commandTest() throws Exception {

        WhatIsMyIp cmd = new WhatIsMyIp();
        String dir = cmd.invoke("curl http://ipecho.net/plain");
        System.out.print(dir);
    }
}
