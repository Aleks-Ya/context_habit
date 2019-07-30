package ru.yaal.contexthabit.service;

import androidx.work.WorkManager;

import ru.yaal.contexthabit.repo.Repository;

public final class Singleton {
    public static Repository repository;
    public static RenewService renewService;
    public static WorkManager workManager;
}
