package es.upv.etsit.trabajoaplicusa;

/**
 * Representa un hábito a seguir por el usuario.
 */
public class Habit {

    private final String name;
    private boolean completed;

    public Habit(String name) {
        this.name = name.trim();
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Habit{name='" + name + "', completed=" + completed + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habit)) return false;
        Habit habit = (Habit) o;
        return name.equals(habit.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
