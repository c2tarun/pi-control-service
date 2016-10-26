package com.c2tarun.service.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.pi4j.io.gpio.*;

/**
 * Created by tarun on 10/24/16.
 */
public class PinConfigurationModule extends AbstractModule {
    @Override
    protected void configure() { }

    @Provides
    public GpioController getGpioController() {
        return GpioFactory.getInstance();
    }

    @Provides
    @Named("redLed")
    public GpioPinDigitalOutput getRedLedPin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "myredled", PinState.LOW);
    }

    @Provides
    @Named("yelloLed")
    public GpioPinDigitalOutput getYellowLedPin(GpioController gpioController) {
        return gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "myredled", PinState.LOW);
    }
}
