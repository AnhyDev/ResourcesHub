# Anhydrite

## Інструкція по створенню веб-сторінки для продажу токенів ANB в проєктш Anhydrite.


#### Для того, щоб створити сайт чи сторінку з нашогоо шаблону, скачайте та розархівуйте вміст файлу:
#### https://github.com/Anhydr1te/Anhydrite/raw/main/site_example/anhydrite_site.zip

### Внесите дукілька простих змін:


1. В файлі `index.html`, рядок 23 можете замінити назву піраміди.

2. В файлі `index.html`, в рядку 54, можете замінити мовний файл.

3. В файлі `/assets/js/anh_example.js`, в рядку 3, в змінну `prm_address` внесить адресу потрібної вам піраміди:

	- Purple Pyramid: `0x15e584A1527EF01Dd0cF6C1D1d140cD5dE9D65cC`
	- Green Pyramid:  `0xF855294bd9573698380dFC4e25054b2FA9c57E9B`
	- Yellow Pyramid: `0x586b3EbCAd926867B3C6329Fa6b1D79A74B50249`
	- Orange Pyramid: `0x0CB3765bC673Ecfc55Fa36Ced05aC83572313e21`
	- Red Pyramid):   `0xD44DFd8230cF2A821F76C2B1F0679028ff7c084e`

4. В файлі `assets/js/anh_example.js`, в рядку 4, в змінну `own_address` внесіть адресу свого гаманця, з якого купували токен в цій піраміді. Це потрібно для того, щоб всі токени, які будуть продаватися містили вашу адресу та приносили вам прибутки.

5. В папці `/images/` є файли `bg_red.jpg`, `bg_orange.jpg`, `bg_yellow.jpg`, `bg_green.jpg`, `bg_purple.jpg`, оберіть той, що вам потрібен для фону та переіменуйте його в `bg.jpg`
   або можете добавити свій файл аналогичного типу.


Якщо ви розумієтк що робите, можете редагувати всі файли за власним бажанням а також копіювати будь-яку інформацію з головного сайту https://anh.ink

#### Приклади сторінок з запропонованим форматом:

	- Purple Pyramid: https://purple.anh.ink/
	- Green Pyramid:  https://green.anh.ink/
	- Yellow Pyramid: https://yellow.anh.ink/
	- Orange Pyramid: https://orange.anh.ink/
	- Red Pyramid):   https://red.anh.ink/


Ця сторінка буде взаємодіяти з пірамідами використовуючи браузерний гаманець MetaMask, якщо ви хочете щоб вона також булл доступна в мобільному додатку MetaMask, ваш сайт повинен використовувати `SSL шифровання`, тобто адреса повинна починатися з `https`