package com.example.demo.bot.component;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Predicate;

@Component
public class Predicates {

    public static Predicate<Update> isStartCommand() {
        return upd -> {
            String message = upd.getMessage().getText();
            return message.contains(Command.START.getName())
                    || message.toLowerCase().contains(Command.START.getLocalizedLowerCase());
        };
    }

    public static Predicate<Update> isHelpCommand() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.HELP.getName())
                    || message.toLowerCase().contains(Command.HELP.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> isAll() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.ALL.getName())
                || message.toLowerCase().contains(Command.ALL.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> isBd() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.BD.getName())
                || message.toLowerCase().contains(Command.BD.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> isValentine() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.VALENTINE.getName())
                || message.toLowerCase().contains(Command.VALENTINE.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> isMeeting() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.MEETING.getName())
                || message.toLowerCase().contains(Command.MEETING.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> isRegistration() {
        return upd -> {
            String message = upd.getMessage().getText();
            return (message.contains(Command.REGISTRATOIN.getName())
                || message.toLowerCase().contains(Command.REGISTRATOIN.getLocalizedLowerCase()));
        };
    }

    public static Predicate<Update> toMe(String botName) {
        return upd -> upd.getMessage().getText().contains("@".concat(botName));
    }
}
