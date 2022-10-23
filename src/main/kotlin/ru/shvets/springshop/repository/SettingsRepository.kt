package ru.shvets.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository
import ru.shvets.springshop.entity.Settings

interface SettingsRepository : JpaRepository<Settings, Long> {
}