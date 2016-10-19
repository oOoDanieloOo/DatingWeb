package abd.p1.model;

public enum Sexo
{
	HOMBRE, MUJER;
	
	@SuppressWarnings("unused")
	private int code;
	
	public static Sexo getSexoFromString(String sexo)
	{
		switch (sexo)
	    {
	    	case "Masculino":
	    		return Sexo.HOMBRE;
	    	case "Femenino":
	    		return Sexo.MUJER;
	    	default:
	    		return null;
	    }
	}
	
	public static String getStringFromSexo(Sexo sexo)
	{
		switch (sexo)
	    {
	    	case HOMBRE:
	    		return "Masculino";
	    	case MUJER:
	    		return "Femenino";
	    	default:
	    		return null;
	    }
	}
	
	public static Integer getGender(Sexo sexo)
	{
		switch (sexo)
	    {
	    	case HOMBRE:
	    		return 0;
	    	case MUJER:
	    		return 1;
	    	default:
	    		return null;
	    }
	}
}
