package abd.p1.model;

public enum Preferencia
{
	HOMBRE, MUJER, AMBOS;
	
	@SuppressWarnings("unused")
	private int code;
	
	public static Integer getPreference(Preferencia preference)
	{
		switch (preference)
	    {
	    	case HOMBRE:
	    		return 0;
	    	case MUJER:
	    		return 1;
	    	case AMBOS:
	    		return 2;
	    	default:
	    		return null;
	    }
	}
	
	public static Preferencia getPreferenciaFromString(String preference)
	{
		switch (preference)
	    {
	    	case "Hombres":
	    		return Preferencia.HOMBRE;
	    	case "Mujeres":
	    		return Preferencia.MUJER;
	    	case "Ambos":
	    		return Preferencia.AMBOS;
	    	default:
	    		return null;
	    }
	}
	
	public static String getStringFromPreferencia(Preferencia preference)
	{
		switch (preference)
	    {
	    	case HOMBRE:
	    		return "Hombres";
	    	case MUJER:
	    		return "Mujeres";
	    	case AMBOS:
	    		return "Ambos";
	    	default:
	    		return null;
	    }
	}
}
