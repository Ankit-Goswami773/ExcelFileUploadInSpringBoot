package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.entity.ResponseMessage;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(FileEmptyException.class)
	public ResponseEntity<ResponseMessage> fileEmptyException(FileEmptyException emptyException)
	{
		ResponseMessage message=new ResponseMessage();
		message.setMessage(emptyException.getMessage());
		return new ResponseEntity<ResponseMessage>(message,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(FileExtensionException.class)
	public ResponseEntity<ResponseMessage> fileExtensionException(FileExtensionException emptyException)
	{
		ResponseMessage message=new ResponseMessage();
		message.setMessage(emptyException.getMessage());
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HeaderNotMatchedException.class)
	public ResponseEntity<ResponseMessage> headerCheckException(HeaderNotMatchedException checkException)
	{
		ResponseMessage message=new ResponseMessage();
		message.setMessage(checkException.getMessage());
		return new ResponseEntity<ResponseMessage>(message,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmptyRecordException.class)
	public ResponseEntity<?> headerCheckException(EmptyRecordException emptyRecordException)
	{
		ResponseMessage message=new ResponseMessage();
		message.setMessage(emptyRecordException.getMessage());
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
}
