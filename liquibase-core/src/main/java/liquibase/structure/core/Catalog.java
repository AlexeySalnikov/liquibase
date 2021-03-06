package liquibase.structure.core;

import liquibase.structure.AbstractDatabaseObject;
import liquibase.structure.DatabaseObject;

import java.util.*;

public class Catalog extends AbstractDatabaseObject {

    public Catalog() {
        setAttribute("objects",  new HashMap<Class<? extends DatabaseObject>, Set<DatabaseObject>>());
    }

    public Catalog(String name) {
        this();
        setAttribute("name", name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public DatabaseObject[] getContainingObjects() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return getAttribute("name", String.class);
    }

    @Override
    public Catalog setName(String name) {
        setAttribute("name", name);
        return this;
    }

    protected Map<Class<? extends DatabaseObject>, Set<DatabaseObject>> getObjects() {
        return getAttribute("objects", Map.class);
    }

    public <DatabaseObjectType extends DatabaseObject> List<DatabaseObjectType> getDatabaseObjects(Class<DatabaseObjectType> type) {
        Set<DatabaseObjectType> databaseObjects = (Set<DatabaseObjectType>) getObjects().get(type);
        if (databaseObjects == null) {
            return new ArrayList<DatabaseObjectType>();
        }
        return new ArrayList<DatabaseObjectType>(databaseObjects);
    }

    public void addDatabaseObject(DatabaseObject databaseObject) {
        if (databaseObject == null) {
            return;
        }
        Set<DatabaseObject> objects = this.getObjects().get(databaseObject.getClass());
        if (objects == null) {
            objects = new HashSet<DatabaseObject>();
            this.getObjects().put(databaseObject.getClass(), objects);
        }
        objects.add(databaseObject);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (getName() != null ? !getName().equals(catalog.getName()) : catalog.getName() != null) return false;

        return true;
    }



    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
