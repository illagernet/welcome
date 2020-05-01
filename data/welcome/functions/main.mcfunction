# Give new players a Totem of Undying
give @a[tag=!known] minecraft:totem_of_undying

# Greet new players and prompt them to join the Discord server
tellraw @a[tag=!known] [{"text":"Welcome to Illager Net! ","color":"yellow"},{"text":"Join the Discord server","color":"blue","underlined":true,"clickEvent":{"action":"open_url","value":"http://illager.net/discord"}}]

# Remind online players to greet new players
execute as @a[tag=!known] run tellraw @a[tag=known] [{"text":"Welcome ","color":"yellow"},{"selector":"@s"},{"text":" to the server!"}]

# Tag welcomed players
tag @a[tag=!known] add known
