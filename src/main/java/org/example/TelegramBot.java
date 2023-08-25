package org.example;


import org.example.service.WeatherService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TelegramBot extends TelegramLongPollingBot {
    private final String token;
    private final String botName;
    private final WeatherService weatherService = new WeatherService();


    public TelegramBot(String token, String botName) {
        this.token = token;
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        boolean isNoFoundCity = false;
        try {
            SendMessage message = new SendMessage();
            URL url = new URL(weatherService.buildHttpRequestWeather(update.getMessage().getText()));
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = null;

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                isNoFoundCity = true;
            }

            message.enableHtml(true);
            message.setChatId(update.getMessage().getChatId());

            if (isNoFoundCity) {
                message.setText("Некоректне введення назва міста");
                execute(message);
            }

            String messageWeather = weatherService.getWeather(bufferedReader);
            message.setText(messageWeather);


            execute(message);


        } catch (TelegramApiException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
