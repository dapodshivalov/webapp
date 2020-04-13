package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.CountryIdsRepo;
import repository.ItemIdsRepo;
import repository.filter.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class FilterController {

    @Autowired
    private ContinentRepo continentRepo;

    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private TradeCategoryRepo tradeCategoryRepo;

    @Autowired
    private TradeTypeRepo tradeTypeRepo;

    @Autowired
    private SectionRepo sectionRepo;

    @Autowired
    private ChapterRepo chapterRepo;

    @Autowired
    private YearRepo yearRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CountryIdsRepo countryIdsRepo;

    @Autowired
    private ItemIdsRepo itemIdsRepo;

//    @GetMapping(value = "/api/filters")
//    public List<Filter<Object>> getAllFilters() {
//        List<Filter<Object>> filters = new ArrayList<>();
//        List<Continent> res = continentRepo.findAll();
//        List<Object> continents = res.stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idContinent", continents));
//
//
//        List<Object> regions = regionRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idRegion", regions));
//
//        List<Object> countries = countryRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idCountry", countries));
//
//        List<Object> tradeCategories = tradeCategoryRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idTradeCategory", tradeCategories));
//
//        List<Object> tradeTypes = tradeTypeRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idTradeType", tradeTypes));
//
//        List<Object> sections = sectionRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idSection", sections));
//
//        List<Object> chapters = chapterRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idChapter", chapters));
//
//        List<Object> years = yearRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idYear", years));
//
//        List<Object> items = itemRepo.findAll().stream().filter(el -> el != null).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
//        filters.add(new Filter("idItem", items));
//
//        return filters;
//    }

    @GetMapping(value = "/api/countryfilter")
    public Map<String, Object> getCountryFilter() {
        Map<String, Object> result = new HashMap<>();

        List<Object> continents = continentRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
        List<Object> regions = regionRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
        List<Object> countries = countryRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());

        result.put("continents", continents);
        result.put("regions", regions);
        result.put("countries", countries);

        List<Object> relations = countryIdsRepo.findAll().stream().filter(Objects::nonNull).collect(Collectors.toList());

        result.put("relations", relations);

        return result;
    }

    @GetMapping(value = "/api/itemfilter")
    public Map<String, Object> getItemFilter() {
        Map<String, Object> result = new HashMap<>();

        List<Object> sections = sectionRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
        List<Object> chapters = chapterRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
        List<Object> items = itemRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());
        List<Object> relations = itemIdsRepo.findAll().stream().filter(Objects::nonNull).collect(Collectors.toList());

        result.put("sections", sections);
        result.put("chapters", chapters);
        result.put("items", items);
        result.put("relations", relations);

        return result;
    }

    @GetMapping(value = "/api/tradecategoryfilter")
    public Map<String, Object> getTradeCategoryFilter() {
        Map<String, Object> result = new HashMap<>();

        List<Object> tradeCategories = tradeCategoryRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());

        result.put("tradeCategories", tradeCategories);

        return result;
    }

    @GetMapping(value = "/api/tradetypefilter")
    public Map<String, Object> getTradeTypeFilter() {
        Map<String, Object> result = new HashMap<>();

        List<Object> tradeTypes = tradeTypeRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());

        result.put("tradeTypes", tradeTypes);

        return result;
    }

    @GetMapping(value = "/api/yearfilter")
    public Map<String, Object> getYearFilter() {
        Map<String, Object> result = new HashMap<>();

        List<Object> years = yearRepo.findAll().stream().filter(Objects::nonNull).map(el -> (Object) el.toTinyData()).collect(Collectors.toList());

        result.put("years", years);

        return result;
    }
}
