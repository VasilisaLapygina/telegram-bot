package com.example.demo.bot;

import com.example.demo.bot.component.Command;
import com.example.demo.bot.component.Keyboard;
import com.example.demo.bot.component.Message;
import com.example.demo.bot.component.Predicates;
import com.example.demo.bot.model.Application;
import com.example.demo.bot.model.Friend;
import com.example.demo.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

@Component
@SuppressWarnings("unused")
public class TelegramBot extends AbilityBot {

    private final Application application;
    private final Random random;

    @Autowired
    public TelegramBot(BotConfig botProperties, Application application) throws NoSuchAlgorithmException {
        super(botProperties.getToken(), botProperties.getBotName());
        this.application = application;
        this.random = SecureRandom.getInstanceStrong();
    }

    /**
     * Выводит приветственное сообщение и кнопки с доступными командами
     */
    public Reply hello() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            String answer = ". Привет, " + upd.getMessage().getFrom().getFirstName();
            sendMessage(upd.getMessage().getChatId(), Message.HELLO.getText() + answer, Keyboard.getDefaultKeyboard());
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isStartCommand());
    }

    /**
     * Подручный. Выводит все существующие команты с описанием
     */
    public Reply helper(){
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            StringBuilder message = new StringBuilder("Перечень команд которыми вы можете воспользоваться:\n");
            Arrays.stream(Command.values())
                .filter(Command::isShow)
                .forEach(value -> message.append(value.getLocalizedLowerCase()).
                    append(" (").append(value.getName()).append(")")
                    .append(" - ").append(value.getDescription()).append("\n"));
            sendMessage(upd.getMessage().getChatId(), message.toString());
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isHelpCommand()
        );
    }

    /**
     * Тегает всех в комнате
     */
    public Reply all(){
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            String answer = "Вызываю всех \n" + getFriends();
            sendMessage(upd.getMessage().getChatId(), answer);
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isAll()
        );
    }

    /**
     * Метод выводит список всех пользователей
     */
    private String getFriends() {
        StringBuilder stringBuffer = new StringBuilder();
        application.getFriends()
            .forEach(friend ->
                stringBuffer.append("\n")
                    .append(friend.getNickname())
                    .append(" (").append(friend.getName()).append(" ) ")
            );
        return stringBuffer.toString();
    }


    /**
     * ДР
     */
    public Reply bd(){
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            String answer = "Список дней рождений: \n" + getBd();
            sendMessage(upd.getMessage().getChatId(), answer);
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isBd()
        );
    }

    /**
     * Метод выводит список всех дней рождений
     */
    private String getBd() {
        StringBuilder stringBuilder = new StringBuilder();
        application.getFriends()
            .forEach(friend -> stringBuilder.append("\n").append(friend.getName()).append(" ").append(friend.getDr()));
        return stringBuilder.toString();
    }

    /**
     * Отправка валентинки
     */
    public Reply valentine() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            String message = upd.getMessage().getText();

            String answer = "Вам написали валентинку: \n" ; // todo добавить текст валентинки из смс
            sendMessage(upd.getMessage().getChatId(), answer); // todo добавить чат
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isValentine()
        );
    }

    /**
     * Обработка нового пользователя
     */
    public Reply newUser() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            List<User> newUser = upd.getMessage().getNewChatMembers();
            newUser.forEach(u -> {
                String message =  "Привет, " + u.getFirstName()+ ". Рад тебя видеть!" ;
                sendMessage(upd.getMessage().getChatId(), message,  Keyboard.getDefaultKeyboard());
            });
        };
        return Reply.of(action,
            Flag.MESSAGE,
            upd ->  upd.getMessage().getNewChatMembers() != null);
    }

    /**
     * Метод создания опроса
     */
    public Reply registration(){
        String answer = "Спасибо регистрация прошла успешно!";

        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            String message = upd.getMessage().getText();
            // todo определить пользователя и сохранить чат
            sendMessage(upd.getMessage().getChatId(), answer);
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isRegistration()
        );
    }

    public Reply blowjob() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> {
            int blowChance = random.nextInt(10);
            if(blowChance == 9) {
                List<Friend> friends = application.getFriends();
                int friendIndex = random.nextInt(friends.size());
                Friend friendToBlow = friends.get(friendIndex);
                String message = "Тебе отсосет " + friendToBlow.getNickname();
                sendMessage(upd.getMessage().getChatId(), message);
            } else {
                String message = "Сам отсоси, потом проси!";
                sendMessage(upd.getMessage().getChatId(), message, null, upd.getMessage().getMessageId());
            }
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isBlowjob()
        );
    }

    private void sendMessage(Long chatId, String message, ReplyKeyboard keyboard, Integer replyToMessageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyToMessageId(replyToMessageId);
        if(keyboard != null) {
            sendMessage.setReplyMarkup(keyboard);
        }
        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Unable to send message: " + e);
        }
    }

    private void sendMessage(Long chatId, String message, ReplyKeyboard keyboard) {
        sendMessage(chatId, message, keyboard, null);
    }


    private void sendMessage(Long chatId, String message) {
        sendMessage(chatId, message, null);
    }

    private void sendMessage(Long chatId, Message message) {
        sendMessage(chatId, message.getText(), null);
    }

    private boolean inGroup(Update upd) {
        return upd.getMessage().isGroupMessage();
    }

    private boolean inPrivate(Update upd) {
        return upd.getMessage().isUserMessage();
    }

    //todo возможная цель: писать какие-нибудь логи о работе
    /**
     * Добавление создателя
     */
    @Override
    public long creatorId() {
        return 0;
    }
}
