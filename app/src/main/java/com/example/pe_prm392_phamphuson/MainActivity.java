package com.example.pe_prm392_phamphuson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pe_prm392_phamphuson.DAO.AppDatabase;
import com.example.pe_prm392_phamphuson.DAO.Country;
import com.example.pe_prm392_phamphuson.DAO.CountryDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_id;
    EditText edt_name;
    EditText edt_rank;
    EditText edt_gdppc;
    EditText edt_year;
    private Toast toast;

    private CountryAdapter countryAdapter;
    private List<Country> countryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_rank = findViewById(R.id.edt_rank);
        edt_gdppc = findViewById(R.id.edt_gdppc);
        edt_year = findViewById(R.id.edt_year);

        toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "CountryDB").build();

        RecyclerView recyclerView = findViewById(R.id.rec_countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CountryAdapter adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);



        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryDao countryDao = db.countryDao();

                Country country = new Country();

                country.setId(Integer.parseInt(edt_id.getText().toString()));
                country.setName(edt_name.getText().toString());
                country.setRank(Integer.parseInt(edt_rank.getText().toString()));
                country.setGdppc(Long.parseLong(edt_gdppc.getText().toString()));
                country.setYear(edt_year.getText().toString());
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        countryDao.insertAll(country);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast.setText("Thêm Sucessfully!");
                                toast.show();
                            }
                        });
                    }
                });
                t.start();
            }
        });


        ((Button)findViewById(R.id.btn_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryDao countryDao = db.countryDao();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String idText = edt_id.getText().toString();
                        String nameText = edt_name.getText().toString();
                        String rankText = edt_rank.getText().toString();
                        String gdppcText = edt_gdppc.getText().toString();
                        String yearText = edt_year.getText().toString();

                        List<Country> countries;

                        if (!idText.isEmpty()) {
                            int id = Integer.parseInt(idText);
                            countries = countryDao.loadAllByIds(new int[]{id});

                            if (!countries.isEmpty()) {
                                Country country = countries.get(0);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Hiển thị thông tin của đối tượng đầu tiên lên các EditText
                                        edt_id.setText(String.valueOf(country.getId()));
                                        edt_name.setText(country.getName());
                                        edt_rank.setText(String.valueOf(country.getRank()));
                                        edt_gdppc.setText(String.valueOf(country.getGdppc()));
                                        edt_year.setText(country.getYear());
                                    }
                                });
                            }
                        } else if (!nameText.isEmpty()) {
                            countries = countryDao.findByName("%" + nameText + "%");
                        } else if (!rankText.isEmpty()) {
                            countries = countryDao.findByRank(Integer.parseInt("%" + rankText + "%"));
                        } else if (!gdppcText.isEmpty()) {
                            countries = countryDao.findByGddppc(Long.parseLong("%" + gdppcText + "%"));
                        } else if (!yearText.isEmpty()) {
                            countries = countryDao.findByYear("%" + yearText + "%");
                        } else {
                            countries = countryDao.getAll();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setCountries(countries);
                            }
                        });
                    }
                }).start();
            }
        });

        ((Button)findViewById(R.id.btn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CountryDao countryDao = db.countryDao();

                        int id = Integer.parseInt(edt_id.getText().toString());
                        Country country = countryDao.findById(id);
                        if (country != null){
                            countryDao.delete(country);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toast.setText("Delete Sucessfully!");
                                    toast.show();
                                }
                            });
                        }
                    }
                });
                t.start();
            }
        });

        ((Button)findViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CountryDao countryDao = db.countryDao();
                        int id = Integer.parseInt(edt_id.getText().toString());
                        Country country = countryDao.findById(id);
                        if (country != null){
                            String newName = edt_name.getText().toString();
                            String newRank = edt_rank.getText().toString();
                            String newGdppc = edt_gdppc.getText().toString();
                            String newYear = edt_year.getText().toString();

                            if (!newName.isEmpty()) {
                                country.setName(newName);
                            }
                            if (!newRank.isEmpty()) {
                                country.setRank(Integer.parseInt(newRank));
                            }
                            if (!newGdppc.isEmpty()) {
                                country.setGdppc(Long.parseLong(newGdppc));
                            }if (!newYear.isEmpty()) {
                                country.setYear(newYear);
                            }

                            countryDao.update(country);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toast.setText("Update Sucessfully!");
                                    toast.show();
                                }
                            });
                        }
                    }
                });
                t.start();
            }
        });
    }
}