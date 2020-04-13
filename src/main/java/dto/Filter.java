package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter<T> {
    private String name;
    private List<T> params;

    @JsonIgnore
    public boolean isEmpty() {
        return params == null || params.isEmpty();
    }
}
