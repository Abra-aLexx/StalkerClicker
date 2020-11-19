package com.mygame.stalker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс отвечает за саму игру StalkerGame.
 * В этом классе запускается два второстепенных потока.
 * Один из них отвечает за смену монстров, а другой
 * отвечает за смену ранга.
 * */
public class GameWindowActivity extends AppCompatActivity {
    // кол-во жизней мутанта
   private int hp = 0;
   // текущее оружие(нож по умолчанию)
   private int weapon = 1;
   // общее кол-во убийств
   private int kills = -1;
   // Этот ImageView в дальнейшем будет содержать картинку монстра
   private ImageView image;
   // Этот TextView будет отображать количество жизней у монстра
   private TextView textHp;
    // Этот TextView будет отображать общее количество денег
   private TextView textMoney;
   // поле health в дальнейшем будет отображаться в textHp
   private String health;
    // поле dollar в дальнейшем будет отображаться в textMoney
   private String dollar;
    // этот массив будет хранить нажатые кнопки в ShopActivity
   private int[] purchasedWeapons = new int[12];
   // переменная хранит текущий ранг
   private int rang = 0;
   // переменная хранит id текущего мутанта
   private int mutant = 0;
   // переменная хранит текущее кол-во денег
   private int money = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_window);
        // заполняем массив 0, чтобы не был null
        Arrays.fill(purchasedWeapons, 0);
        // здесь инициализируем ImageView и TextView
        image = findViewById(R.id.imageMutant);
        textHp = findViewById(R.id.textHp);
        textMoney = findViewById(R.id.textMoney);
        health = getString(R.string.health);
        dollar = getString(R.string.money);
            Intent intent = getIntent();
            reload(intent);
        loadImages();
        changeRang();
    }
    /**
     * Метод сохраняет значения при изменении конфигурации
     * */
/**
 * Метод устанавливает картинку монстра, который был до перехода
 * на другую активность
 * */
    @Override
    protected void onStart() {
        super.onStart();
        switch (mutant){
            case 1: image.setImageResource(R.drawable.tushkan);break;
            case 2: image.setImageResource(R.drawable.dog);break;
            case 3: image.setImageResource(R.drawable.plot);break;
            case 4: image.setImageResource(R.drawable.kaban);break;
            case 5: image.setImageResource(R.drawable.psydog);break;
            case 6: image.setImageResource(R.drawable.poltergeist);break;
            case 7: image.setImageResource(R.drawable.snorck);break;
            case 8: image.setImageResource(R.drawable.bloodsocker);break;
            case 9: image.setImageResource(R.drawable.burrer);break;
            case 10: image.setImageResource(R.drawable.controler);break;
            case 11: image.setImageResource(R.drawable.himera);break;
            case 12: image.setImageResource(R.drawable.giant);break;
        }
    }

    /**
     * Метод восстанавливает значения после перехода с других активностей
     * */

    @SuppressLint("SetTextI18n")
    private void reload(Intent intent){
        // при помощи объекта Intent получаем все значения
        hp = intent.getIntExtra("hp",0);
        weapon = intent.getIntExtra("weapon",1);
        kills = intent.getIntExtra("kills",-1);
        rang = intent.getIntExtra("rang",0);
        mutant = intent.getIntExtra("mutant",0);
        money = intent.getIntExtra("money",0);
        purchasedWeapons = intent.getIntArrayExtra("purchasedWeapons");
        if(purchasedWeapons==null){
            purchasedWeapons = new int[12];
            for (int i = 0; i < purchasedWeapons.length; i++) {
                purchasedWeapons[i] = 0;
            }
        }
        // устанавливаем значения на оба TextView
        textHp.setText(health+hp);
        textMoney.setText(dollar+money);
    }
    /**
     * Метод отвечает за загрузку и смену картинок если
     * жизни у монстра закончились.
     * Монстры устанавливаются в зависимости от ранга.
     * */
    private void loadImages(){
        // создаем новый поток и запускаем его отдельно через объект Handler
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                /* если жизни закончились, то генерируем нового монстра рандомно
                * в зависимости от ранга, устанавливаем соответствующую картинку
                * в ImageView и меняем значение у textHp
                * */
                if (hp<=0) {
                    Random rand = new Random();
                    // увеличиваем кол-во убийств
                        ++kills;
                        // передаем убитого мутанта для подсчёта денег
                    countMoney(mutant);
                    if(rang<2) {
                            mutant = rand.nextInt(4) + 1;
                        switch (mutant) {
                            case 1: {
                                    hp = 5;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.tushkan);
                                break;
                            }
                            case 2: {
                                    hp = 10;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.dog);
                                break;
                            }
                            case 3: {
                                    hp = 30;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.plot);
                                break;
                            }
                            case 4: {
                                    hp = 50;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.kaban);
                                break;
                            }
                        }
                    }
                    if(rang>=2&&rang<4){
                            mutant = rand.nextInt(4) + 5;
                        switch (mutant) {
                            case 5: {
                                    hp = 70;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.psydog);
                                break;
                            }
                            case 6: {
                                    hp = 100;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.poltergeist);
                                break;
                            }
                            case 7: {
                                    hp = 150;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.snorck);
                                break;
                            }
                            case 8: {
                                    hp = 200;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.bloodsocker);
                                break;
                            }
                        }
                    }
                    if(rang>=4){
                            mutant = rand.nextInt(4) + 9;
                        switch (mutant) {
                            case 9: {
                                    hp = 300;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.burrer);
                                break;
                            }
                            case 10: {
                                    hp = 500;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.controler);
                                break;
                            }
                            case 11: {
                                    hp = 1000;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.himera);
                                break;
                            }
                            case 12: {
                                    hp = 2000;
                                textHp.setText(health + hp);
                                image.setImageResource(R.drawable.giant);
                                break;
                            }
                        }
                    }

                }
                // запускаем поток заново(циклически)
                handler.post(this);
            }
        });
    }
    /**
     * Метод подсчитывает деньги в зависимости от убитого мутанта
     * и устанавливает новое значение у textMoney.
     * */
    @SuppressLint("SetTextI18n")
    private void countMoney(int mut){
        switch(mut){
            case 1:{money+=1;textMoney.setText(dollar+money);break;}
            case 2:{money+=3;textMoney.setText(dollar+money);break;}
            case 3:{money+=7;textMoney.setText(dollar+money);break;}
            case 4:{money+=10;textMoney.setText(dollar+money);break;}
            case 5:{money+=15;textMoney.setText(dollar+money);break;}
            case 6:{money+=20;textMoney.setText(dollar+money);break;}
            case 7:{money+=30;textMoney.setText(dollar+money);break;}
            case 8:{money+=50;textMoney.setText(dollar+money);break;}
            case 9:{money+=100;textMoney.setText(dollar+money);break;}
            case 10:{money+=200;textMoney.setText(dollar+money);break;}
            case 11:{money+=500;textMoney.setText(dollar+money);break;}
            case 12:{money+=1000;textMoney.setText(dollar+money);break;}
        }
    }
    /**
     * Метод запускает новый поток, в котором осуществляется
     * проверка и смена ранга, в зависимости от кол-ва убийств.
     * Всего 6 рангов, при достижении 6-ого ранга поток больше не
     * запускается.
     * */
    public void changeRang(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(kills>=50&&kills<200){
                    rang = 1;
                }
                if(kills>=200&&kills<400){
                    rang = 2;
                }
                if(kills>=400&&kills<700){
                    rang = 3;
                }
                if(kills>=700&&kills<1100){
                    rang = 4;
                }
                if(kills>=1100&&kills<2000){
                    rang = 5;
                }
                if(kills>=2000){
                    rang = 6;
                }
                if(rang!=6) {
                    handler.post(this);
                }
            }
        });
    }
