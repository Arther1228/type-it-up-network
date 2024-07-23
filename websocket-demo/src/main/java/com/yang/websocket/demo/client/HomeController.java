package com.yang.websocket.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * https://blog.csdn.net/hitpter/article/details/134574273
 */
@Controller
public class HomeController {

    private final WebSocketClientService webSocketClientService;

    @Autowired
    public HomeController(WebSocketClientService webSocketClientService) {
        this.webSocketClientService = webSocketClientService;
    }

    @GetMapping("/")
    public String home() {
        webSocketClientService.connect();
        return "home";
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
