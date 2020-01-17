package com.poc.websocket.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

public class RmeSessionChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);
        String token = null;
        if (Objects.nonNull(multiValueMap.getFirst("auth"))) {
            token = multiValueMap.getFirst("auth");
        }
//        for (Map.Entry<String, List<String>> head : multiValueMap.entrySet()) {
//            if (head.getKey().equals("auth")) {
//                token = head.getValue().toString();
//            }
//        }
        System.out.println(token);
        return message;
    }
}
