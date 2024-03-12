package md.usm.book_store.services;

import lombok.RequiredArgsConstructor;
import md.usm.book_store.models.City;
import md.usm.book_store.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;

    public City findCityById(Integer city_id){
        return cityRepository.findById(city_id).orElseGet(null);
    }

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }
}
