package persistence;

import org.json.JSONObject;

public interface Writable {
    public JSONObject toJson();
}
