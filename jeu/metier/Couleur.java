package jeu.metier;

public enum Couleur
{
	ROUGE ( "rouge"   ,255,  0,  0 ),
	VERT  ( "vert"    ,  0,255,  0 ),
	BLEU  ( "bleu"    ,  0,  0,255 ),
	JAUNE ( "jaune"   ,255,255,  0 ),
	VIOLET( "violet"  ,255,  0,255 ),
	CYAN  ( "cyan"    ,  0,255,255 );

	// attributs
	private String libelle;

	private int r;
	private int v;
	private int b;

	// constructeur
	Couleur(String libelle,int r,int v,int b)
	{
		this.libelle = libelle;

		this.r = r;
		this.v = v;
		this.b = b;
	}


	// accesseurs
	public String getLibelle() { return libelle; }

	public int getR() { return r; }
	public int getV() { return v; }
	public int getB() { return b; }

	public String getValeurs()
	{
		int valeur = r * 65536 + v * 256 + b;

		String sLibelle = String.format( "%-11s"        , libelle );
		String sNom     = String.format( "%-8s"         , name()  );
		String sValeur  = String.format( "%,11d"        , valeur  );
		String sRGB     = String.format( "[%3d,%3d,%3d]", r,v,b   );

		return sLibelle
			   + "("
			   + sNom
			   + ") "
			   + sValeur
			   + " , "
			   + sRGB;
	}

	public static String valueOf( int ord )
	{
		for( Couleur c : Couleur.values() )
		{
			if( c.ordinal() == ord ) return c.name();
		}

		return null;
	}
}