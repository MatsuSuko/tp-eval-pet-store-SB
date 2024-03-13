package TPPet;

import jakarta.persistence.*;

@Entity
public class Cat extends Animal {
    private String chipId;

    // Getters et setters

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
}
