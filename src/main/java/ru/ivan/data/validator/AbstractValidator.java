package ru.ivan.data.validator;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractValidator<T> {
  @NotNull
  protected String msg;
   protected abstract boolean validation(T t);
  public final boolean validate(T t) throws IllegalArgumentException{
    if(!validation(t)){throwValidateError(this.msg);}
    return true;
  }
  protected void throwValidateError(String msg){
    throw new IllegalArgumentException(msg);
  }
}
