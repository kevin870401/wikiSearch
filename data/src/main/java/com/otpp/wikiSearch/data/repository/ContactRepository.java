package com.otpp.wikiSearch.data.repository;

import com.otpp.wikiSearch.data.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepository extends SolrCrudRepository<Contact, String>, WikiRepositoryCustom {

    Page<Contact> findByTitleIn(@Param("title") Collection<String> title, Pageable page);

    Page<Contact> findBySalutationIs(@Param("salutation") Collection<String> salutation, Pageable page);

    Page<Contact> findByLastnameIs(@Param("lastname") Collection<String> lastname, Pageable page);

    long countByTitleIs(@Param("title") Collection<String> titleFragments);

    Page<Contact> findByTitleStartsWith(@Param("title") Collection<String> titleFragments, Pageable pagebale);

    @Facet(fields = {"salutation"}, minCount = 1)
    @Query(value = "*:*")
    FacetPage<Contact> getSalutationFacetSortedByContactCount(Pageable page);

    Page<Contact> findByAddressContaining(@Param("address") Collection<String> textFragments, Pageable pagebale);

}
