package com.nikola.exampleactivities.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nikola.exampleactivities.db.model.Category;
import com.nikola.exampleactivities.db.model.Meal;

import java.sql.SQLException;

/**
 * Created by Dzoni on 6/2/2017.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "ormlite.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Meal,Integer> mMealDao = null;
    private Dao<Category,Integer> mCategoryDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Meal.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Category.class,true);
            TableUtils.dropTable(connectionSource, Meal.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //jedan Dao objekat sa kojim komuniciramo. Ukoliko imamo vise tabela
    //potrebno je napraviti Dao objekat za svaku tabelu
    public Dao<Meal, Integer> getmMealDao() throws SQLException {
        if (mMealDao == null) {
            mMealDao = getDao(Meal.class);
        }

        return mMealDao;
    }


    public Dao<Category, Integer> getmCategoryDao() throws SQLException {
        if (mCategoryDao == null) {
            mCategoryDao = getDao(Category.class);
        }

        return mCategoryDao;
    }

    // Free resources when closing database
    @Override
    public void close() {
        super.close();

        mMealDao = null;
        mCategoryDao = null;
    }
}
