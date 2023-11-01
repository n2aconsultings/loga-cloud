package startup.loga.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repair implements Serializable
{
    private Long id;
    private String dossier;
    private String profile;
    private Date createdAt;
    private String reference;
    private String description;
    private Integer mileage;
    private Boolean approved;
    private List<Task> tasks = new ArrayList<>();
    private List<Spare> spares = new ArrayList<>();

    public Integer getTotalSpare() {
        int totalSpare = 0;
        for (Spare spare : this.getSpares()) {
            totalSpare += spare.getAmount();
        }
        return totalSpare;
    }

    public Integer getTotalTask() {
        int totalTask=0;
        for (Task task : this.getTasks()) {
            totalTask += task.getCost();
        }
        return totalTask;
    }

    public Integer getTotal(){
        int totalSpare = getTotalSpare();
        int totalTask = getTotalTask();
        return totalTask+totalSpare;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void addSpare(Spare spare){
        this.spares.add(spare);
    }

    @Override
    public String toString() {
        return reference;
    }
}
