#!/bin/bash

# Инструкция по установке:
#	1.	Сохраните скрипт в файл, например, generate_empty_commits.sh.
#	2.	Сделайте его исполняемым: chmod +x generate_empty_commits.sh
# 3.	Запустите скрипт в корневой директории вашего Git-репозитория: ./generate_empty_commits.sh
# 4.  Скрипт создаст пустые коммиты с нужными временными метками для каждого дня в заданном диапазоне.


# Диапазон дат: с 2024-03-01 до 2024-05-01 (включительно)
start_date="2024-05-02"
end_date="2024-08-01"

# Получаем эпоху конечной даты
end_date_epoch=$(date -j -f "%Y-%m-%d" "$end_date" "+%s")

current_date="$start_date"
current_date_epoch=$(date -j -f "%Y-%m-%d" "$current_date" "+%s")

# Функция для генерации случайного времени в формате HH:MM:SS
generate_random_time() {
  hour=$(printf "%02d" $(( RANDOM % 24 )))
  minute=$(printf "%02d" $(( RANDOM % 60 )))
  second=$(printf "%02d" $(( RANDOM % 60 )))
  echo "${hour}:${minute}:${second}"
}

while [ "$current_date_epoch" -le "$end_date_epoch" ]; do
  # Генерируем случайное число коммитов от 1 до 6 (на macOS используется jot)
  num_commits=$(jot -r 1 1 6)
  echo "Дата: $current_date – создаём $num_commits пустых коммитов"

  for (( i=1; i<=num_commits; i++ )); do
    random_time=$(generate_random_time)
    timestamp="${current_date}T${random_time}"

    # Создаем пустой коммит с указанной датой
    GIT_AUTHOR_DATE="$timestamp" GIT_COMMITTER_DATE="$timestamp" \
      git commit --allow-empty -m "Пустой коммит от $timestamp"
  done

  # Переход к следующему дню (на macOS: флаг -v+1d)
  current_date=$(date -j -v+1d -f "%Y-%m-%d" "$current_date" "+%Y-%m-%d")
  current_date_epoch=$(date -j -f "%Y-%m-%d" "$current_date" "+%s")
done