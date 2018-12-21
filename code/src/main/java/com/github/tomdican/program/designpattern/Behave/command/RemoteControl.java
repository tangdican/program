package com.github.tomdican.program.designpattern.Behave.command;

public class RemoteControl {
    public void excuteCommand(Command command) {
        command.execute();
    }

    public static void main(String[] args) {
        Light light = new Light();
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.excuteCommand(lightOnCommand);
        remoteControl.excuteCommand(lightOffCommand);
    }

}
