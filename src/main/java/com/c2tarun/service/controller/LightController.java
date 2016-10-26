package com.c2tarun.service.controller;

import com.google.inject.Inject;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("light")
public class LightController {

    private static GpioPinDigitalOutput redLedPin;
    private static GpioPinDigitalOutput yellowLedPin;

    @Inject
    public LightController(GpioPinDigitalOutput redLedPin, GpioPinDigitalOutput yellowLedPin) {
        this.redLedPin = redLedPin;
        this.yellowLedPin = yellowLedPin;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String toggleLight() {

        redLedPin.toggle();
        yellowLedPin.toggle();
        PinState state = redLedPin.getState();
        switch (state) {
            case HIGH:
                return "Light on";
            case LOW:
                return "Light off";
        }
        return "Something weird happened";
    }
}
