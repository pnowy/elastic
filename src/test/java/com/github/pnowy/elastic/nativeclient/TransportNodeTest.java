package com.github.pnowy.elastic.nativeclient;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class TransportNodeTest {
    private static final Logger log = LoggerFactory.getLogger(TransportNodeTest.class);

    private Client client;

    @Before
    public void setUp() throws Exception {
        TransportClient transportClient = TransportClient.builder().settings(Settings.builder().put("cluster.name", "cp")).build();
        transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        this.client = transportClient;
    }

    @Test
    public void shouldDisplayAllIndices() throws Exception {
        ImmutableOpenMap<String, IndexMetaData> indices = client.admin().cluster()
                .prepareState().execute()
                .actionGet().getState()
                .getMetaData().getIndices();
        log.info("indices: {}", indices.keys());
    }

}
