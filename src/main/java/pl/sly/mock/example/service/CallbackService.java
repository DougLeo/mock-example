package pl.sly.mock.example.service;

import pl.sly.mock.example.model.dto.Callback;

public interface CallbackService {
    void invokeCallback(Callback callback);
}
