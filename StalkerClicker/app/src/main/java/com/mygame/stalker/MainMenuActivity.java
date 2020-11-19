package com.mygame.stalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Класс отвечает за главное меню игры StalkerGame
 * */
public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Метод перенаправляет на активность с информацией о авторе
     * */
    public void showInformation(View view) {
        Intent intent = new Intent(this, AuthorInformationActivity.class);
        startActivity(intent);
    }
    /**
     * Метод перенаправляет на активность с самой игрой
     * */
    public void startGame(View view) {
        Intent intent = new Intent(this,GameWindowActivity.class);
        startActivity(intent);
    }
}
