package com.mygame.stalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity {
    // ImageView, которое отображает картинку сталкера
   private ImageView imageStalker;
    // ImageView, которое отображает картинку текущего оружия
   private ImageView imageGun;
   // TextView, которое будет хранить статистику
   private TextView textStatistic;
   // строка, которая будет хранить текущий ранг
   private String textRung;
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
    // этот массив будет хранить нажатые кнопки в ShopActivity
    private int[] purchasedWeapons = new int[12];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        imageStalker = findViewById(R.id.imageStalker);
        textStatistic = findViewById(R.id.textStatistic);
        imageGun = findViewById(R.id.imageGun);
        Intent intent = getIntent();
        // подгружаем данные из Intent
        setValues(intent);
        // устанавливаем картинку сталкера
        setImageStalker();
        // устанавливаем картинку оружия
        setWeapon();
        // устанавливаем текстовое представление ранга
        setRang();
        // устанавливаем статистику в TextView
        setStatistic();
    }
    /**
     * Метод устанавливает строковое представление
     * текущего ранга.
     * */
    private void setRang(){
        switch (rang){
            case 0:{textRung=getString(R.string.noob);break;}
            case 1:{textRung=getString(R.string.amateur);break;}
            case 2:{textRung=getString(R.string.experienced);break;}
            case 3:{textRung=getString(R.string.veteran);break;}
            case 4:{textRung=getString(R.string.master);break;}
            case 5:{textRung=getString(R.string.expert);break;}
            case 6:{textRung=getString(R.string.legend);break;}
        }
    }
    /**
     * Метод устанавливает картинку текущего оружия.
     * */
    private void setWeapon() {
        switch (weapon){
            case 2:{imageGun.setImageResource(R.drawable.pm);break;}
            case 3:{imageGun.setImageResource(R.drawable.obrez);break;}
            case 4:{imageGun.setImageResource(R.drawable.aksu);break;}
            case 5:{imageGun.setImageResource(R.drawable.ak);break;}
            case 6:{imageGun.setImageResource(R.drawable.lr);break;}
            case 7:{imageGun.setImageResource(R.drawable.il);break;}
            case 8:{imageGun.setImageResource(R.drawable.gp);break;}
            case 9:{imageGun.setImageResource(R.drawable.groza);break;}
            case 10:{imageGun.setImageResource(R.drawable.vss);break;}
            case 11:{imageGun.setImageResource(R.drawable.pkm);break;}
            case 12:{imageGun.setImageResource(R.drawable.gauss);break;}
            case 13:{imageGun.setImageResource(R.drawable.rpg);break;}
        }
    }
    /**
     * Метод устанавливает статистику в textStatistic,
     * включая текущий ранг, кол-во убийств и кол-во денег.
     * */
    private void setStatistic() {
        String txt = getString(R.string.rang)+" "+textRung+getString(R.string.killed_enemies)+" "+kills+getString(R.string.money1)+" "+money+"$";
        textStatistic.setText(txt);
    }
    /**
     * Метод восстанавливает значения, переданные из предыдущей
     * активности.
     * */
    private void setValues(Intent intent){
        hp = intent.getIntExtra("hp",0);
        weapon = intent.getIntExtra("weapon",1);
        kills = intent.getIntExtra("kills",0);
        rang = intent.getIntExtra("rang",0);
        mutant = intent.getIntExtra("mutant",0);
        money = intent.getIntExtra("money",0);
        purchasedWeapons = intent.getIntArrayExtra("purchasedWeapons");

    }
    /**
     * Метод устанавливает картинку сталкера,
     * в соответствии с текущим рангом.
     * */
    private void setImageStalker(){
                if(rang==1){
                    imageStalker.setImageResource(R.drawable.lubitel);
                }
                if(rang==2){
                    imageStalker.setImageResource(R.drawable.oputnuy);
                }
                if(rang==3){
                    imageStalker.setImageResource(R.drawable.veteran);
                }
                if(rang==4){
                    imageStalker.setImageResource(R.drawable.master);
                }
                if(rang==5){
                    imageStalker.setImageResource(R.drawable.exspert);
                }
                if(rang==6){
                    imageStalker.setImageResource(R.drawable.legend);
                }
    }
/**
 * Метод возвращает нас на активность с игрой и передаёт данные
 * при помощи объекта Intent.
 * */
    public void backToGameWindow(View view) {
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
}
