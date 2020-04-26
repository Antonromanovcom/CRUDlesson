package com.antonromanov.springboot.crud.librarycrud.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CoverType {
HARD("Твердая обложка"),
SOFT("Мягкая обложка");

private final String type;
}
