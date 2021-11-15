package com.example.demo.rabbitMqConfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDirect {

	@Value("${com.rabbitmq.queue}")
	String queueName;
	
	@Value("${com.rabbitmq.exchange}")
	String exchange;

	@Value("${com.rabbitmq.routingkey}")
	private String routingkey;
	
	@Bean
	Queue FileDataQueue()
	{
		return new Queue(queueName,false);
	}
     @Bean
	DirectExchange exchange()
	{
		return new DirectExchange(exchange);
	}
	
	@Bean
	Binding fileBinding(Queue fileDataQueue,DirectExchange directExchange)
	{
	return BindingBuilder.bind(fileDataQueue).to(directExchange).with(routingkey);	
	}
/*	 @Bean
	    public MessageConverter converter() {
	        return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean
	    public AmqpTemplate template(ConnectionFactory connectionFactory) {
	        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	        rabbitTemplate.setMessageConverter(converter());
	        return rabbitTemplate;
	    }
	*/
	
}
