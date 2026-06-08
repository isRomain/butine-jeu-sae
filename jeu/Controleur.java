package jeu;

import jeu.metier.Pile;

public class Controleur 
{
	public static void main(String[] args) 
	{
		Pile pile = new Pile();

		for (int i = 0; i < 13; i++)
			System.out.println(pile.piocher());
	}
}
