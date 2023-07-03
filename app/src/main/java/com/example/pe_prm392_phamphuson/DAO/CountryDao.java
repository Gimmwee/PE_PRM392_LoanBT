package com.example.pe_prm392_phamphuson.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDao {
    @Query("SELECT * FROM country")
    List<Country> getAll();

    @Query("SELECT * FROM country WHERE id IN (:id)")
    List<Country> loadAllByIds(int[] id);

    @Query("SELECT * FROM country WHERE name LIKE :name")
    List<Country> findByName(String name);

    @Query("SELECT * FROM country WHERE rank LIKE :rank")
    List<Country> findByRank(int rank);

    @Query("SELECT * FROM country WHERE gdppc LIKE :gdppc")
    List<Country> findByGddppc(long gdppc);

    @Query("SELECT * FROM country WHERE year LIKE :year")
    List<Country> findByYear(String year);

    @Query("SELECT * FROM country WHERE id = :id")
    Country findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Country... countries);

    @Delete
    void delete(Country countries);

    @Update
    void update(Country countries);
}