/**
 * Метод в зависимости от текущего оружия рассичитывает и
 * вычитает урон от жизней монстра. Всего оружий 13, диапозон
 * наносимого урона от 1 до 500 единиц.
 * */
        public void attack(View view){
            switch (weapon) {
                case 1: {
                    hp--;
                    textHp.setText(health+hp);
                    break;
                }
                case 2: {
                    hp-=3;
                    textHp.setText(health+hp);
                    break;
                }
                case 3: {
                    hp-=5;
                    textHp.setText(health+hp);
                    break;
                }
                case 4: {
                    hp-=10;
                    textHp.setText(health+hp);
                    break;
                }
                case 5: {
                    hp-=20;
                    textHp.setText(health+hp);
                    break;
                }
                case 6: {
                    hp-=30;
                    textHp.setText(health+hp);
                    break;
                }
                case 7: {
                    hp-=40;
                    textHp.setText(health+hp);
                    break;
                }
                case 8: {
                    hp-=50;
                    textHp.setText(health+hp);
                    break;
                }
                case 9: {
                    hp-=70;
                    textHp.setText(health+hp);
                    break;
                }
                case 10: {
                    hp-=100;
                    textHp.setText(health+hp);
                    break;
                }
                case 11: {
                    hp-=150;
                    textHp.setText(health+hp);
                    break;
                }
                case 12: {
                    hp-=300;
                    textHp.setText(health+hp);
                    break;
                }
                case 13: {
                    hp-=500;
                    textHp.setText(health+hp);
                    break;
                }
            }
        }
/**
 * Метод перекидывает пользователя на активность с статистикой и
 * передаёт в эту активность данные при помощи объекта Intent.
 * */
    public void showStatistic(View view) {
        Intent intent = new Intent(this,StatisticActivity.class);
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
     * Метод перекидывает пользователя на активность с магазином и
     * передаёт в эту активность данные при помощи объекта Intent.
     * */
    public void goToShop(View view) {
        Intent intent = new Intent(this,ShopActivity.class);
        intent.putExtra("hp",hp);
        intent.putExtra("weapon",weapon);
        intent.putExtra("kills",kills);
        intent.putExtra("rang",rang);
        intent.putExtra("mutant",mutant);
        intent.putExtra("money",money);
        intent.putExtra("purchasedWeapons",purchasedWeapons);
        startActivity(intent);
    }
}
