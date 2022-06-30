package cc.iotkit.comps.config;

import cc.iotkit.model.device.message.ThingModelMessage;
import cc.iotkit.mq.MqConsumer;
import cc.iotkit.mq.MqProducer;
import cc.iotkit.mq.vertx.VertxMqConsumer;
import cc.iotkit.mq.vertx.VertxMqProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@Data
public class ComponentConfig {

    @Value("${component.dir:./data/components}")
    private String componentDir;

    @Value("${converter.dir:./data/converters}")
    private String converterDir;

    public Path getComponentFilePath(String comId) {
        return Paths.get(componentDir, comId)
                .toAbsolutePath().normalize();
    }

    public Path getConverterFilePath(String conId) {
        return Paths.get(converterDir, conId)
                .toAbsolutePath().normalize();
    }

    @Bean("objectMapper")
    public ObjectMapper myMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @ConditionalOnMissingBean
    @Bean
    public MqProducer<ThingModelMessage> getThingModelMessageProducer() {
        return new VertxMqProducer<>(ThingModelMessage.class);
    }

    @ConditionalOnMissingBean
    @Bean
    public MqConsumer<ThingModelMessage> getThingModelMessageConsumer() {
        return new VertxMqConsumer<>(ThingModelMessage.class);
    }

}