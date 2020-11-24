package com.example.dialogue.network;

import tech.gusavila92.websocketclient.WebSocketClient;
/**
 * @author Jacob Nett
 */
public interface IWeb {
    public void createWebSocketClient();
    public WebSocketClient getWebSocketClient();
}
