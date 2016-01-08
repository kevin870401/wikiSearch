package com.otpp.wikiSearch.data.entity;

import lombok.Data;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@Data
@SolrDocument(solrCoreName = "employerinfo")
public class Contact {

    private
    @Indexed("id")
    String id;

    private
    @Indexed("lastname")
    String lastname;
    private
    @Indexed("firstname")
    String firstname;
    private
    @Indexed("title")
    String title;
    private
    @Indexed("irn")
    String irn;
    private
    @Indexed("salutation")
    String salutation;

    private
    @Indexed("address")
    String address;

    private
    @Indexed("phone")
    List<String> phone;

    private
    @Indexed("emailAddress")
    List<String> emailAddress;
}
