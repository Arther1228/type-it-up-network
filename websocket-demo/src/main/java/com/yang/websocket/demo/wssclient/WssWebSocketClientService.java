package com.yang.websocket.demo.wssclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

@Slf4j
@Service
public class WssWebSocketClientService {

    private WssMyWebSocketHandler webSocketHandler;

    public void connectAndSendMessage() {
        try {
            SSLSocketFactory sslSocketFactory = WssWebSocketClientConfig.createSSLSocketFactory();
            URI uri = WssWebSocketClientConfig.getWebSocketURI();
            webSocketHandler = new WssMyWebSocketHandler(uri);
            webSocketHandler.setSocketFactory(sslSocketFactory);
            webSocketHandler.connectBlocking();

            // 发送消息
            String message = "{\"id\":\"2\",\"type\":\"add\",\"payload\":{\"query\":\"subscription{    drone {      id,      name,      description,       image,      state,      direction,      distance,      whitelisted,      can_attack,      can_takeover,       can_ctrl_landing,      can_tdoa,      in_ada,      speed,      attacking,      ctrl_landing,       attacking_ttl,      created_time,      deleted_time,      lastseen_time,      lastseen,      longitude,       latitude,      height,      seen_sensor{          snr_dB,          sensor_id,          detected_freq_khz,           signal_dbm,          port      },      attack_bands,      attack_type,      tdoa_tracking,      tracing{           lastlen          origin{              lat              lng            },points      }      localization{          lat,           lng      },      initial_location{          lat,          lng      },      rc_location{        lat,        lng      },       link_id,      has_duplicate,      jamming_conflicts,      confirmed,      tracking_video,       has_screenshot,      directional_attack_state    }  }\",\"extensions\":{},\"operationName\":null,\"variables\":{}}}";

            webSocketHandler.send(message);

        } catch (Exception e) {
            log.error("连接或发送消息时出错", e);
        }
    }

    @PreDestroy
    public void disconnect() {
        try {
            if (webSocketHandler != null && webSocketHandler.isOpen()) {
                webSocketHandler.closeBlocking();
            }
        } catch (Exception e) {
            log.error("断开连接时出错", e);
        }
    }

}
