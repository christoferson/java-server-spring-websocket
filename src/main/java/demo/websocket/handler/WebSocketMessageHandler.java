package demo.websocket.handler;

import java.io.IOException;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketMessageHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(String.format("Connected to WebSocket Server. Session=%s Addfress=%s", session.getId(), session.getRemoteAddress()));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Received: " + message.getPayload());
        try {
            TextMessage outputMessage = new TextMessage(String.format("[%s] Reply: %s", session.getId(), message.getPayload()));
            session.sendMessage(outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(String.format("Disconnected from WebSocket Server. Session=%s", session.getId()));
    }
}
