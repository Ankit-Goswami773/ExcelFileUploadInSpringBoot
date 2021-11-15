package com.example.demo.rabbitMqConfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Files;
import com.example.demo.entity.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RabbitMqSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;	
	
	@Value("${com.rabbitmq.queue}")
	String queueName;
	
	@Value("${com.rabbitmq.exchange}")
	String exchange;

	@Value("${com.rabbitmq.routingkey}")
	private String routingkey;
	
	
	public ResponseMessage send(Files files) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonMessage = objectMapper.writeValueAsString(files);
	  rabbitTemplate.convertAndSend(exchange,routingkey,jsonMessage);
	  ResponseMessage message=new ResponseMessage();
	  message.setMessage("Publish Message Succesfully");
	  return message;
	}
	
}
