package cc.iotkit.comp;

import cc.iotkit.comp.model.AuthInfo;
import cc.iotkit.comp.model.DeviceState;
import cc.iotkit.comp.model.RegisterInfo;
import cc.iotkit.converter.IConverter;
import lombok.Data;

@Data
public abstract class AbstractDeviceComponent implements IDeviceComponent {

    protected IMessageHandler handler;

    protected IConverter converter;

    protected CompConfig config;

    protected String script;

    @Override
    public void create(CompConfig config) {
        this.config = config;
    }

    @Override
    public void onDeviceRegister(RegisterInfo info) {
    }

    @Override
    public void onDeviceAuth(AuthInfo authInfo) {
    }

    @Override
    public void onDeviceStateChange(DeviceState state) {
    }

    @Override
    public CompConfig getConfig() {
        return config;
    }

    @Override
    public void putScriptEnv(String key, Object value) {
    }
}