
package com.selimcinar.sanatkitabmuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Sqlite veritabanı Açma veya oluşturma kodları ("Veritabanı adı",Mode_Gizli,Boşolabilir);
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            //Sql tablosu oluşturma kodları  -(Tablo oluşmuş değil ise tablo oluştur adınıyaz (id oluştur özel anahtarlı,name oluştur varcharlı , age oluştur INT tipli ))
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT) ");
            //Tabloda elemanlara veri ekler
            //database.execSQL("INSERT INTO musicians(name,age) VALUES('James',50)");
            //database.execSQL("INSERT INTO musicians(id,name,age) VALUES(2,'Harold',80)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES('Lars',70)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES('Lars',70)");
            //Güncelleme işlemleri
            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'LARS'");
            //database.execSQL("UPDATE musicians SET name='Lars' WHERE name = 'LARS'");

            //Silme işlemleri
            //database.execSQL("DELETE From musicians Where id = 2");

            //Verileri gezme dolaşma satır sorguları("Sql kodları dolaşım",Seçili filtreler)
            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null);
            //Filtreleme işlemleri
            // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id = 2 ",null);
            // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE age>50 ",null);
            // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name='James' ",null);
            // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%S' ",null); //Sonu S ile biten
            // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'S%' ",null); //Başı S ile başlayan


            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) { //Veriler üzerinden dolaşım yap
                System.out.println("Name : " + cursor.getString(nameIx)); //Gezinti üzerinden dolaş nameIx string değişkeni içindeki sql değerlerini al
                System.out.println("Age  : " + cursor.getInt(ageIx));  //Cursor gezinti üzerinden dolaş ageIx int değişkeni içindeki sql değerlerini al
                System.out.println("Id   : " + cursor.getInt(idIx)); //Cursor gezinti üzerinden dolaş idIx değişkeni içindeki sql değerlerini al
            }
            cursor.close(); //Cursori Gezintiyi kapat
        } catch (Exception e) {
            //Loglara hataları yazdırır.
            e.printStackTrace();
        }

    }
}