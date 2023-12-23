package com.example.ntsoumoucarel.controller;

import com.example.ntsoumoucarel.model.AddressForm;
import com.example.ntsoumoucarel.model.Forecast;
import com.example.ntsoumoucarel.model.Response;
import com.example.ntsoumoucarel.model.ResponseEtalabAddress;
import com.example.ntsoumoucarel.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class MeteoController {

    @Autowired
    private AddressRepository  adresseRepository;

    //token pour pouvoir utiliser l'API meteo concept
    String meteoConceptApiKey = "93a413c67d6f63ea863c6d6dd134a48bbcd32abf41dfba1e0ee8e9d59d028767";

    @PostMapping("/meteo")
    public String getMeteo(@ModelAttribute("addressForm") AddressForm addressForm, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        //ETALAB DATA GOUV ===> API ADRESSE
        String etalabApiUrl = "https://api-adresse.data.gouv.fr/search/?q=" + addressForm.getAddress();
        ResponseEtalabAddress etalabResponse = restTemplate.getForObject(etalabApiUrl, ResponseEtalabAddress.class);

        try {
            //Récupérez les coordonnées depuis la réponse d'Etalab
            assert etalabResponse != null;
            String city = etalabResponse.getFeatures().get(0).getProperties().getCity();
            String label = etalabResponse.getFeatures().get(0).getProperties().getLabel();
            double longitude = etalabResponse.getFeatures().get(0).getGeometry().getCoordinates().get(0);
            double latitude = etalabResponse.getFeatures().get(0).getGeometry().getCoordinates().get(1);


            //METEO-CONCEPT ===> API METEO

            String meteoConceptApiUrl = "https://api.meteo-concept.com/api/forecast/daily?token="
                    + meteoConceptApiKey + "&latlng=" + latitude + "," + longitude;
            Response response = restTemplate.getForObject(meteoConceptApiUrl, Response.class);

            //Récupérez les coordonnées depuis la réponse d'Etalab
            double tMin = response.getForecast().get(0).getTmin();
            double tMax = response.getForecast().get(0).getTmax();
            double weather = response.getForecast().get(0).getWeather();
            double wind10m = response.getForecast().get(0).getWind10m();
            double temperature = Forecast.calculateTemperature(tMin, tMax, wind10m);


            //Pour Ajouter des informations météorologiques au modèle
            model.addAttribute("weatherInfo", "Adresse : " + addressForm.getAddress());
            model.addAttribute("city", "Ville : " + city);
            model.addAttribute("label", "Adresse Complet : " + label);
            model.addAttribute("temperatureMax", "Température maximale : " + tMax);
            model.addAttribute("temperatureMin", "Température minimale : " + tMin);
            model.addAttribute("latitude", "Latitude : " + latitude);
            model.addAttribute("longitude", "Longitude : " + longitude);
            model.addAttribute("descriptionMeto", "Meteo : " + weather);
            model.addAttribute("temperature", "Temperature Ressentie : " + temperature);

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                // Gérer l'erreur spécifique liée à la position géographique
                model.addAttribute("error",
                        "Adresse inexistante, Veuillez entrer une adresse correcte");
            } else {
                // Gérer d'autres types d'erreurs HttpClientErrorException
                model.addAttribute("error",
                        "Adresse inexistante, Veuillez entrer une adresse correcte.");
            }
        } catch (Exception e) {
            // Gérer d'autres exceptions
            model.addAttribute("error",
                    "Adresse inexistante, Veuillez entrer une adresse correcte.");
        }

        return "meteo";
    }
}

