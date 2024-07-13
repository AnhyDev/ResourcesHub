### AnhyFamily Плагін: Конфігураційний Файл

Конфігураційний файл плагіна AnhyFamily дозволяє налаштувати різні аспекти плагіна, від баз даних до сімейних функцій. Нижче наведено детальний опис кожного параметра конфігурації.

#### Основні Налаштування

- **language**: Мова за замовчуванням, яка буде використовуватися, якщо переклад не знайдено в бажаній мові.
  ```yaml
  language: en
  ```

- **plugin_name**: Назва плагіна, яка буде відображатися користувачам у чаті.
  ```yaml
  plugin_name: AnhyFamily
  ```

#### Налаштування Бази Даних

- **database**: Налаштування для підключення до бази даних. Можливі типи баз даних: 'SQLite' або 'MySQL'.
  ```yaml
  database:
    type: 'SQLite'
    mysql:
      host: 'db4free.net'
      port: 3306
      database: 'anhyfamily'
      username: 'anhydev'
      password: 'HE5rYZb2hygDf4FW'
      prefix: 'anhy_'
      useSSL: false
      autoReconnect: true
  ```

#### Налаштування Цін

- **prices**: Налаштування валюти та цін на різні дії в плагіні.
  ```yaml
  prices:
    currency: VIRTUAL
    marriage: 0
    divorce: 0
    adoption: 0
  ```

#### Налаштування Статі

- **gender**: Налаштування, що дозволяють вибір небінарної статі та відповідні права для усиновлення та одруження.
  ```yaml
  gender:
    non_binary: false
    non_binary_adoption: false
    non_binary_marriage: false
  ```

#### Налаштування Церемоній

- **ceremonyRadius**: Максимальна відстань від священника до наречених. Якщо 0, то обмежень немає.
  ```yaml
  ceremonyRadius: 20
  ```

- **ceremonyHearingRadius**: Радіус, у якому повідомлення про одруження буде видно в чаті. Якщо 0, то всі гравці, які онлайн.
  ```yaml
  ceremonyHearingRadius: 200
  ```

- **marriedSymbol**: Символ, що використовується для позначення одружених гравців.
  ```yaml
  marriedSymbol: "⚭"
  ```

#### Налаштування Приватної Церемонії

- **privateCeremony**: Локація для проведення приватної церемонії одруження.
  ```yaml
  privateCeremony:
    world: "world"
    x: 100
    y: 64
    z: -200
  ```

#### Налаштування Сімейного Хому

- **home**: Налаштування для сімейного хому.
  ```yaml
  home:
    timeout: 1440
    world: false
  ```

#### Налаштування Сімейної Скрині

- **chest**: Налаштування для сімейної скрині.
  ```yaml
  chest:
    command: true
    distance: 0
    world: false
    click: true
    material:
      - CHEST
      - BARREL
    distance_to_home: 20
  ```

#### Налаштування Імен та Прізвищ

- **languages_limitation**: Налаштування обмежень для імен та прізвищ за допомогою регулярних виразів.
  ```yaml
  languages_limitation: "^(?!.*[-']{2})(?!.*--)(?!.*'')\\p{L}['-]*[\\p{L}]+$"
  ```

### Приклад Конфігураційного Файлу

```yaml
#
# ░█████╗░███╗░░██╗██╗░░██╗██╗░░░██╗███████╗░█████╗░███╗░░░███╗██╗██╗░░██╗░░░██╗
# ██╔══██╗████╗░██║██║░░██║╚██╗░██╔╝██╔════╝██╔══██╗████╗░████║██║██║░░╚██╗░██╔╝
# ███████║██╔██╗██║███████║░╚████╔╝░█████╗░░███████║██╔████╔██║██║██║░░░╚████╔╝░
# ██╔══██║██║╚████║██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██║██║╚██╔╝██║██║██║░░░░╚██╔╝░░
# ██║░░██║██║░╚███║██║░░██║░░░██║░░░██║░░░░░██║░░██║██║░╚═╝░██║██║███████╗██║░░░
# ╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚══════╝╚═╝░░░
#
language: en
plugin_name: AnhyFamily

database:
  type: 'SQLite'
  mysql:
    host: 'db4free.net'
    port: 3306
    database: 'anhyfamily'
    username: 'anhydev'
    password: 'HE5rYZb2hygDf4FW'
    prefix: 'anhy_'
    useSSL: false
    autoReconnect: true

prices:
  currency: VIRTUAL
  marriage: 0
  divorce: 0
  adoption: 0

gender:
  non_binary: false
  non_binary_adoption: false
  non_binary_marriage: false

ceremonyRadius: 20
ceremonyHearingRadius: 200
marriedSymbol: "⚭"

privateCeremony:
  world: "world"
  x: 100
  y: 64
  z: -200

home:
  timeout: 1440
  world: false

chest:
  command: true
  distance: 0
  world: false
  click: true
  material:
    - CHEST
    - BARREL
  distance_to_home: 20

languages_limitation: "^(?!.*[-']{2})(?!.*--)(?!.*'')\\p{L}['-]*[\\p{L}]+$"
```

Цей файл конфігурації дозволяє налаштувати плагін AnhyFamily відповідно до ваших потреб, забезпечуючи гнучке управління базою даних, цінами, гендерними параметрами, церемоніями, сімейними локаціями та обмеженнями для імен та прізвищ.


[<<< Попередня сторінка: Обнімашки](hugs.md) | [Наступна сторінка: Плейсхолдери >>>](placeholders.md)
