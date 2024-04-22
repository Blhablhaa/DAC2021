package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//how to tell SC : following class contains instru
@Configuration
@ComponentScan(basePackages = {"dependent,dependency"})
public class AppConfig {

}
