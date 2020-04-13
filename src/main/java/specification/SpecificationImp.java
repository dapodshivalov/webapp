package specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SpecificationImp<E> implements Specification<E> {
    private SearchCriteria criteria;

    public <E> SpecificationImp(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case IN:
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
                List<Object> list = (List<Object>) criteria.getValue();
                for (Object el : list) {
                    inClause.value(el);
                }
                return inClause;
        }
        return null;
    }
}
