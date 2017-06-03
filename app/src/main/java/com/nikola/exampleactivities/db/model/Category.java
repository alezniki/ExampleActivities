package com.nikola.exampleactivities.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nikola on 6/3/17.
 */

@DatabaseTable(tableName = Category.TABLE_NAME_CATEGORY)
public class Category {

    public static final String TABLE_NAME_CATEGORY = "categories";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name";

    @DatabaseField(columnName = CATEGORY_ID,generatedId = true)
    private int id;
    @DatabaseField(columnName = CATEGORY_NAME)
    private String name;

    public Category(){}

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
