package com.github.pnowy.elastic.nativeclient;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.NodeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientNodeTest {
    private static final Logger log = LoggerFactory.getLogger(ClientNodeTest.class);
    private static final String INDEX = "db";

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = NodeBuilder.nodeBuilder()
                .client(true)
                .clusterName("cp")
                .settings(Settings.settingsBuilder().put("path.home", System.getProperty("user.dir")))
                .node()
                .client();
    }

    @Test
    public void shouldDisplayAllIndices() throws Exception {
        ImmutableOpenMap<String, IndexMetaData> indices = client.admin().cluster()
                .prepareState().execute()
                .actionGet().getState()
                .getMetaData().getIndices();
        log.info("indices: {}", indices.keys());
    }

    @Test
    public void shouldCreateIndex() throws Exception {
        boolean indexExists = client.admin().indices().prepareExists(INDEX).execute().actionGet().isExists();
        if (indexExists) {
            log.info("index existed, deleting");
            client.admin().indices().prepareDelete(INDEX).execute().actionGet();
        }
        CreateIndexResponse result = client.admin().indices().prepareCreate(INDEX).execute().actionGet();
        log.info("index created, result: {}", result.getHeaders());
    }

    @Test
    public void shouldSearchSomething() throws Exception {
        SearchResponse allHits = client.prepareSearch("db").setTypes("person")
                .setQuery(QueryBuilders.matchAllQuery())
                .execute().actionGet();
        log.info("get total hits: {}", allHits.getHits().getTotalHits());
    }
}
