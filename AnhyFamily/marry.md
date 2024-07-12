### AnhyFamily Плагін: Одруження

Плагін AnhyFamily дозволяє гравцям одружуватися, створюючи сім'ї у грі Minecraft. За замовчуванням одружитися можуть лише чоловік та жінка, але це можна змінити у конфігурації плагіна:

```yaml
# Дозволити шлюби для небінарних та невизначених статей
non_binary_marriage: false
```

#### Вимоги до Одруження

1. **Ім'я та Прізвище:** У кожного з наречених має бути встановлено ім'я та прізвище.
2. **Родинні зв'язки:** Наречені не можуть бути родичами (предками чи нащадками один одного).
3. **Оплата за церемонію:** Якщо це вказано у конфігурації, то за церемонію одруження у наречених буде знято оплату. Платіжні засоби можуть бути віртуальними, предметами чи криптовалютою (останнє ще не реалізовано).

```yaml
prices:
  # Віртуальні гроші, предмети, криптовалюта (ще не реалізовано)
  currency: VIRTUAL
  marriage: 0
  divorce: 0
  adoption: 0
```

#### Типи Одружень

Існує два типи одружень: публічне та приватне.

##### Публічне Одруження

Публічна церемонія проводиться за участі священника і може відбуватися у будь-якому місці.

- **Команда для священника:**  
  ```
  /marry public player1 player2 [0|1|2]
  ```
  Останній числовий аргумент (необов'язковий) визначає спільне прізвище:
  - `0` - кожен зберігає своє прізвище.
  - `1` - другий гравець бере прізвище першого.
  - `2` - перший гравець бере прізвище другого.
  - Якщо не вказати нічого, рівнозначно `1`.

- **Процес:**  
  Після введення команди священником, обом нареченим відправляється запит з пропозицією погодитися або відмовитися. При згоді відбувається одруження, при відмові церемонія скасовується.

- **Чат-повідомлення:**  
  Повідомлення священника та шлюбні клятви гравців можуть бачити або всі на сервері, або лише ті, хто є у певному радіусі від місця церемонії.

```yaml
# Максимальна відстань від священника до наречених. Якщо 0, то обмежень немає.
ceremonyRadius: 20
# Радіус, у якому повідомлення про одруження буде видно в чаті. Якщо 0, то всі гравці, які онлайн.
ceremonyHearingRadius: 200
```

##### Приватне Одруження

Приватна церемонія проводиться лише за участі наречених у визначеній локації.

- **Конфігурація локації:**
  ```yaml
  privateCeremony:
    world: "world"
    x: 100
    y: 64
    z: -200
  ```
  Наречені повинні бути в радіусі 10 блоків від вказаних координат.

- **Команда для наречених:**
  ```
  /marry private player
  ```
  Гравець, зазначений у команді, отримує запит на підтвердження або відмову від церемонії. При згоді відбувається одруження, при відмові - церемонія скасовується.

- **Чат-повідомлення:**  
  Про церемонію можуть дізнатися з чату лише гравці, які знаходяться поряд.

#### Після Одруження

Після одруження створюється сімейний об'єкт, який зберігає інформацію про:

- Сімейну локацію дому.
- Сімейну скриню.
- Список батьків та дітей обох наречених.
- Доступ до сімейного чату, дому, скрині та інші права.

Сімейний об'єкт існує, доки існує сім'я, тобто до розлучення.

Ця система забезпечує інтерактивний та багатогранний підхід до створення та управління сім'ями у грі Minecraft.