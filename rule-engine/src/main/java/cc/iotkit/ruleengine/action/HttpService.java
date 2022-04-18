package cc.iotkit.ruleengine.action;

import cc.iotkit.common.utils.JsonUtil;
import cc.iotkit.model.device.message.ThingModelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class HttpService extends ScriptService {
    private String url;

    private final OkHttpClient httpClient = new OkHttpClient();

    @SneakyThrows
    public String execute(ThingModelMessage msg) {
        //执行转换脚本
        Map result = execScript(msg);
        if (result == null) {
            log.warn("execScript result is null");
            return "execScript result is null";
        }

        HttpData httpData = new HttpData();
        BeanUtils.populate(httpData, result);

        //组装http请求
        String url = this.url + httpData.getPath();
        Request.Builder builder = new Request.Builder();
        Map<String, Object> headers = httpData.getHeader();
        if (headers != null) {
            headers.forEach((key, val) -> builder.header(key, val.toString()));
        }
        HttpHeader httpHeader = new HttpHeader();
        BeanUtils.populate(httpHeader, headers);

        builder.url(url);
        RequestBody requestBody;
        requestBody = RequestBody.create(MediaType.get(httpHeader.getContentType()),
                httpData.getBody().toString());

        Request request = builder.method(httpData.getMethod().toUpperCase(), requestBody).build();
        String requestDataStr = JsonUtil.toJsonString(result);
        log.info("send http request:{} ,{}", url, requestDataStr);

        String responseBody = "";
        int responseCode;
        //发送请求
        try (Response response = httpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            responseCode = response.code();
            responseBody = body == null ? "" : body.string();
            log.info("send result,code:{},response:{}", responseCode, responseBody);
        } catch (IOException e) {
            throw new RuntimeException("send request failed", e);
        }

        return String.format("send request,url:%s,method:%s,receive response,code:%s,body:%s",
                url, requestDataStr, responseCode, responseBody);

    }

    @Data
    public static class HttpData {
        private String path;
        private String method;
        private Map<String, Object> header;
        private Object body;
    }

    @Data
    public static class HttpHeader {
        private String contentType;
    }

}
