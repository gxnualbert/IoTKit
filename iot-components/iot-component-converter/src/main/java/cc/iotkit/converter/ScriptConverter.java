/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.converter;

import cc.iotkit.common.thing.ThingService;
import cc.iotkit.common.utils.JsonUtil;
import cc.iotkit.model.device.message.ThingModelMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

@Slf4j
@Data
public class ScriptConverter implements IConverter {
    private final NashornScriptEngine engine = (NashornScriptEngine) (new ScriptEngineManager()).getEngineByName("nashorn");

    private Object scriptObj;

    public void setScript(String script) {
        try {
            scriptObj = engine.eval(String.format("new (function () {\n%s})()", script));
        } catch (ScriptException e) {
            log.error("eval converter script error", e);
        }
    }

    public ThingModelMessage decode(DeviceMessage msg) {
        try {
            Object rst = engine.invokeMethod(scriptObj, "decode", msg);
            if (rst instanceof ThingModelMessage) {
                return (ThingModelMessage) rst;
            }

            ScriptObjectMirror result = (ScriptObjectMirror) rst;
            ThingModelMessage modelMessage = new ThingModelMessage();
            BeanUtils.populate(modelMessage, result);
            return modelMessage;
        } catch (Throwable e) {
            log.error("execute decode script error", e);
        }
        return null;
    }

    @Override
    public DeviceMessage encode(ThingService<?> service, Device device) {
        try {
            ScriptObjectMirror result = (ScriptObjectMirror) engine.invokeMethod(scriptObj, "encode", service, device);
            Map map = (Map) JsonUtil.toObject(result);
            DeviceMessage message = new DeviceMessage();
            BeanUtils.populate(message, map);
            return message;
        } catch (Throwable e) {
            log.error("execute encode script error", e);
        }
        return null;
    }

    @Override
    public void putScriptEnv(String key, Object value) {
        engine.put(key, value);
    }
}
