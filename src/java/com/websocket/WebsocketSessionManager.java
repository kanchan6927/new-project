/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.websocket;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;

/**
 *
 * @author KIRAN
 */
public class WebsocketSessionManager {

    private static final List<Session> sessions = new ArrayList<>();

    public static void addSession(Session session) {
        sessions.add(session);
    }

    public static void removeSession(Session session) {
        sessions.remove(session);
    }

    public static void sendMessageToActiveSessions(String message) {
        List<Session> activeSessions = new ArrayList<>();
        for (Session session : sessions) {
            if (session.isOpen()) {
                activeSessions.add(session);
            }
        }

        for (Session session : activeSessions) {
            session.getAsyncRemote().sendText(message);
        }
    }

}
