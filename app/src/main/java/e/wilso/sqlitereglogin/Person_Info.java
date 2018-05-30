package e.wilso.sqlitereglogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Person_Info extends AppCompatActivity {

   TextView sname, susername, semail, sphone;
   Button rtnbutton;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_person__info);

      findview();

      final Intent intent = getIntent();
      Bundle bag = intent.getExtras();

      String name = bag.getString("name", null);
      String username = bag.getString("username", null);
      String email = bag.getString("email", null);
      String phone = bag.getString("phone", null);

      sname.setText(name);
      susername.setText(username);
      semail.setText(email);
      sphone.setText(phone);

      rtnbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent1 = new Intent(Person_Info.this, MainActivity.class);
            startActivity(intent1);
         }
      });

   }

   private void findview() {
      sname = findViewById(R.id.sname);
      susername = findViewById(R.id.susername);
      semail = findViewById(R.id.semail);
      sphone = findViewById(R.id.sphone);
      rtnbutton = findViewById(R.id.rtnbutton);
   }
}
