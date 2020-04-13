package controller;

import dto.Filter;
import entity.CountryIds;
import entity.ItemIds;
import entity.Trade;
import exception.FilteringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import repository.CountryIdsRepo;
import repository.ItemIdsRepo;
import repository.TradeRepo;
import specification.SpecificationsBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class ValueController {

    @Autowired
    private CountryIdsRepo countryIdsRepo;

    @Autowired
    private ItemIdsRepo itemIdsRepo;

    @Autowired
    private TradeRepo tradeRepo;

    private List<Filter<Object>> prepareFilters(List<Filter<Object>> filters) throws FilteringException {
        List<Filter<Object>> curFilters = Arrays.asList(
                getFilterByName(filters, "idCountry"),
                getFilterByName(filters, "idRegion"),
                getFilterByName(filters, "idContinent"));

        List<CountryIds> countryIds = search(curFilters, countryIdsRepo, true);

        for (Filter el :
                curFilters) {
            if (el != null) {
                deleteFilterByName(filters, el.getName());
            }
        }

        Filter<Object> countryFilter = new Filter<>("idCountry", null);
        filters.add(countryFilter);

        makeFilterFromList(countryFilter, countryIds, CountryIds::getIdCountry);



        curFilters = Arrays.asList(
                getFilterByName(filters, "idChapter"),
                getFilterByName(filters, "idSection"));

        List<ItemIds> itemIds = search(curFilters, itemIdsRepo, true);

        for (Filter el :
                curFilters) {
            if (el != null) {
                deleteFilterByName(filters, el.getName());
            }
        }

        Filter<Object> itemFilter = new Filter<>("idItem", null);
        filters.add(itemFilter);

        makeFilterFromList(itemFilter, itemIds, ItemIds::getIdItem);

        return filters;
    }

    private <T> List<T> search(List<Filter<Object>> filters, JpaSpecificationExecutor<T> repo) {
        return search(filters, repo, false);
    }

    private <T> List<T> search(List<Filter<Object>> filters, JpaSpecificationExecutor<T> repo, boolean stopIfAllFiltersAreEmpty) {
        boolean isAllFiltersEmpty = true;
        SpecificationsBuilder<T> builder = new SpecificationsBuilder();

        for (Filter<Object> el:
             filters) {
            if (el != null && !el.isEmpty()) {
                isAllFiltersEmpty = false;
                List<Object> params = el.getParams();
                builder.with(el.getName(), ":", params);
            }
        }

        if (isAllFiltersEmpty && stopIfAllFiltersAreEmpty) {
            return null;
        }

        Specification<T> spec = builder.build();
        List<T> res = repo.findAll(spec);

        return res;
    }


//    @RequestMapping(method = RequestMethod.POST, value = "/api/search", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<Trade> searchTrade(@RequestBody List<Filter<Object>> filters) throws FilteringException {
//        filters = prepareFilters(filters);
//
//        List<Trade> trades = search(filters, tradeRepo);
//
//        return trades;
//    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception ex) {
        System.out.println(ex.getMessage());
    }

    private <T, E> void makeFilterFromList(Filter<E> filter, List<T> list, Function<? super T, E> mapper) throws FilteringException {
        if (list == null) {
            filter.setParams(null);
        } else {
            if (list.size() == 0) {
                // TODO вернуть ошибку фильтрации товаров
                throw new FilteringException("Can't filter by " + filter.getName());
            }
            List<E> ids = list.stream().map(mapper).collect(Collectors.toList());
            filter.setParams(ids);
        }
    }

    private Filter<Object> getFilterByName(List<Filter<Object>> filters, String name) {
        Filter res = null;
        for (Filter el: filters) {
            if (el.getName().equals(name)) {
                res = el;
                break;
            }
        }
        return res;
    }

    private void deleteFilterByName(List<Filter<Object>> filters, String name) {
        for (Filter el: filters) {
            if (el.getName().equals(name)) {
                filters.remove(el);
                break;
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/getcountryvalue", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getCountryValue(@RequestBody List<Filter<Object>> filters) {
        List<Trade> trades = search(filters, tradeRepo);

        Map <Integer, Double> map = new TreeMap<>();

        Map <Integer, List<Double>> topCountry = new TreeMap<>();

        for (Trade trade :
                trades) {
            map.putIfAbsent(trade.getIdCountry(), 0.0);
            map.replace(trade.getIdCountry(), map.get(trade.getIdCountry()) + trade.getValue());

            topCountry.putIfAbsent(trade.getIdCountry(), Arrays.asList(0.0, 0.0, 0.0));
            List<Double> old = new ArrayList<>(topCountry.get(trade.getIdCountry()));
            old.set(trade.getIdTradeType(), old.get(trade.getIdTradeType()) + trade.getValue());
            topCountry.replace(trade.getIdCountry(), old);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("map", map);
        result.put("topCountry", topCountry);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/valuebyyears", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getValueByYears(@RequestBody List<Filter<Object>> filters) {
        List<Trade> trades = search(filters, tradeRepo);

        Map <Integer, List<Double>> value = new HashMap<>();
        Map <Integer, Double> growth = new TreeMap<>();
        Map <Integer, Double> balance = new TreeMap<>();

        int minYear = 3000;
        int maxYear = 0;
        for (Trade trade :
                trades) {
            minYear = Math.min(trade.getIdYear(), minYear);
            maxYear = Math.max(trade.getIdYear(), maxYear);
            value.putIfAbsent(trade.getIdYear(), Arrays.asList(0.0, 0.0, 0.0));
            List<Double> old = new ArrayList<>(value.get(trade.getIdYear()));
            old.set(trade.getIdTradeType(), old.get(trade.getIdTradeType()) + trade.getValue());
            value.replace(trade.getIdYear(), old);
        }

        if (trades.size() == 0) {
            Map<String, Object> result = new HashMap<>();
            result.put("value", value);
            result.put("growth", growth);
            result.put("balance", balance);

            return result;
        }
        List<Double> tmp = new ArrayList<>(value.get(minYear));
        balance.putIfAbsent(minYear, tmp.get(0) + tmp.get(1) - tmp.get(2));

        Double lastYearValue = tmp.get(0) + tmp.get(1) + tmp.get(2);
        Double curYearValue = 0.0;
        if (lastYearValue <= 0.00001 && lastYearValue >= -0.00001) {
            value.remove(minYear);
        }
        for (int i = minYear + 1; i <= maxYear; i++) {
            try {
                tmp = new ArrayList<>(value.get(i));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("Year" + (1999 + i));
                continue;
            }
            curYearValue = tmp.get(0) + tmp.get(1) + tmp.get(2);
            if (curYearValue <= 0.00001 && curYearValue >= -0.00001) {
                value.remove(i);
                continue;
            }
            balance.putIfAbsent(i, tmp.get(0) + tmp.get(1) - tmp.get(2));

            if (lastYearValue <= 0.00001 && lastYearValue >= -0.00001) {
                lastYearValue = curYearValue;
                continue;
            }
            growth.putIfAbsent(i, (curYearValue - lastYearValue) / lastYearValue);
            lastYearValue = curYearValue;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        result.put("growth", growth);
        result.put("balance", balance);

        return result;
    }
}