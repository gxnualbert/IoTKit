/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.data.cache;

import cc.iotkit.common.Constants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoCacheEvict {

    @CacheEvict(value = Constants.CACHE_DEVICE_INFO, key = "#root.method.name+#deviceId")
    public void findByDeviceId(String deviceId) {
    }

    @CacheEvict(value = Constants.CACHE_DEVICE_INFO, key = "#root.method.name+#productKey+#deviceName")
    public void findByProductKeyAndDeviceName(String productKey, String deviceName) {
    }

}
