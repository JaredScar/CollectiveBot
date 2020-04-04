# CollectiveBot

## What is it?
CollectiveBot is a discord bot I made for my discord server named "Project Badger" in
which is a FiveM server community I run. CollectiveBot contains features such as a
suggestions system for accepting and denying suggestions, a welcome message system, as
well as some other planned features within the future. More will be added as time goes
on. Be sure to keep up to date with it :)
## How to install?
1. You will want to download CollectiveBot release on the `Releases` page
2. You will need to set up a Discord Bot and get it's discord token.
3. Put the token within `''` after `BotToken:`
4. Set up the modules you want enabled within the config
5. Set up the IDs of channels and emoji names within the config for SuggestionEmojis
6. Click `start_CollectiveBot` and start the bot
```
#######################
###  CollectiveBot  ###
###       by        ###
###     Badger      ###
#######################
BotToken: ''
Enables:
  SuggestionsAddon: true
  WelcomeMessage: true
SuggestionAcceptEmoji: ':YesEmoji:'
SuggestionDenyEmoji: ':NoEmoji:'
SuggestionApprovedEmoji: ':ApprovedEmoji:'
SuggestionDeniedEmoji: ':DeniedEmoji:'
SuggestionChannels:
  - '693985384907931719'
SuggestionDeniedChannel: '695294976908722229'
SuggestionApprovedChannel: '695294976908722229'
SuggestionRoles: # These are the roles that are allowed to approve and/or deny suggestions
  - '693833282240118794'

WelcomeMessage:
  Title: 'Welcome {USER-TAG} to Project Badger'
  TitleLink: ''
  Description: "**You're member #{MEMBER-COUNT} :tada:**"
  Footer: 'CollectiveBot by Badger'
  FooterIMG: 'https://i.gyazo.com/981a86c82860fc5ae5859bcd1bb0df09.gif'
  Thumbnail: '{USER-AVATAR}'
  Channel: '693836887651254283'
  Color: '0 254 0'
```
## Further Information
If you find yourself needing further assistance and/or help even afer reading 
this whole README file, please make sure to check out my documentation over 
at https://docs.badger.store/badger-software/collectivebot