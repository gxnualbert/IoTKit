/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.ruleengine.action;

import cc.iotkit.common.utils.JsonUtil;
import cc.iotkit.data.IDeviceInfoData;
import cc.iotkit.model.device.DeviceInfo;
import cc.iotkit.model.device.message.ThingModelMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngineManager;
import java.util.Map;

@Slf4j
@Data
public class ScriptService {

    private final NashornScriptEngine engine = (NashornScriptEngine) (new ScriptEngineManager())
            .getEngineByName("nashorn");

    private String script;

    private ScriptObjectMirror scriptObject;

    private IDeviceInfoData deviceInfoData;

    public Map execScript(ThingModelMessage msg) {
        try {
            if (scriptObject == null) {
                scriptObject = (ScriptObjectMirror) engine.eval("new (function(){" + script + "})()");
            }
            //取设备信息
            DeviceInfo deviceInfo = deviceInfoData.findByDeviceId(msg.getDeviceId());

            //执行转换脚本
            ScriptObjectMirror result = (ScriptObjectMirror) engine
                    .invokeMethod(scriptObject, "translate", msg, deviceInfo);

            if (result == null) {
                return null;
            }

            Object objResult = JsonUtil.toObject(result);
            if (!(objResult instanceof Map)) {
                return null;
            }
            return (Map) objResult;
        } catch (Throwable e) {
            log.error("run script error", e);
            return null;
        }
    }
}
