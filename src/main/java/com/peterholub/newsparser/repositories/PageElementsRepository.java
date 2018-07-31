package com.peterholub.newsparser.repositories;

import com.peterholub.newsparser.domains.PageElements;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PageElementsRepository extends JpaRepository<PageElements, Long> {

    @Override
    List<PageElements> findAll();

    @Override
    <S extends PageElements> List<S> saveAll(Iterable<S> iterable);

    List<PageElements>findAllByParsingDateTime(LocalDateTime parsingDateTime);


}
