package org.example.watchlist.domain.application.service.dto;

import java.util.List;

public record MovieResults(
    Integer page,
    List<MovieDto> results,
    Integer total_results,
    Integer total_pages
) {
}
