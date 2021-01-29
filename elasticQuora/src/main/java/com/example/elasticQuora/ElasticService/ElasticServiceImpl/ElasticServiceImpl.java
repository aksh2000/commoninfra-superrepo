package com.example.elasticQuora.ElasticService.ElasticServiceImpl;



import com.example.elasticQuora.ElasticEntity.Business;
import com.example.elasticQuora.ElasticRepository.BusinessRepository;
import com.example.elasticQuora.ElasticService.ElasticService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticServiceImpl implements ElasticService
{


    @Autowired
    BusinessRepository businessRepository;


    @Override
    public List<Business> generalSearchUser(String name) {
        List<Business> searchResult=new ArrayList<>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(name)
                                .lenient(true)
                                .field("businessName"))
                .should(
                        QueryBuilders.queryStringQuery("*" + name + "*")
                                .lenient(true)
                                .field("businessName"));
        Query build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();
        return businessRepository.search(build).getContent();
    }


    public void deleteById(String id) {
        businessRepository.deleteById(id);
    }


    public List<Business> findAll() {
        Iterable<Business> employeeIterable=businessRepository.findAll();
        List<Business> employeeList=new ArrayList<>();
        employeeIterable.forEach(employeeList::add);
        return employeeList;

    }

    public Business save(Business employee) {
        return businessRepository.save(employee);
    }


    public Business findById(String id){
        return businessRepository.findById(id).get();

    }
}
