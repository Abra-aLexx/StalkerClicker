package com.mygame.stalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Класс связанный с активностью, которая показывает
 * информацию о авторе.
 * */
public class AuthorInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_information);
    }
/**
 * Метод обрабатывает нажатие кнопки для возврата в главное меню.
 * */
    public void backToMainMenu(View view) {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }
}
