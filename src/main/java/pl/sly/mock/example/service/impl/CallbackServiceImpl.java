package pl.sly.mock.example.service.impl;

import org.springframework.stereotype.Service;
import pl.sly.mock.example.model.dto.Callback;
import pl.sly.mock.example.service.CallbackService;

@Service
public class CallbackServiceImpl implements CallbackService {
    public enum State {SUCCESS, FAIL}

    @Override
    public void invokeCallback(Callback callback) {

    }
}
