package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class S3 extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3);

        EditText editText = findViewById(R.id.notes);
        Intent intent = getIntent();
        try {
            String noteId = intent.getStringExtra("noteid");
            int noteID = Integer.parseInt(noteId);
            this.noteid = noteID;
        } catch (NumberFormatException e){ }

        if(noteid != -1) {
            Note note = S2a.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveNoteButton(View view){
        EditText editText = findViewById(R.id.notes);
        String content = editText.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (S2a.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }
        Intent intent = new Intent(this, S2a.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
