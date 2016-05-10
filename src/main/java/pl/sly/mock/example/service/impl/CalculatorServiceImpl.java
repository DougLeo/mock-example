package pl.sly.mock.example.service.impl;

import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.Calculation;
import pl.sly.mock.example.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public void invokeCalculation(Calculation calc) {
        if (calc != null) {
            calc.setResult(Long.valueOf(123));
        }
    }
}
