package com.example.produktmicroservice.conf;
import com.example.produktmicroservice.request.RequestHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {

    @Value("${routing-key.product}")
    private String product_key;
    @Value("${queue.product}")
    private String product_queue;
    @Value("${xchange.name}")
    private String directExchange;


    @Bean
    public RequestHandler requestHandler() {
        return new RequestHandler();
    }

    @Bean
    public Queue productQueue() {
        return new Queue(product_queue);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Binding bindProduct(Queue productQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(productQueue).to(directExchange).with(product_key);
    }

}
