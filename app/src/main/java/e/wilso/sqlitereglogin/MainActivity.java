package e.wilso.sqlitereglogin;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   //SQLiteOpenHelper openHelper;
   //SQLiteDatabase db;
   Button _btnreg, _btnlogin;
   EditText _txtname, _txtusername, _txtpass, _txtemail, _txtphone;

   private DatabaseHelper openHelper;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //openHelper = new DatabaseHelper(this, "register.db", null, 1);
      openHelper = DatabaseHelper.getInstance(this);

      findView();

      _btnreg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            //db = openHelper.getWritableDatabase();
            String name = _txtname.getText().toString();
            String username = _txtusername.getText().toString();
            String pass = _txtpass.getText().toString();
            String email = _txtemail.getText().toString();
            String phone = _txtphone.getText().toString();

            insertdata(name, username, pass, email, phone);

            Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
         }
      });

      _btnlogin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
         }
      });
   }

   private void findView() {
      _txtname = findViewById(R.id.txtname);
      _txtusername = findViewById(R.id.txtusername);
      _txtpass = findViewById(R.id.txtpass);
      _txtemail = findViewById(R.id.txtemail);
      _txtphone = findViewById(R.id.txtphone);
      _btnreg = findViewById(R.id.btnreg);
      _btnlogin = findViewById(R.id.btnlog);
   }

   public void insertdata(String name, String username, String pass, String email, String phone) {
      ContentValues contentValues = new ContentValues();
      contentValues.put(DatabaseHelper.COL_2, name);
      contentValues.put(DatabaseHelper.COL_3, username);
      contentValues.put(DatabaseHelper.COL_4, pass);
      contentValues.put(DatabaseHelper.COL_5, email);
      contentValues.put(DatabaseHelper.COL_6, phone);

      long id = openHelper.getWritableDatabase().insert(DatabaseHelper.TABLE_NAME, null, contentValues);
      Log.d("Yes", id + "");
   }
}
