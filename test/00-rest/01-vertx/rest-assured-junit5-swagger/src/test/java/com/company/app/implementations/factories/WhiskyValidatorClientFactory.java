package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factories.WhiskyClientFactory;
import com.company.app.implementations.WhiskyValidator;

public class WhiskyValidatorClientFactory implements WhiskyClientFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyValidator(new WhiskyClientClientFactory().create());
  }
}
