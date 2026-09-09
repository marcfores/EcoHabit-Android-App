package es.upv.etsit.trabajoaplicusa;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private static final String PREFS_NAME = "EcoHabitPrefs";

    private final List<Habit> habits;
    private final SharedPreferences prefs;
    private final Context context;

    public HabitAdapter(Context context, List<Habit> habits) {
        this.habits = habits;
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habits.get(position);

        holder.nameText.setText(habit.getName());

        // Cambiar icono según si está completado o no
        holder.icon.setImageResource(habit.isCompleted() ? R.drawable.ic_done : R.drawable.ic_habit);

        // Evitar que el checkbox dispare el listener al hacer bind
        holder.checkBox.setOnCheckedChangeListener(null);
        boolean completed = prefs.getBoolean(habit.getName(), habit.isCompleted());
        habit.setCompleted(completed);
        holder.checkBox.setChecked(completed);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            habit.setCompleted(isChecked);
            prefs.edit().putBoolean(habit.getName(), isChecked).apply();

            // Cambiar icono dinámicamente
            holder.icon.setImageResource(isChecked ? R.drawable.ic_done : R.drawable.ic_habit);

            Snackbar.make(holder.itemView,
                    isChecked ? "Hábito completado" : "Hábito desmarcado",
                    Snackbar.LENGTH_SHORT).show();

            // Animación al activar o desactivar el hábito
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 1f, 1.1f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 1f, 1.1f, 1f);

            scaleX.setDuration(300);
            scaleY.setDuration(300);

            scaleX.start();
            scaleY.start();
        });

        holder.deleteButton.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            Habit removedHabit = habits.get(pos);

            habits.remove(pos);
            notifyItemRemoved(pos);

            // Borramos de SharedPreferences
            prefs.edit().remove(removedHabit.getName()).apply();

            Snackbar.make(v, "Hábito eliminado", Snackbar.LENGTH_LONG)
                    .setAction("Deshacer", view -> {
                        habits.add(pos, removedHabit);
                        notifyItemInserted(pos);
                        prefs.edit().putBoolean(removedHabit.getName(), removedHabit.isCompleted()).apply();
                    }).show();
        });
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        final TextView nameText;
        final CheckBox checkBox;
        final ImageButton deleteButton;
        final ImageView icon;

        HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.habit_name);
            checkBox = itemView.findViewById(R.id.habit_checkbox);
            deleteButton = itemView.findViewById(R.id.button_delete);
            icon = itemView.findViewById(R.id.habit_icon);
        }
    }
}
