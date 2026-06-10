# BUTINE ! — Conception du plateau
<img width="50" height="50" alt="abeille" src="https://github.com/user-attachments/assets/5ca6fe00-c427-471b-a5b7-311d06523254" />



Ce projet correspond à la partie conception du jeu **BUTINE !**.

Le but est de créer un plateau de jeu sur le thème des abeilles.  
L’utilisateur peut choisir les dimensions de la grille, la taille des cases, placer des régions colorées, ajouter des fleurs, puis exporter le plateau créé.

## Fonctionnalités

- Créer une grille personnalisée.
- Choisir la largeur, la hauteur et la taille des cases.
- Colorier les régions du plateau.
- Placer des fleurs avec différentes formes.
- Ajouter des départs pour les joueurs.
- Vérifier certaines contraintes du plateau.
- Exporter le plateau dans un fichier `plateau.data`.
- Importer un plateau déjà créé depuis la page d’accueil (Bouton Modifer).

## Lancement


Depuis la racine du projet :

### Sous Linux/MacOS

```bash
./compile.sh
```

### Sous Windows

```bash
javac @compile.list -d class

cd class && java conception.Controleur
```
