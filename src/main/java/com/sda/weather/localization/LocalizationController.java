package com.sda.weather.localization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocalizationController {

    private final LocalizationService localizationService;
    private final ObjectMapper objectMapper;

    public String createLocalization(String data) {
        try {
            LocalizationDTO newLocalization = objectMapper.readValue(data, LocalizationDTO.class);
            Localization localization = localizationService.createLocalization(newLocalization.getCity(), newLocalization.getCountry(), newLocalization.getRegion(), newLocalization.getLatitude(), newLocalization.getLongitude());
            LocalizationDTO localizationDTO = mapToLocalizationDTO(localization);
            return objectMapper.writeValueAsString(localizationDTO);
        } catch (IllegalArgumentException | JsonProcessingException e) {
            return String.format("{\"message\": \"%s\"}", e.getMessage());
        }
    }

    public String getAllLocalizations() {
        try {
            List<Localization> allLocalizations = localizationService.getAllLocalizations();
            List<LocalizationDTO> allLocalizationsDTO = allLocalizations.stream()
                    .map(e -> mapToLocalizationDTO(e))
                    .collect(Collectors.toList());
            return objectMapper.writeValueAsString(allLocalizationsDTO);
        } catch (JsonProcessingException e) {
            return String.format("{\"message\": \"%s\"}", e.getMessage());
        }
    }

    private LocalizationDTO mapToLocalizationDTO(Localization localization) {

        LocalizationDTO localizationDTO = new LocalizationDTO();
        localizationDTO.setId(localization.getId());
        localizationDTO.setCity(localization.getCity());
        localizationDTO.setCountry(localization.getCountry());
        localizationDTO.setRegion(localization.getRegion());
        localizationDTO.setLatitude(localization.getLatitude());
        localizationDTO.setLongitude(localization.getLongitude());
        return localizationDTO;
    }

}
