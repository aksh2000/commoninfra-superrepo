package com.example.elasticQuora.ElasticEntity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Data
@Document(indexName = "BusinessElastic")
public class Business {
    @Id
    String businessEmail;
    String businessName;
    String photoUrl;

}
