package br.com.ufsm.orderapi.config.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ErrorDto {
	
	private String field;
	private String error;
	
}
