package dev.invest.service;


import dev.invest.db.repository.FundamentalRepository;
import dev.invest.mapper.FundamentalMapper;
import dev.invest.model.fundamental.CreateFundamentalRequest;
import dev.invest.model.fundamental.FundamentalDto;
import dev.invest.model.fundamental.UpdateFundamentalRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundamentalService {
    private final FundamentalRepository fundamentalRepository;
    private final FundamentalMapper fundamentalMapper;

    public List<FundamentalDto> getAll() {
        return fundamentalRepository.getAll()
                .stream()
                .map(fundamentalMapper::toModel)
                .toList();
    }

    public FundamentalDto getByUid(UUID uid) {
        return fundamentalRepository.getById(uid)
                .map(fundamentalMapper::toModel)
                .orElseThrow(() -> new NoSuchElementException("Фундаментальный прогноз с uid " + uid + " не найден"));
    }

    public FundamentalDto create(CreateFundamentalRequest createFundamentalRequest) {
        return fundamentalMapper.toModel(fundamentalRepository.save(createFundamentalRequest));
    }

    public FundamentalDto update(UUID uid, UpdateFundamentalRequest request) {
        return fundamentalRepository.update(uid, request)
                .map(fundamentalMapper::toModel)
                .orElseThrow(() -> new NoSuchElementException("Фундаментальный прогноз с uid " + uid + " не найден"));
    }

    public void deleteByUid(UUID uid) {
        fundamentalRepository.delete(uid)
                .orElseThrow(() -> new NoSuchElementException("Фундаментальный прогноз с uid " + uid + " не найден"));
        log.info("Фундаментальный прогноз {} удален", uid);
    }
}
