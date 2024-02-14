package com.example.demo.bot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "chat")
    private Long chat;
    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private Mode mode;
    @Column(name = "last_command")
    private String lastCommand;
}
