package cl.lai.datamining.evaluacion3;

import weka.core.Instances;

public class Ejercicio_02 extends App_loader_data_set{
	
	public static void main(String args[])throws Exception{
		String current = new java.io.File( "." ).getCanonicalPath()+"/src/resources/";
		String file = current + "censo_gse_gastos.csv";
		 
		Instances datosEntrenamiento = load_normal_data_set(file);
		//TODO Implementar 
 	}
}
