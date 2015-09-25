package gob.pe.proc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	
	public static boolean isInteger(String numero){
		for(int z=0;z<numero.length();z++){				
			if(!Character.isDigit(numero.charAt(z))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCadena(String cadena){
		for(int z=0;z<cadena.length();z++){				
			if(!Character.isAlphabetic(cadena.charAt(z))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isCantidadDigitos(String numero,Integer cifras){
		if(numero.length()!=cifras){
			return false;
		}
		return true;
	}
	
	public static boolean isDateValid(String dateToValidate) {
		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {

			sdf.parse(dateToValidate);

		} catch (ParseException e) {
			return false;
		}

		return true;
	}
	
	public static boolean isCuaderno(Integer cuaderno){
		if(cuaderno<=0 && cuaderno>6){
			return false;
		}
		return true;
	}
	public static String cambiarFormatoDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String fecha=sdf.format(date);
		return fecha;
	}
	
	//para la insercion hacia la base de datos
    public static synchronized java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
    
            try {
				fechaEnviar = formatoDelTexto.parse(fecha);
				return fechaEnviar;
            } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
    }
    
    public static synchronized java.util.Date deStringToDate(String fecha, String format) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(format);
        Date fechaEnviar = null;
        try {
			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
        } catch (java.text.ParseException e) {
        	e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean validateEmail(String email) {
         Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
   
 
}
