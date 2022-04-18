package cc.iotkit.ruleengine.action;

import cc.iotkit.common.utils.JsonUtil;
import cc.iotkit.model.device.message.ThingModelMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceAction implements Action<DeviceActionService.Service> {

    public static final String TYPE = "device";

    private String type;

    private List<DeviceActionService.Service> services;

    private DeviceActionService deviceActionService;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<String> execute(ThingModelMessage msg) {
        List<String> results = new ArrayList<>();
        for (DeviceActionService.Service service : services) {
            deviceActionService.invoke(service);
            results.add(JsonUtil.toJsonString(service));
        }
        return results;
    }

}
