import java.util.HashMap;
import java.util.LinkedList;

public class HashTableManager {
    private int m = 7;
    private HashMap<String, LinkedList<Tarea>> tareas;

    public HashTableManager(){
        tareas = new HashMap<>();
    }

    public void addTarea(Tarea t){
        String indS = String.valueOf(t.getId() % m);
        if (this.tareas.containsKey(indS)){
            this.tareas.get(indS).add(t);
        }else {
            LinkedList<Tarea> tmp = new LinkedList<>();
            tmp.add(t);
            this.tareas.put(indS, tmp);
        }
    }

    public Tarea getTarea(String ID){
        String indS = String.valueOf(Integer.parseInt(ID)%m);
        if (tareas.containsKey(indS)){
            for (Tarea t:tareas.get(indS)){
                if (String.valueOf(t.getId()).equals(ID))
                    return t;
            }
        }
        return null;
    }
}
