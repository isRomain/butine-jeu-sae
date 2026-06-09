package jeu;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Joue un fichier audio WAV en boucle pour la musique de fond de l'application.
 */
public class Musique
{
	private Clip clip;

	public Musique(String chemin)
	{
		try
		{
			File fichier = new File(chemin);
			AudioInputStream flux = AudioSystem.getAudioInputStream(fichier);

			this.clip = AudioSystem.getClip();
			this.clip.open(flux);
		}
		catch (Exception error)
		{
			System.err.println("Erreur lors du chargement de la musique : " + error.getMessage());
		}
	}

	public void jouer()
	{
		if (this.clip != null)
			this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void arreter()
	{
		if (this.clip != null)
			this.clip.stop();
	}
}
