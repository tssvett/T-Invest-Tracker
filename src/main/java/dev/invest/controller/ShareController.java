package dev.invest.controller;

import dev.invest.mapper.ShareMapper;
import dev.invest.model.page.Pagination;
import dev.invest.model.share.CreateShareRequest;
import dev.invest.model.share.ShareDto;
import dev.invest.model.share.UpdateShareRequest;
import dev.invest.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(ShareController.API_SHARE)
@Tag(name = ShareController.SHARE_CONTROLLER, description = "API для работы с акциями")
public class ShareController {
    static final String SHARE_CONTROLLER = "share-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_SHARE = API_PREFIX + "/share";

    private final ShareService shareService;
    private final ShareMapper shareMapper;

    @PostMapping("/all")
    @Operation(summary = "Получить все акции", tags = {SHARE_CONTROLLER})
    public List<ShareDto> findAllShares(@RequestBody @Valid @Nullable Pagination pagination) {
        return shareMapper.toModelList(shareService.getAllPagination(pagination));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Получить акцию по её идентификатору", tags = {SHARE_CONTROLLER})
    public ShareDto findShareByUid(@PathVariable UUID uuid) {
        //TODO: Business logic
        return null;
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Удалить акцию по идентификатору", tags = {SHARE_CONTROLLER})
    public void deleteShare(@PathVariable UUID uuid) {
        //TODO: Business logic
    }
}
