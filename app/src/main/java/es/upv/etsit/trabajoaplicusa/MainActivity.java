package es.upv.etsit.trabajoaplicusa;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    private List<Habit> habitList;
    private FloatingActionButton fabAddHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        setupRecyclerView();
        setupFab();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        fabAddHabit = findViewById(R.id.fab_add_habit);
    }

    private void initData() {
        habitList = new ArrayList<>();
        habitList.add(new Habit("Apagar luces innecesarias"));
        habitList.add(new Habit("Reducir uso de plástico"));
        habitList.add(new Habit("Duchas más cortas"));
        habitList.add(new Habit("Usar transporte público"));
        habitList.add(new Habit("Reciclar correctamente"));
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HabitAdapter(this, habitList);
        recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        fabAddHabit.setOnClickListener(v -> showAddHabitDialog());
    }

    private void showAddHabitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo hábito");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input.setHint("Escribe el nombre del hábito");
        builder.setView(input);

        builder.setPositiveButton("Añadir", (dialog, which) -> {
            String habitName = input.getText().toString().trim();
            if (habitName.isEmpty()) {
                Toast.makeText(MainActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
            } else {
                addNewHabit(habitName);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void addNewHabit(String name) {
        Habit newHabit = new Habit(name);
        habitList.add(newHabit);
        adapter.notifyItemInserted(habitList.size() - 1);
        recyclerView.scrollToPosition(habitList.size() - 1);
    }
}
