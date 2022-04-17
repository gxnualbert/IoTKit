package cc.iotkit.dao;

import cc.iotkit.model.Paging;
import cc.iotkit.model.device.message.ThingModelMessage;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class ThingModelMessageDao {

    @Autowired
    private ElasticsearchRestTemplate template;

    public Paging<ThingModelMessage> findByTypeAndIdentifier(String deviceId, String type,
                                                             String identifier,
                                                             int page, int size) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.termQuery("deviceId", deviceId));
        if (StringUtils.isNotBlank(type)) {
            builder.must(QueryBuilders.termQuery("type", type));
        }
        if (StringUtils.isNotBlank(identifier)) {
            builder.must(QueryBuilders.matchPhraseQuery("identifier", identifier));
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(builder)
                .withPageable(PageRequest.of(page-1, size, Sort.by(Sort.Order.desc("time"))))
                .build();
        SearchHits<ThingModelMessage> result = template.search(query, ThingModelMessage.class);
        return new Paging<>(result.getTotalHits(), result.getSearchHits().stream()
                .map(SearchHit::getContent).collect(Collectors.toList()));
    }

}
