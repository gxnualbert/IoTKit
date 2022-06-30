package cc.iotkit.ruleengine.action;

import cc.iotkit.model.device.message.ThingModelMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpAction implements Action<HttpService> {

    public static final String TYPE = "http";

    private String type;

    private List<HttpService> services;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<String> execute(ThingModelMessage msg) {
        List<String> results = new ArrayList<>();
        for (HttpService service : services) {
            results.add(service.execute(msg));
        }
        return results;
    }

}