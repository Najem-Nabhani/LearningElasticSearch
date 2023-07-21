# ElasticSearch

### Supports Full-text Simple and Powerful Search

## Overview
* ES stores data in indexes, each index has a number of documents identified by an id.
* Indexing is the main thing of ES, data store is not it's specialty, making realtime heavy reads & writes less efficient.

## Storing Data

>POST /{indexname}/_doc/{id}

>>body {key:value}

## Searching for Data

>GET /{indexname}/_doc/{id}

>GET {indexname}/_search?q={queryparams