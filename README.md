# Welcome
## What is this repository?
> This repository will be used to upload source files for Liferay blogs I write. I will add a small instruction on how to use the source files for each blog I write.

## *Using Liferay fragments as mini templates* [Blog](https://blog.finalist.nl/en/blog/using-liferay-fragments-modular-webcontent-templates/)

### Basic solution
1. Add a content Structure with the structure from */documents/Structures*
2. Add all the mini template fragments with the code from */fragments/mini-templates* except for the complete-article fragments
3. Add a display page for the Structure Article and add the article fragment, set the display page as the default display page
4. Create an article, and view the article

### Improved solution
1. Add a content Structure with the structure from */documents/Structures*
2. Install the Template context contributor from */modules/liferay-template-context-contributor*
3. Add a fragment with the code from */fragments/mini-templates/complete-article* (Note: Or rewrite the mini templates to use the new services)
4. Add a display page for the Structure Article and add the article fragment, set the display page as the default display page
5. Create an article, and view the article


## *Removing panels from my account in Liferay 7.2* [Blog](https://blog.finalist.nl/en/blog/removing-panels-my-account-liferay-72)
### Installation instructions
1. Install the fragment bundle from */modules/admin-web-my-account-fragment (check if bundle version matches your environment)
2. Install the custom screen navigation module from */modules/custom-my-account-screennavigation-entries
3. Check the "My Account settings" screen