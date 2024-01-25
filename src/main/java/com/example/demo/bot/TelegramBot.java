package com.example.demo.bot;

import com.example.demo.bot.component.Command;
import com.example.demo.bot.component.Keyboard;
import com.example.demo.bot.component.Message;
import com.example.demo.bot.component.Predicates;
import com.example.demo.bot.service.FriendService;
import com.example.demo.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.function.Consumer;

@Component
@SuppressWarnings("unused")
public class TelegramBot extends AbilityBot { // todo https://www.baeldung.com/spring-boot-telegram-bot

    private final FriendService friendService;

    @Autowired
    public TelegramBot(BotConfig botProperties, FriendService friendService) {
        super(botProperties.getToken(), botProperties.getBotName());
        this.friendService = friendService;
    }

    /**
     * Выводит приветственное сообщение и кнопки с доступными командами
     */
    public Reply hello() {
        Consumer<Update> action = upd -> {
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
        Consumer<Update> action = upd -> {
            StringBuilder message = new StringBuilder("Перечень команд которыми вы можете воспользоваться:\n");
            for (Command value : Command.values()) {
                message.append(value.getLocalizedLowerCase()).
                    append(" (").append(value.getName()).append(")")
                    .append(" - ").append(value.getDescription()).append("\n");

            }
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
        // todo допилить
        String answer = "Вызываю всех @n_hanova @itrealrew @mikhail_kurochkin @user_undef @Anna_Eletsky @Anna_Eletsky @vasilisa_obrubova @test_user_9 @LizaShiryaeva @KamradSirGrey @j_nans @agonyaev @x69760d0a @kochetkova_olesun @ribusick ";

        Consumer<Update> action = upd -> {
            StringBuilder message = new StringBuilder(answer);
            sendMessage(upd.getMessage().getChatId(), message.toString());
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isAll()
        );
    }

    /**
     * ДР
     */
    public Reply bd(){
        String answer = "Список дней рождений: " + getBd();

        Consumer<Update> action = upd -> {
            StringBuilder message = new StringBuilder(answer);
            sendMessage(upd.getMessage().getChatId(), message.toString());
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
    public String getBd() {
        StringBuffer stringBuffer = new StringBuffer();
        friendService.get().forEach(friend -> stringBuffer.append("\n").append(friend.getName()).append(" ").append(friend.getDr()));
        return stringBuffer.toString();
    }

    /**
     * Метод создания опроса
     */
    public Reply meeting(){
        String answer = "Я не умею(((";

        Consumer<Update> action = upd -> {
            StringBuilder message = new StringBuilder(answer);
            sendMessage(upd.getMessage().getChatId(), message.toString());
        };

        return Reply.of(action,
            Flag.MESSAGE,
            Flag.TEXT,
            Predicates.isMeeting()
        );
    }



    private void sendMessage(Long chatId, String message, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        if(keyboard != null) {
            sendMessage.setReplyMarkup(keyboard);
        }
        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException e) {
            BotLogger.error("Unable to send message", e);
        }
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
    public int creatorId() {
        return 0;
    }
}
