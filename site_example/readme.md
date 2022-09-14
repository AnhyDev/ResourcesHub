# Anhydrite

## Instructions for creating a web page for the sale of ANB tokens in the Anhydrite project.


#### In order to create a website or page from our template, download and unzip the contents of the file:
#### https://github.com/Anhydr1te/Anhydrite/raw/main/site_example/anhydrite_site.zip

### Make a few simple changes:


1. In the file `index.html`, you can change the name of the pyramid in the line 23.

2. In the `index.html` file, you can change the language file in the line 54.

3. In the `/assets/js/anh_example.js` file, in the line 3, in the `prm_address` variable, enter the address of the pyramid you need:

	- Purple Pyramid: `0x15e584A1527EF01Dd0cF6C1D1d140cD5dE9D65cC`
	- Green Pyramid:  `0xF855294bd9573698380dFC4e25054b2FA9c57E9B`
	- Yellow Pyramid: `0x586b3EbCAd926867B3C6329Fa6b1D79A74B50249`
	- Orange Pyramid: `0x0CB3765bC673Ecfc55Fa36Ced05aC83572313e21`
	- Red Pyramid:    `0xD44DFd8230cF2A821F76C2B1F0679028ff7c084e`

4. In the `assets/js/anh_example.js` file, in the line 4, in the `own_address` variable, enter the address of your wallet from which you bought the token in this pyramid. This is necessary so that all tokens that will be sold contain your address and bring you profit.

5. In the folder `/images/` there are files `bg_red.jpg`, `bg_orange.jpg`, `bg_yellow.jpg`, `bg_green.jpg`, `bg_purple.jpg`, select the one you prefer for the background and rename it in `bg.jpg`
   or you can add your own file of a similar type.


If you understand what you are doing, you may edit all files to your liking and even copy any information from the main site https://anh.ink.

#### Examples of pages with our proposed format:

##### Purple Pyramid: https://purple.anh.ink/
##### Green Pyramid:  https://green.anh.ink/
##### Yellow Pyramid: https://yellow.anh.ink/
##### Orange Pyramid: https://orange.anh.ink/
##### Red Pyramid): https://red.anh.ink/

This page will interact with the pyramids using the `MetaMask` browser wallet, if you want it to be available in the `MetaMask` mobile App too, your site must use `SSL encryption`, i.e. the address must start with `https`.