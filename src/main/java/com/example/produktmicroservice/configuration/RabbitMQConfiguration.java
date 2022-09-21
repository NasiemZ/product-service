package com.example.produktmicroservice.configuration;
import com.example.produktmicroservice.consumer.ProductConsumer;
import com.example.produktmicroservice.service.ProductService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${queue.price}")
    private String priceQueue;
    @Value("${routing-key.price}")
    private String price_key;

    @Value("${routing-key.product}")
    private String product_key;
    @Value("${queue.product}")
    private String product_queue;

    @Value("${xchange.name}")
    private String directExchange;

    private RabbitTemplate rabbitTemplate;



    @Bean
    public ProductConsumer requestHandler() {
        return new ProductConsumer();
    }

    @Bean Queue priceQueue() {
        return new Queue(priceQueue);
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
    public Binding bindPrice(Queue priceQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(priceQueue).to(directExchange).with(price_key);
    }

    @Bean
    public Binding bindProduct(Queue productQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(productQueue).to(directExchange).with(product_key);
    }

}
