package com.my;

public interface Command {

    public String getCommand();

    public String invoke(String message);
}
