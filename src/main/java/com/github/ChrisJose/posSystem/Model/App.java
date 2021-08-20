package com.github.ChrisJose.posSystem.Model;
import java.net.URISyntaxException;

import com.github.ChrisJose.posSystem.Model.Domain.Customer;
import com.github.ChrisJose.posSystem.Model.Domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.netty.buffer.Unpooled;

import reactor.netty.DisposableServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufAllocator;


public class App {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger log = (Logger) LoggerFactory.getLogger("p1.app");

    public static void main (String[] args) throws URISyntaxException  {

        log.info("Beginning Application");
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        applicationContext.getBean(DisposableServer.class)
                .onDispose()
                .block();
        applicationContext.close();
        log.info("Ending Application");

    }

    static ByteBuf toByteBuf(Object o){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex)  {
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }

    public static ByteBuf intToByteBuff(Object o) {
        try {
            return Unpooled.buffer()
                    .writeBytes(OBJECT_MAPPER.writerFor(Integer.class).writeValueAsBytes(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer parseCustomer(String str){
        Customer customer = null;
        try{
            customer = OBJECT_MAPPER.readValue(str, Customer.class);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return customer;
    }

    public static Order parseOrder(String str){
        Order order = null;
        try{
            order = OBJECT_MAPPER.readValue(str, Order.class);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return order;
    }




}
