package dev.invest.service;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ForecastRecord;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.db.repository.ForecastRepository;
import dev.invest.mapper.ForecastMapper;
import dev.invest.model.forecast.CreateForecastRequest;
import dev.invest.model.forecast.ForecastDto;
import dev.invest.model.forecast.UpdateForecastRequest;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastService {
    private final ForecastRepository forecastRepository;
    private final ForecastMapper forecastMapper;

    public List<ForecastDto> getAll() {
        List<ForecastRecord> forecasts = forecastRepository.getAll();
        log.info("Получено {} прогнозов", forecasts.size());

        return forecasts.stream()
                .map(forecastMapper::toModel)
                .toList();
    }

    public ForecastDto getByUid(UUID uid) {
        ForecastRecord forecast = forecastRepository.getById(uid)
                .orElseThrow(() -> new NoSuchElementException("Прогноз с uid " + uid + " не найден"));
        log.info("Получен прогноз {}", forecast.getUid());

        return forecastMapper.toModel(forecast);
    }

    public ForecastDto create(CreateForecastRequest forecast) {
        ForecastRecord saved = forecastRepository.save(forecast);
        log.info("Прогноз {} сохранен", saved.getUid());

        return forecastMapper.toModel(saved);
    }

    public ForecastDto update(UUID uuid, UpdateForecastRequest forecastRequest) {
        ForecastRecord saved = forecastRepository.update(uuid, forecastRequest)
                .orElseThrow(() -> new NoSuchElementException("Прогноз с uid " + uuid + " не найден"));
        log.info("Прогноз {} обновлен", saved.getUid());

        return forecastMapper.toModel(saved);
    }

    public void deleteByUid(UUID uid) {
        forecastRepository.delete(uid)
                .orElseThrow(() -> new NoSuchElementException("Прогноз с uid " + uid + " не найден"));
        log.info("Прогноз {} удален", uid);
    }
}
