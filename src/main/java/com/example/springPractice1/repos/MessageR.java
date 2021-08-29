package com.example.springPractice1.repos;

import com.example.springPractice1.domain.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessageR extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
