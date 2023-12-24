# Bienvenue dans POO-Kemon !!!

POO-Kemon est un petit jeu de combat au tour par tour. Le jeu possède une base de données textuelle facile à éditer qui permet de facilement intégrer au jeu de nouveaux monstres, attaques ou objets sans même avoir à modifier son code.

## Diverses expériences de jeux
### Envie de stratégie ?
- Construisez votre équipe pas à pas en navigant au sein du POO-Kedex.
- Choisissez avec soin les objets avec lesquels vous partirez au combat.
### Plutôt aventurier ?
- Partez au combat avec une équipe totalement aléatoire !
- Choisir ses objets, c'est douter de son talent. Vous pouvez choisir de partir avec un sac d'objets aléatoire !

## Une volonté de modularité
- Un nombre de joueur limité à la valeur maximale d'un entier occupant 32 bits
- Une gestion des combats adaptée à beaucoup de configurations simples ( Modification du nombre de monstres dans les équipe ou du nombre d'objets dans le sac en modifiant une simple valeur )

## Une ergonomie réfléchie
- Qui a dit qu'un jeu uniquement disponible sur terminal pouvait se permettre d'être désagréable à regarder ? Un grand soin a été porté aux graphismes textuels du jeu afin de permettre une expérience de jeu la plus agréable possible.
- Pourquoi demander un choix lorsqu'il n'y a qu'une possibilité ? Si vous demandez à attaquer avec un monstre et qu'il n'a qu'une seule attaque disponible, elle sera automatiquement sélectionnée. De même, s'il n'y a qu'un adversaire face à vous, pourquoi choisir qui cibler ? Le jeu s'en occupe.

## Comment jouer ?
Ce jeu étant entièrement codé en Java, il est nécessaire de le compiler pour l'utiliser.
### Compilation
Dans le dossier principal :
- Windows : javac "@compile.list"
- Linux : javac @compile.list
### Exécution
Dans le dossier principal :
java com.esiea.pootp1.POOKemon
