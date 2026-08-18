# BUTINE ! — Conception du plateau

### ⚠ Branche Jeu pour jouer, branche Conception pour créer le plateau. ⚠

## **Présentation**

Ce projet porte sur la réalisation de la partie jeu d’un plateau inspiré de l’univers des abeilles.
Les joueurs y contrôlent une abeille dont l’objectif est de se déplacer sur le plateau afin de récolter un maximum de fleurs tout en respectant les règles de déplacement imposées par le jeu.

**Objectif**
Les joueurs s’affrontent pour maximiser à la fois : leur déplacement sur le plateau, et leur récolte de fleurs sur un plateau composé de plaines.
Le joueur qui obtient le meilleur score à la fin de la partie remporte la victoire.

### **Mécaniques du jeu**
 **Début de partie**
	Le jeu commence avec un plateau prédéfini et plusieurs points de départ, chacun associé à une couleur distincte pour chaque joueur.

* **Déplacement**
L’abeille (joueur) doit se déplacer sur le plateau et butiner les fleurs en s’avancer dessus. 🍯

* Les règles sont simples :

	* Il est interdit d’enjamber ou d’emprunter le chemin déjà utilisé par une autre abeille.

	* L’abeille peut se déplacer de manière horizontale, verticale ou en diagonale, mais uniquement si une fleur est accessible sur la trajectoire.

	* Il n’est pas possible d’avancer d’une case si la case suivante ne contient pas de fleur.

	* En revanche, si une fleur se trouve plusieurs cases plus loin (par exemple à 5 cases), l’abeille peut emprunter tout ce chemin de 5 cases pour atteindre directement cette fleur.

	* Un autre joueur ne peut pas emprunter ton chemin, mais il peut accéder à la même fleur que toi.

Pour faciliter le jeu, les chemins possibles sont visibles sur le plateau.

### **Récolte de fleurs**

À chaque arrivée sur une case contenant des fleurs, les joueurs gagnent le nombre de points associés à ces fleurs.
La quantité de fleurs disponible par case varie selon la configuration du jeu et les règles mises en place.


### **Calcul des points**

Les points se calculent de la manière suivante :

```
Score = ( nombre de fleurs maximum présent dans une zone du plateau ) ×
		( le nombre de zones différents que l’abeille a occupées durant la partie )
```


## Lancement


Depuis la racine du projet :

### Sous Linux/MacOS

```bash
./compile.sh
```

### Sous Windows

```bash
javac @compile.list -d class

cd class && java jeu.Controleur
```
