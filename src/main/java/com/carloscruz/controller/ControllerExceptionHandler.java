package com.carloscruz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.carloscruz.dto.MessageDTO;
import com.carloscruz.enumerator.TipoMensagemEnum;



/**
 * @author carlos.duarte
 * Classe responsável por tratar as exceções e retornar menságens amigáveis para o usuário
 * @since 04/03/2016
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	@Autowired
	  private MessageSource msgSource;
	  
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public List<MessageDTO> processValidationError(MethodArgumentNotValidException ex) {
		  BindingResult result = ex.getBindingResult();
		  List<FieldError> errors = result.getFieldErrors();
		  return processFieldError(errors);
	  }
	  
	  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public List<MessageDTO> processMediaTypeError(HttpMediaTypeNotSupportedException ex) {	
		  List<MessageDTO> listMsg = new ArrayList<MessageDTO>();
		  MessageDTO msg = new MessageDTO(TipoMensagemEnum.ERROR, "Erro ao processar o tipo do dado, favor contactar o suporte!");  
		  listMsg.add(msg);
		  return listMsg;
	  }
	  
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public List<MessageDTO> processConstraintViolationExceptionError(DataIntegrityViolationException ex) {	
		  List<MessageDTO> listMsg = new ArrayList<MessageDTO>();
		  MessageDTO msg;
		  if(ex.getCause().getClass().equals(ConstraintViolationException.class)) {
			  msg = new MessageDTO(TipoMensagemEnum.ERROR, "Já existe registro cadastrado no banco com essas informações");
		  } else {
			  msg = new MessageDTO(TipoMensagemEnum.ERROR, ex.getCause().getCause().getMessage());
		  }
		  listMsg.add(msg);
		  return listMsg;
	  }
	  
	  @ExceptionHandler(HttpMessageNotWritableException.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public List<MessageDTO> processHttpMessageNotWritableExceptionError(HttpMessageNotWritableException ex) {
		  List<MessageDTO> listMsg = new ArrayList<MessageDTO>();
		  MessageDTO msg = new MessageDTO(TipoMensagemEnum.ERROR, ex.getCause().getMessage());
		  listMsg.add(msg);
		  return listMsg;
	  }
	  
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)	  
	  @ResponseBody
	  public List<MessageDTO> processExceptionGenericError(Exception ex) {
		  List<MessageDTO> listMsg = new ArrayList<MessageDTO>();
		  MessageDTO msg = new MessageDTO(TipoMensagemEnum.ERROR, ex.getMessage() != null ? ex.getMessage() : "Erro, favor contactar o suporte!");
		  listMsg.add(msg);
		  return listMsg;
	  }
	
	  private List<MessageDTO> processFieldError(List<FieldError> errors) {
		  List<MessageDTO> listMsg = new ArrayList<MessageDTO>();		  
		  if (errors != null) {
			  Locale currentLocale = LocaleContextHolder.getLocale();
			  for (FieldError error : errors) {
				  String msg = "";
				  msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
				  listMsg.add(new MessageDTO(TipoMensagemEnum.ERROR, msg));
			  }
		  }
		  return listMsg;
	  }
}
