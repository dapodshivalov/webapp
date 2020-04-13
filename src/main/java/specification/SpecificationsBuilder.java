package specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificationsBuilder<E> {
    private List<SearchCriteria> filters;

    public <E> SpecificationsBuilder() {
        filters = new ArrayList<>();
    }

    public SpecificationsBuilder<E> with(String key, String operation, Object value) {
        SearchOperation op = SearchOperation.getOperation(operation.charAt(0));
        filters.add(new SearchCriteria(key, op, value));
        return this;
    }

    public SpecificationsBuilder<E> with(String key, String operation, Object value, boolean orPredicate) {
        SearchOperation op = SearchOperation.getOperation(operation.charAt(0));
        filters.add(new SearchCriteria(key, op, value, orPredicate));
        return this;
    }

    public Specification<E> build() {
        if (filters.size() == 0) {
            return null;
        }

        List<Specification> specs = filters.stream()
                .map(SpecificationImp<E>::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < filters.size(); i++) {
            result = filters.get(i).isOrPredicate()
                    ? Specification.where(result).or(specs.get(i))
                    : Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
