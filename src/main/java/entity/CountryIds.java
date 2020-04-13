package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Country_Id", schema = "moe_db")
public class CountryIds {
    @Id
    private Integer idCountry;
    private Integer idRegion;
    private Integer idContinent;
}
