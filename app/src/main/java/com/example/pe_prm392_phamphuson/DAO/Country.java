package com.example.pe_prm392_phamphuson.DAO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "rank")
    private int rank;
    @ColumnInfo(name = "gdppc")
    private long gdppc;
    @ColumnInfo(name = "year")
    private String year;

    public Country() {
    }

    public Country(int id, String name, int rank, long gdppc, String year) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.gdppc = gdppc;
        this.year = year;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getGdppc() {
        return gdppc;
    }

    public void setGdppc(long gdppc) {
        this.gdppc = gdppc;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", gdppc=" + gdppc +
                ", year='" + year + '\'' +
                '}';
    }
}
