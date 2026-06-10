# BUTINE ! — Conception du plateau
<img width="50" height="50" alt="abeille" src="https://github.com/user-attachments/assets/5ca6fe00-c427-471b-a5b7-311d06523254" />



Ce projet se concentre sur la partie de jeu d'un plateau de jeu inspiré par les abeilles. 🐝 

**Objectif :** Les joueurs s'affrontent pour maximiser à la fois leur déplacement et leur récolte de fleurs sur un plateau composés de plaine.  

**Mécaniques du Jeu:**

* **Début:** Le joueur commence avec un plateau de jeu pré-déterminé et plusieurs points de départ avec chacun, une couleur distinct.
* **Déplacement :** L'abeille (joueur) doit se déplacer sur le plateau et butiner les fleurs en s'avançant dessus. 🍯 Les règles sont simples : il est interdit d'enjamber le chemin d'une autre abeille. L'abeille peut se déplacer de manière horizontale, verticale ou en diagonale seulement si une fleur est accessible. Sur le plateau, des chemins possibles seront visibles pour l’actionne.
* **Récolte de Fleurs :** À chaque arrivée sur une case avec des fleurs, les joueurs gagnent le nombre de points associés aux fleurs. La quantité de fleurs disponible par espace variera en fonction du jeu et des règles mises en place. 
* **Scoring :** Les joueurs gagnent des points selon un système de score complexe:

    * **Maximum de Fleurs par Zone:** (Nombre de Fleurs maximum d'une zone) * Nombre de Zones occupées par l'abeille.


## Lancement


Depuis la racine du projet :

### Sous Linux/MacOS

```bash
./compile.sh
```

### Sous Windows

```bash
javac @compile.list -d class
```

```bash
cd class && java jeu.Controleur
```