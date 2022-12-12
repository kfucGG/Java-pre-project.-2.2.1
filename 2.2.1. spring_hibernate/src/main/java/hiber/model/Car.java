package hiber.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private int series;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    @OneToOne(mappedBy = "car")
    private User owner;
}
