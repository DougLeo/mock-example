package pl.sly.mock.example.data;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataHelper {
    public String invokeQuery() {
        if (DataManager.getDataUrl() != null) {
            return UUID.randomUUID().toString();
        }

        return null;
    }

    public void invokeReload() {
        DataManager.reload();
    }
}
