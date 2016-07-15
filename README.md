AGENDA
-------

1. Introduction (PRESENTATION)
2. Downloading and running an Elasticsearch node
    - Elasticsearch
    - Kibana => Sense
3. Basic concepts (PRESENTATION)
    - cluster & node
    - index
    - type
    - document
    - near realtime (NRT)
4. Basic configuration
    - elasticsearch.yml
    - logging.yml
5. Adding, updating, retrieving and deleting data (CRUD)
    - cluster / node checking
    - create / delete index
    - create document (wit provided id and generated id)
    - retrieve single data
    - retrieve multiple data by search
    - delete data
    - update data
    - indexing bulk data
    - multiple get
6. Query syntax / basic search queries
    - search all data
    - lite search
    - query search: match, match_phrase
    - filter search
    - simple aggregations
7. Basic text analysis theory and practice
    - the '_all' field & searching by example
    - exact values vs full text values
    - inverted index, analysis and analyzers (PRESENTATION)
    - build-in analyzers && custom analyzer definition (tokenizer, token_filter, char_filter)
8. Mappings overview & query/filtering consequences
    - check the mapping of existing index
    - elasticsearch data types (PRESENTATION)
    - customizing field mappings (type, index, analyzer)
    - why use mappings - example
    - testing existing mapping
    - complex core field types (arrays, multilevel objects - object data type, nested object data type)
    - multiple fields mapping (old multifields) - one field for search and for example second field for sorting
9. Searching one more time (accounts.json file)
    - lite query syntax, general query structure
    - filter context, query context, scoring theory (https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html) (PRESENTATION)
    - most important queries (match_all, match, term, terms, etc.) (https://www.elastic.co/guide/en/elasticsearch/guide/current/match-query.html)
    - combining queries (must, must_not, should, filter)
    - pagination, sorting
    - highlighting, boosting
    - querying nested objects
10. Analytical aggregations
    - basics concepts (buckets, metrics)
    - basic examples (buckets, metrics, buckets inside buckets)
    - statistics
    - histograms, date histograms
    - aggregations with query, global bucket, bucket filtering, post filter
    - bucket sorting
    - approximate aggregations
11. JAVA clients
    - transport client vs node client
    - Java clients: the native client, JEST, Spring Elasticsearch (https://www.elastic.co/blog/found-java-clients-for-elasticsearch)
12. Advanced subjects (PRESENTATION + DEMO)
    - shards, replicas
    - modeling data (application side joins, denormalization, nested objects, parent-child relationship)
13. Where to find more resources

Import accounts.json:
curl -XPOST 'localhost:9200/bank/account/_bulk?pretty' --data-binary "@accounts.json"
curl 'localhost:9200/_cat/indices?v'

[WHAT is RELEVANCE](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html)

LINKS:

- Download Elasticsearch & Kibana: [https://www.elastic.co/downloads](https://www.elastic.co/downloads)
- Installing Sense: [https://www.elastic.co/guide/en/sense/current/installing.html](https://www.elastic.co/guide/en/sense/current/installing.html)
- Materials: GitHub [https://github.com/pnowy/elastic](https://github.com/pnowy/elastic)
- Resources: Elasticsearch – Elasticsearch documentation & the definitive guide (use the correct version)
  - [Documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current)
  - [The Definitive Guide](https://www.elastic.co/guide/en/elasticsearch/guide/current)
- New features on [ES 5](https://www.elastic.co/blog/elasticsearch-5-0-0-alpha1-released)
