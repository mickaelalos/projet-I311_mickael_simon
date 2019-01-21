package fr.univtln.malos_samil.i311.projet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Hello world!
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        logger.info("App started.");
        logger.debug("About to talk :");
        System.out.println("Hello world !");
    }
}
