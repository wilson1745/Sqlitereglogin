package e.wilso.sqlitereglogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

   //public static final String DATABASE_NAME = "register.db";

   private static DatabaseHelper instance;

   public static final String TABLE_NAME = "register";
   public static final String COL_1 = "ID";
   public static final String COL_2 = "Name";
   public static final String COL_3 = "Username";
   public static final String COL_4 = "Password";
   public static final String COL_5 = "Email";
   public static final String COL_6 = "Phone";

   public static DatabaseHelper getInstance(Context ctx) {
      if(instance == null) {
         instance = new DatabaseHelper(ctx, "register.db", null, 1);
      }
      return instance;
   }


   public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
      super(context, name, factory, version);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Username TEXT, Password TEXT, Email TEXT, Phone TEXT)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(db);
   }
}
