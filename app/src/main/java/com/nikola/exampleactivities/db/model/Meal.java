package com.nikola.exampleactivities.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Dzoni on 6/2/2017.
 */

@DatabaseTable(tableName = Meal.TABLE_NAME_MEAL)
public class Meal {

    public static final String TABLE_NAME_MEAL = "meals";
    public static final String MEAL_ID = "id";
    public static final String MEAL_NAME = "name";
    public static final String MEAL_DESCRIPTION = "description";
    public static final String MEAL_CALORIES = "calories";
    public static final String MEAL_PRICE = "price";
    public static final String MEAL_IMAGE = "image";
    public static final String MEAL_CATEGORY = "category";

    @DatabaseField(columnName = MEAL_ID, generatedId = true)
    private int mID;
    @DatabaseField(columnName = MEAL_NAME)
    private String mName;
    @DatabaseField(columnName = MEAL_DESCRIPTION)
    private String mDescription;
    @DatabaseField(columnName = MEAL_PRICE)
    double mPrice;
    @DatabaseField(columnName = MEAL_IMAGE)
    private String mImage;
    @DatabaseField(columnName = MEAL_CALORIES)
    double mCalories;

//    @DatabaseField(columnName = MEAL_CATEGORY)
//    private String mCategory;
    @DatabaseField(columnName = MEAL_CATEGORY, foreign = true, foreignAutoRefresh = true)
    private Category mCategory;


    // ORMLite needs empty constructor in classes that describes database tables
    public Meal(){}

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getmCalories() {
        return mCalories;
    }

    public void setmCalories(double mCalories) {
        this.mCalories = mCalories;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    @Override
    public String toString() {
        return mName;
    }

}
