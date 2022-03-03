package cc.iotkit.common.utils;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class UniqueIdUtil {

    private static final int MACHINE_ID = RandomUtils.nextInt(10, 99);

    private static final AtomicInteger SEQUENCE = new AtomicInteger(1000);

    public static String newRequestId() {
        return newUniqueId("RID");
    }

    public static String newUniqueId(String prefix) {
        int id = SEQUENCE.getAndIncrement();
        if (id >= 5000) {
            SEQUENCE.set(1000);
        }

        return prefix + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + id + MACHINE_ID;
    }

}
