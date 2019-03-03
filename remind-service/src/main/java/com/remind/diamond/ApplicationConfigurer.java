package com.remind.diamond;

import com.github.diamond.client.PropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class ApplicationConfigurer {

    @Bean
    public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        PropertiesConfiguration config = new PropertiesConfiguration("106.14.164.102", 8283, "DEMO", "development");
        config.addConfigurationListener(new ConfigurationListenerTest());
        propertyPlaceholderConfigurer.setProperties(config.getProperties());
        String test1 = config.getString("test1");
        System.out.println(test1+"development");
        return propertyPlaceholderConfigurer;
    }
}