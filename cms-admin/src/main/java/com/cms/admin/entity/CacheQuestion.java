package com.cms.admin.entity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@RedisHash("CacheQuestion")
@Data
public class CacheQuestion implements Serializable {
    @org.springframework.data.annotation.Id
    public Long quizId;
    public Questions question;
    public Boolean toContinue;
}