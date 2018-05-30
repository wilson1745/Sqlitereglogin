package e.wilso.sqlitereglogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Login extends AppCompatActivity {

   SQLiteDatabase db;
   Button _btnreg, _btnlog;
   EditText _txtusername, _txtpass;
   Cursor cursor;
   Bundle bag = new Bundle();

   private DatabaseHelper openHelper;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);

      final ListView list = findViewById(R.id.list);

      openHelper = DatabaseHelper.getInstance(this);
      db = openHelper.getReadableDatabase();

      _txtusername = findViewById(R.id.txtusername);
      _txtpass = findViewById(R.id.txtpass);
      _btnlog = findViewById(R.id.btnlog);
      _btnreg = findViewById(R.id.btnreg);

      _btnlog.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String username = _txtusername.getText().toString();
            String pass = _txtpass.getText().toString();

            cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME +
                            " WHERE " + DatabaseHelper.COL_3 +
                            " = ? AND " + DatabaseHelper.COL_4 +
                            " = ? ", new String[]{username, pass});

            //Log.d("YESSSSSS", "You log it");

            if(cursor != null) {
               if(cursor.getCount() > 0) {
                  cursor.moveToFirst();
                  String strname = cursor.getString(1);
                  String strusername = cursor.getString(2);
                  String stremail = cursor.getString(3);
                  String strphone = cursor.getString(4);

                  bag.putString("name", strname);
                  bag.putString("username", strusername);
                  bag.putString("email", stremail);
                  bag.putString("phone", strphone);

                  Intent intent = new Intent(Login.this, Person_Info.class);
                  intent.putExtras(bag);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(intent);

                  Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
               }
               else {
                  Toast.makeText(getApplicationContext(), "user not exist", Toast.LENGTH_LONG).show();
               }
            }
         }
      });

      _btnreg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
         }
      });
   }
}
