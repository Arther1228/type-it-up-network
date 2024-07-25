package com.yang.websocket.demo.client;

import com.yang.websocket.demo.wssclient.WssWebSocketClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * https://blog.csdn.net/hitpter/article/details/134574273
 */
@Controller
public class HomeController {

    private final WebSocketClientService webSocketClientService;
    private final WssWebSocketClientService wssWebSocketClientService;

    @Autowired
    public HomeController(WebSocketClientService webSocketClientService, WssWebSocketClientService wssWebSocketClientService) {
        this.webSocketClientService = webSocketClientService;
        this.wssWebSocketClientService = wssWebSocketClientService;
    }

    @GetMapping("/")
    public String home() {
        webSocketClientService.connect();
        return "home";
    }

    @GetMapping("/wss")
    @ResponseBody
    public String wss() {
        wssWebSocketClientService.connectAndSendMessage();
        return "WebSocket operation completed";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        webSocketClientService.sendMessage(message);
        return "home";
    }

    @PostMapping("/disconnect")
    public String disconnect() {
        webSocketClientService.disconnect();
        return "home";
    }
}
