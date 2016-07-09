package com.github.pnowy.elastic.spring;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApp.class)
public class SpringElasticsearchTest {
    private static final Logger log = LoggerFactory.getLogger(SpringElasticsearchTest.class);

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void shouldCreateIndex() throws Exception {
        boolean index = elasticsearchTemplate.createIndex(Person.class);
        assert index;
    }

    @Test
    public void shouldIndexExampleData() throws Exception {
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(Person.of("Jan", 23L)).build();
        String index = elasticsearchTemplate.index(indexQuery);
        log.info("index response: {}", index);
    }

    @Test
    public void shouldSearchData() throws Exception {
        NativeSearchQueryBuilder nc = new NativeSearchQueryBuilder();
        nc.withIndices("db");
        nc.withTypes("person");
        nc.withQuery(QueryBuilders.matchAllQuery());
        // QueryBuilders.wrapperQuery(queryString);

        List<Person> list = elasticsearchTemplate.query(nc.build(), response -> {
            List<Person> persons = new LinkedList<>();
            response.getHits().forEach(hit -> {
                                           Map<String, Object> source = hit.getSource();
                                           String name = String.valueOf(source.get("name"));
                                           Long age = Long.valueOf((Integer) source.get("age"));
                                           persons.add(Person.of(name, age));
                                       }
            );
            return persons;
        });
        log.info("people: {}", list);
    }

    @Data(staticConstructor = "of")
    @Document(indexName = "db", type = "person")
    static class Person {
        private final String name;
        private final Long age;
    }

}
