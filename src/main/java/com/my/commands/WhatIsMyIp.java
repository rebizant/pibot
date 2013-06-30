package com.my.commands;

public class WhatIsMyIp extends BashCommand {

    public static final String COMMAND_NAME = "remoteip";

    @Override
    public String getCommand() {
        return COMMAND_NAME;
    }

    @Override
    public String invoke(String message) {
        return super.invoke("curl http://ipecho.net/plain");
    }
}
