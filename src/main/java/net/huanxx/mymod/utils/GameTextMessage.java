package net.huanxx.mymod.utils;

import net.minecraft.network.chat.Component;

/**
 * 用于生成要发送的信息的模版类
 */
public class GameTextMessage {
    public static Component info(String message){
        return Component.literal("[").append(Component.literal("INFO").withColor(0x3498db)).append("] ").append(message);
    }

    public static Component error(String message){
        return Component.literal("[").append(Component.literal("ERROR").withColor(0xe74c3c)).append("] ").append(message);
    }

    public static Component warning(String message){
        return Component.literal("[").append(Component.literal("WARNING").withColor(0xf1c40f)).append("] ").append(message);
    }

}
