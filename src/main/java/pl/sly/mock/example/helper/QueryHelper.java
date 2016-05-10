package pl.sly.mock.example.helper;

import org.springframework.stereotype.Component;
import pl.sly.mock.example.helper.DatabaseManager;

import java.util.UUID;

@Component
public class QueryHelper {
    public String invokeQuery() {
        if (DatabaseManager.getConnectionUrl() != null) {
            return UUID.randomUUID().toString();
        }

        return null;
    }

    public void callStaticReload() {
        DatabaseManager.reload();
    }
}
