
# ElasticSearch + Spring
This project was created for learning purposes. 
- Time spent learning via this project: 2 days as of 23/7/23

## Project Views
![Screenshot from 2023-07-23 02-38-13](https://github.com/Najem-Nabhani/LearningElasticSearch/assets/42846512/71e2ada8-1d81-4d8c-a41f-e94b77dbd76b)

![Screenshot from 2023-07-23 02-37-15](https://github.com/Najem-Nabhani/LearningElasticSearch/assets/42846512/02ccb132-ae27-4325-8594-aaac9a8cf00d)

![Screenshot from 2023-07-23 02-38-20](https://github.com/Najem-Nabhani/LearningElasticSearch/assets/42846512/e178ca1e-96d2-4716-a059-ae609340b23c)

## Overview
* ES stores data in indexes, each index has a number of documents identified by an id.
* Supports Full-text Simple and Powerful Search
* Indexing is the main thing of ES, data store is not it's specialty, making realtime heavy reads & writes less efficient.

## Storing Data

>POST /{indexname}/_doc/{id}

>>body {key:value}

## Searching for Data

>GET /{indexname}/_doc/{id}

>GET {indexname}/_search?q={queryparams

## Resources
As I was learning I found many resources to contain legacy/deprecated info. I found the following main resources to be most useful:
1. https://www.elastic.co/guide/en/elasticsearch/reference/8.8/full-text-queries.html
1. https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#repositories.custom-implementations

