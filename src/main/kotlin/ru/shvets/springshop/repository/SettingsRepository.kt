package ru.shvets.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository
import ru.shvets.springshop.entity.SettingsEntity

interface SettingsRepository : JpaRepository<SettingsEntity, Long> {
}