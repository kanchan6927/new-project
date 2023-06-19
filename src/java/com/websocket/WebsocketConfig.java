/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.websocket;

import com.techblog.dao.LikeDao;
import com.techblog.entities.RegUser;
import com.techblog.entities.likeStatus;
import com.techblog.helpers.ConnectionProvider;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/websocket")
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)

public class WebsocketConfig {

    private static Set<Session> sessions = new HashSet<>();
    private static Map<Integer, Session> sessionss = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        // Code to handle when a client WebSocket connection is opened

        HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
        int userId = ((RegUser) httpSession.getAttribute("CurrentUser")).getId();
        LikeDao ld = new LikeDao(ConnectionProvider.getConnection());

        List<likeStatus> list = ld.getAllInfoAboutNot(userId);

        for (likeStatus ls : list) {
            int NotfUserId = ls.getLikedUserId();
            sessionss.put(NotfUserId, session);
        }

        int userIdd = 1;

        sessions.add(session);

//        sessionss.put(userIdd, session);
        try {

            String sessionInfo = "Session ID: " + session.getId() + "\n"
                    + "Protocol Version: " + session.getProtocolVersion() + "\n"
                    + "Max Idle Timeout: " + session.getMaxIdleTimeout() + "\n"
                    + "Current userId: " + userId + "\n"
                    + "User Principal: " + session.getUserPrincipal();

//            session.getBasicRemote().sendText(sessionInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Code to handle when a message is received from a client

    }

    @OnClose
    public void onClose(Session session) {
        // Code to handle when a client WebSocket connection is closed
    }

    @OnError
    public void onError(Throwable error) {
        // Code to handle WebSocket errors
    }

    public void sendNotification(String notificationMessage) {
        for (Session session : sessions) {

            try {
                session.getBasicRemote().sendText(notificationMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public Session getSessionbyuserId(int userId) {
        String userIdString = String.valueOf(userId);
        return sessionss.get(userId);

    }

}
