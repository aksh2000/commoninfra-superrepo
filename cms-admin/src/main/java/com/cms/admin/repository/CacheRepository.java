package com.cms.admin.repository;
import com.cms.admin.entity.CacheQuestion;
import org.springframework.data.repository.CrudRepository;
public interface CacheRepository extends CrudRepository<CacheQuestion,Long> {
}