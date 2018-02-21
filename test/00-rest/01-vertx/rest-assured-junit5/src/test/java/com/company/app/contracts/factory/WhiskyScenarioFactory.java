package com.company.app.contracts.factory;

import com.company.app.contracts.WhiskyScenario;

// factory acts as dependency injection that controls how the scenario validator should be built
public interface WhiskyScenarioFactory {
  WhiskyScenario create();
}
