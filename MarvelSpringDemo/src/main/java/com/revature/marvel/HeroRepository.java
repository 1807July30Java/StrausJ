package com.revature.marvel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "hero", path = "hero")
public interface HeroRepository extends PagingAndSortingRepository<Hero, Integer> {
    List<Hero> findByLastName(@Param("name") String name);
}
