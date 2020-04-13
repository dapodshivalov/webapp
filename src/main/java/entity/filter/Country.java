package entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.filter.tinydata.TinyData;
import entity.filter.tinydata.TinyDatable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Country_Name", schema = "moe_db")
public class Country implements TinyDatable {
    @Id
    private Integer idCountry;

    private String country;

    @Override
    @JsonIgnore
    public TinyData toTinyData() {
        return new TinyData(idCountry, country);
    }
}
