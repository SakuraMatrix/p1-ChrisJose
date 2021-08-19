package com.github.ChrisJose.posSystem.Model;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import java.net.URISyntaxException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import reactor.core.Disposable;
import reactor.netty.DisposableServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URISyntaxException;
import io.netty.buffer.ByteBufAllocator;


public class App {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main (String[] args) throws URISyntaxException  {

        /*
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        applicationContext.getBean(DisposableServer.class)
                .onDispose()
                .block();

        applicationContext.close();

         */
    }

    static ByteBuf toByteBuf(Object o){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex) {
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




}
