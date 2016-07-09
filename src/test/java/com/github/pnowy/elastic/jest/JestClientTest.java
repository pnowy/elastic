package com.github.pnowy.elastic.jest;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class JestClientTest {
    private static final Logger log = LoggerFactory.getLogger(JestClientTest.class);

    private JestClient client;

    @Before
    public void setUp() throws Exception {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200")
                                            .multiThreaded(true)
                                            .build());
        client = factory.getObject();
    }

    @Test
    public void indexFromJson() throws Exception {
        String source = "{\"user\":\"kimchy\"}";
        index(source);
    }

    private void index(Object source) throws IOException {
        Index index =  new Index.Builder(source).index("twitter").type("tweet").build();
        client.execute(index);
    }

    @Test
    public void shouldIndexFromBuilder() throws Exception {
        String source = jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", "date")
                .field("message", "trying out Elastic Search")
                .endObject().string();
        index(source);
    }

    @Test
    public void shouldIndexFromMap() throws Exception {
        Map<String, String> source = new LinkedHashMap<>();
        source.put("user", "przemek");
        index(source);
    }

    // more at https://github.com/searchbox-io/Jest/tree/master/jest
}
