#!/bin/bash

echo "Перехід до головного репозиторію..."
cd ~/git/projects/ResourcesHub/

echo "Оновлення головного репозиторію..."
git pull origin main

echo "Ініціалізація та оновлення submodules..."
git submodule update --init --recursive

echo "Оновлення кожного submodule..."
git submodule foreach 'git checkout main; git pull origin main'

echo "Додавання змін submodules до головного репозиторію..."
git add .

echo "Створення commit у головному репозиторії..."
git commit -m "Update submodules to the latest commits"

echo "Push змін у головний репозиторій..."
git push origin main

echo "Оновлення завершено!"
