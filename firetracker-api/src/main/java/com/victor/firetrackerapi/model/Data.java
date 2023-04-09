package com.victor.firetrackerapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "insertionDateTime")
    private LocalDateTime insertionDateTime;
    /*TODO: Set the dateTime with now()
        MyEntity entity = new MyEntity();
        entity.setCreatedAt(LocalDateTime.now());'
     */

    @Column(name = "isOnFire")
    private Boolean isOnFire;

    @Column(name = "irLevel")
    private Double irLevel;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "isActive")
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInsertionDateTime() {
        return insertionDateTime;
    }

    public void setInsertionDateTime(LocalDateTime insertionDateTime) {
        this.insertionDateTime = insertionDateTime;
    }

    public Boolean getOnFire() {
        return isOnFire;
    }

    public void setOnFire(Boolean onFire) {
        isOnFire = onFire;
    }

    public Double getIrLevel() {
        return irLevel;
    }

    public void setIrLevel(Double irLevel) {
        this.irLevel = irLevel;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", insertionDateTime=" + insertionDateTime +
                ", isOnFire=" + isOnFire +
                ", irLevel=" + irLevel +
                ", temperature=" + temperature +
                ", isActive=" + isActive +
                '}';
    }
}
