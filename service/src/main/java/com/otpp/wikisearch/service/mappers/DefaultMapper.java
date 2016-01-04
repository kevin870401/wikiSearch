package com.otpp.wikisearch.service.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class DefaultMapper<DomainClass, DatabaseClass> {

    private final MapperFactory defaultMapperFactory;

    @Autowired
    public DefaultMapper(MapperFactory mapperFactory) {
        this.defaultMapperFactory = mapperFactory;
    }

    public List<DomainClass> mapDbToDomain(List<DatabaseClass> dbObjects) {
        return getMapperFacade().mapAsList(dbObjects, domainClass());
    }

    public DomainClass mapDbToDomain(DatabaseClass dbObject) {
        return getMapperFacade().map(dbObject, domainClass());
    }

    public void mapDbToExistingDomain(DatabaseClass dbObject, DomainClass domainObject) {
        if (domainObject != null && dbObject != null) {
            getMapperFacade().map(dbObject, domainObject);
        }
    }

    public List<DatabaseClass> mapDomainToDb(List<DomainClass> domainObjects) {
        return getMapperFacade().mapAsList(domainObjects, dbClass());
    }

    public DatabaseClass mapDomainToDb(DomainClass domainObject) {
        return getMapperFacade().map(domainObject, dbClass());
    }

    public void mapDomainToDb(DomainClass domainObject, DatabaseClass dbObject) {
        getMapperFacade().map(domainObject, dbObject);
    }

    /*
     * Subclasses that need to change the mapper factory configuration can override this method.
     * Common use cases for overriding are when most of the fields are copied by default but there are some exceptions,
     * mappers can still use the default mapper but need to specify additionally the mappings for the exceptional
     * fields.
     */
    protected void configureMapperFactory() {
        getDefaultMapperFactory().classMap(dbClass(), domainClass())
                                 .byDefault()
                                 .register();
    }

    protected MapperFactory getDefaultMapperFactory() {
        return defaultMapperFactory;
    }

    protected abstract Class<DatabaseClass> dbClass();

    protected abstract Class<DomainClass> domainClass();

    private MapperFacade getMapperFacade() {
        return getDefaultMapperFactory().getMapperFacade();
    }

}
