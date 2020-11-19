package com.mygame.stalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class ShopActivity extends AppCompatActivity {
    // массив хранит все кнопки
    final private Button[] buttons = new Button[12];
    // этот массив будет хранить нажатые кнопки в ShopActivity
    private int[] purchasedWeapons = new int[12];
    // кол-во жизней мутанта
    private int hp = 0;
    // текущее оружие(нож по умолчанию)
    private int weapon = 1;
    // общее кол-во убийств
    private int kills = 0;
    // переменная хранит текущий ранг
    private int rang = 0;
    // переменная хранит id текущего мутанта
    private int mutant = 0;
    // переменная хранит текущее кол-во денег
    private int money = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        // инициализируем кнопки
        loadButtons();
        Intent intent = getIntent();
        // подгружаем данные из предыдущей активности
        loadValues(intent);
        // проверяем нажатые кнопки и устанавливаем их видимость
        for (int i = 0; i < purchasedWeapons.length; i++) {
            if(purchasedWeapons[i]==1){
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }
    }
    /**
     * Метод нужен для инициализации кнопок в массиве с кнопками.
     * */
    public void loadButtons(){
        buttons[0] = findViewById(R.id.buttonPm);
        buttons[1] = findViewById(R.id.buttonObrez);
        buttons[2] = findViewById(R.id.buttonAksy);
        buttons[3] = findViewById(R.id.buttonAk);
        buttons[4] = findViewById(R.id.buttonLr);
        buttons[5] = findViewById(R.id.buttonIl);
        buttons[6] = findViewById(R.id.buttonGp);
        buttons[7] = findViewById(R.id.buttonGroza);
        buttons[8] = findViewById(R.id.buttonVss);
        buttons[9] = findViewById(R.id.buttonPkm);
        buttons[10] = findViewById(R.id.buttonGauss);
        buttons[11] = findViewById(R.id.buttonRpg);

    }
    /**
     * Метод восстанавливает значения, переданные из предыдущей
     * активности.
     * */
    public void loadValues(Intent intent){
        hp = intent.getIntExtra("hp",0);
        weapon = intent.getIntExtra("weapon",1);
        kills = intent.getIntExtra("kills",0);
        rang = intent.getIntExtra("rang",0);
        mutant = intent.getIntExtra("mutant",0);
        money = intent.getIntExtra("money",0);
        purchasedWeapons = intent.getIntArrayExtra("purchasedWeapons");
    }
    /**
     * Метод возвращает нас на активность с игрой и передаёт данные
     * при помощи объекта Intent.
     * */
    public void backToGame(View view) {
        Intent intent = new Intent(this,GameWindowActivity.class);
        intent.putExtra("hp",hp);
        intent.putExtra("weapon",weapon);
        intent.putExtra("kills",kills);
        intent.putExtra("rang",rang);
        intent.putExtra("mutant",mutant);
        intent.putExtra("money",money);
        intent.putExtra("purchasedWeapons",purchasedWeapons);
        startActivity(intent);
    }
    /**
     * Метод отвечает за покупку ПМ, проверяя кол-во имеющихся денег.
     * Если ПМ куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyPm(View view) {
        if(money>=20) {
            weapon = 2;
            money -= 20;
            buttons[0].setVisibility(View.INVISIBLE);
            purchasedWeapons[0] = 1;
        }
    }
    /**
     * Метод отвечает за покупку Обреза, проверяя кол-во имеющихся денег.
     * Если Обрез куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyObrez(View view) {
        if(money>=100) {
            weapon = 3;
            money -= 100;
            buttons[1].setVisibility(View.INVISIBLE);
            purchasedWeapons[1] = 1;
        }
    }
    /**
     * Метод отвечает за покупку АКСУ проверяя кол-во имеющихся денег.
     * Если АКСУ куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyAksu(View view) {
        if(money>=500) {
            weapon = 4;
            money -= 500;
            buttons[2].setVisibility(View.INVISIBLE);
            purchasedWeapons[2] = 1;
        }
    }
    /**
     * Метод отвечает за покупку АК, проверяя кол-во имеющихся денег.
     * Если АК куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyAk(View view) {
        if(money>=800) {
            weapon = 5;
            money -= 800;
            buttons[3].setVisibility(View.INVISIBLE);
            purchasedWeapons[3] = 1;
        }
    }
    /**
     * Метод отвечает за покупку ЛР, проверяя кол-во имеющихся денег.
     * Если ЛР куплена, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyLr(View view) {
        if(money>=1500) {
            weapon = 6;
            money -= 1500;
            buttons[4].setVisibility(View.INVISIBLE);
            purchasedWeapons[4] = 1;
        }
    }
    /**
     * Метод отвечает за покупку ИЛ, проверяя кол-во имеющихся денег.
     * Если ИЛ куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyIl(View view) {
        if(money>=2500) {
            weapon = 7;
            money -= 2500;
            buttons[5].setVisibility(View.INVISIBLE);
            purchasedWeapons[5] = 1;
        }
    }
    /**
     * Метод отвечает за покупку GP-37, проверяя кол-во имеющихся денег.
     * Если GP-37 куплена, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyGp37(View view) {
        if(money>=4000) {
            weapon = 8;
            money -= 4000;
            buttons[6].setVisibility(View.INVISIBLE);
            purchasedWeapons[6] = 1;
        }
    }
    /**
     * Метод отвечает за покупку Грозы, проверяя кол-во имеющихся денег.
     * Если Гроза куплена, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyGroza(View view) {
        if(money>=6000) {
            weapon = 9;
            money -= 6000;
            buttons[7].setVisibility(View.INVISIBLE);
            purchasedWeapons[7] = 1;
        }
    }
    /**
     * Метод отвечает за покупку VSS, проверяя кол-во имеющихся денег.
     * Если VSS куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyVss(View view) {
        if(money>=10000) {
            weapon = 10;
            money -= 10000;
            buttons[8].setVisibility(View.INVISIBLE);
            purchasedWeapons[8] = 1;
        }
    }
    /**
     * Метод отвечает за покупку PKM, проверяя кол-во имеющихся денег.
     * Если PKM куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyPkm(View view) {
        if(money>=15000) {
            weapon = 11;
            money -= 15000;
            buttons[9].setVisibility(View.INVISIBLE);
            purchasedWeapons[9] = 1;
        }
    }
    /**
     * Метод отвечает за покупку Гаусса, проверяя кол-во имеющихся денег.
     * Если Гаусс куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyGauss(View view) {
        if(money>=30000) {
            weapon = 12;
            money -= 30000;
            buttons[10].setVisibility(View.INVISIBLE);
            purchasedWeapons[10] = 1;
        }
    }
    /**
     * Метод отвечает за покупку RPG-7, проверяя кол-во имеющихся денег.
     * Если RPG-7 куплен, то меняется значение weapon, вычитаются деньги,
     * кнопка становится невидимой и попадает в массив нажатых кнопок.
     * */
    public void buyRpg(View view) {
        if(money>=50000) {
            weapon = 13;
            money -= 50000;
            buttons[11].setVisibility(View.INVISIBLE);
            purchasedWeapons[11] = 1;
        }
    }
}
