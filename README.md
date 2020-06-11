# Welcome
## What is this repository?
> This repository will be used to upload source files for Liferay blogs I write. I will add a small instruction on how to use the source files for each blog I write.

## *Using Liferay fragments as mini templates*
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