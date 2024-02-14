package com.example.demo.dao;

import com.example.demo.bot.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FriendDao extends JpaRepository<Friend, UUID> {
}
