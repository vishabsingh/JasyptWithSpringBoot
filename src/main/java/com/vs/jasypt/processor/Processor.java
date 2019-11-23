package com.vs.jasypt.processor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Deque;

@AllArgsConstructor
@NoArgsConstructor
public class Processor<T> {

	protected  Object data;

	public Object getValue(){
		return data;
	}

	public Object getValue(final T context){
		return data == null || data instanceof Processor<?> ? context : getValue();
	}

	public Object getValue(final Deque<Object> contextData){
		if(data instanceof Processor<?>){
			//return getValue((T) ((Processor<T>)data).getValue(contextData));
			return getValue((T) ((Processor) data).getValue(contextData));
		}
		return getValue((T) contextData.getLast());
	}
}
