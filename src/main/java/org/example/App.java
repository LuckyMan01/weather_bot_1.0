package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.util.Properties;

public class App {
    private static final String FILE_NAME = "src/main/resources/application.properties" ;

    public static void main(String[] args) throws TelegramApiException {
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            Properties prop = new Properties();
            prop.load(input);

            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(new TelegramBot(prop.getProperty("bot.token"), prop.getProperty("bot.name")));

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
