package src.metier;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public class Case
{
	private Color  plaine;
	private String fleur;
	private String depart;

	public Case ()
	{
		this.fleur  = "vide";
		this.plaine = new Color(255, 255, 255, 200);
		this.depart = "vide";
	}

	public void setPlaine ( Color plaine  ) { this.plaine = plaine; }
	public void setFleur  ( String fleur  ) { this.fleur  = fleur ; }
	public void setDepart ( String depart ) { this.depart = depart; }

	public String getFleur  () { return this.fleur; }
	public Color  getPlaine () { return this.plaine; }
	public String getDepart () { return this.depart; }

	public Image getImageFleur ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/pollens/pollen_" + this.fleur + ".png");
	}

	public Image getImageDepart ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/contours/contour_case_" + this.depart + ".png");
	}
}