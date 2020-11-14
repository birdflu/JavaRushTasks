package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private Switchable switchable;

    public ElectricPowerSwitch(Switchable securitySystem) {
        this.switchable = securitySystem;
    }

    public void press() {
        System.out.println("Power switch flipped.");
        if (switchable.isOn()) {
            switchable.turnOff();
        } else {
            switchable.turnOn();
        }
    }
}
