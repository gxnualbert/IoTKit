package cc.iotkit.message.listener;

import cc.iotkit.data.IChannelConfigData;
import cc.iotkit.data.IChannelTemplateData;
import cc.iotkit.data.INotifyMessageData;
import cc.iotkit.message.config.VertxManager;
import cc.iotkit.message.enums.MessageTypeEnum;
import cc.iotkit.message.event.MessageEvent;
import cc.iotkit.message.model.MessageSend;
import cc.iotkit.message.model.QyWechatMessage;
import cc.iotkit.model.notify.ChannelConfig;
import cc.iotkit.model.notify.NotifyMessage;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * author: 石恒
 * date: 2023-05-08 15:09
 * description:
 **/

@Slf4j
@Component
@RequiredArgsConstructor
public class QyWechatEventListener implements MessageEventListener {

    private final IChannelConfigData iChannelConfigData;
    private final INotifyMessageData iNotifyMessageData;

    @Override
    @EventListener(condition = "#messageEvent.message.code=='QyWechat'")
    public void doEvent(MessageEvent messageEvent) {

        WebClient client = WebClient.create(VertxManager.INSTANCE.getVertx());
        MessageSend message = messageEvent.getMessage();
        ChannelConfig channelConfig = getChannelConfig(message.getChannelTemplate().getChannelConfigId());
        ChannelConfig.ChannelParam param = channelConfig.getParam();

        String content = getContentFormat(message.getParam(), message.getChannelTemplate().getContent());

        //https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=ff69b535-6594-45c0-9f96-cc66c76cfe1a
        QyWechatMessage qyWechatMessage = QyWechatMessage.builder()
                .msgtype("markdown")
                .markdown(QyWechatMessage.MessageContent.builder().content(content).build())
                .build();

        NotifyMessage notifyMessage = addNotifyMessage(content, message.getMessageType());

        client.getAbs(param.getQyWechatWebhook()).method(HttpMethod.POST).sendJson(qyWechatMessage)
                .onSuccess(response -> {
                    log.info("Received response with status code " + response.statusCode());
                    notifyMessage.setStatus(Boolean.TRUE);
                    iNotifyMessageData.save(notifyMessage);
                })
                .onFailure(err -> log.error("QiYeWechat send message error:" + err.getMessage()));
    }

    @Override
    public ChannelConfig getChannelConfig(String channelConfigId) {
        return iChannelConfigData.findById(channelConfigId);
    }

    @Override
    public NotifyMessage addNotifyMessage(String content, MessageTypeEnum messageType) {
        return iNotifyMessageData.add(NotifyMessage.builder()
                .content(content)
                .messageType(messageType.getCode())
                .status(Boolean.FALSE)
                .build());
    }
}