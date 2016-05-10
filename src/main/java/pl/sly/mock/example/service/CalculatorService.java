package pl.sly.mock.example.service;

import pl.sly.mock.example.model.dto.Calculation;

public interface CalculatorService {
    void invokeCalculation(Calculation calc);
}
