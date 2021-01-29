package com.example.elasticQuora.ElasticRepository;

import com.example.elasticQuora.ElasticEntity.Business;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends ElasticsearchRepository<Business,String> {

}
