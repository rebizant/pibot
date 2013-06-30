package com.my.commands;

import com.my.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashCommand implements Command {

    public static final String COMMAND_NAME = "bash";

    @Override
    public String getCommand() {
        return COMMAND_NAME;
    }

    @Override
    public String invoke(String message) {
        Runtime rt = Runtime.getRuntime();
        try {
            Process process = rt.exec(new String[]{"bash", "-c", message});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + " \n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return "ERROR " + e.getMessage();
        }
    }
}
