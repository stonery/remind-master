package com.remind.diamond;

import com.github.diamond.client.event.ConfigurationEvent;
import com.github.diamond.client.event.ConfigurationListener;

public class ConfigurationListenerTest implements ConfigurationListener {
    @Override
    public void configurationChanged(ConfigurationEvent event) {
        System.out.println(event.getType().name() + " " + event.getPropertyName() + " " + event.getPropertyValue());
    }
}