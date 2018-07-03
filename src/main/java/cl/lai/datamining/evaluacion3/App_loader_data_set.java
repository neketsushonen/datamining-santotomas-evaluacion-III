package cl.lai.datamining.evaluacion3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class App_loader_data_set {
	public static Instances load_normal_data_set(String file)throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
   	 
    	Map<Integer,String> correspondencia = new HashMap<Integer,String>();
    	
    	String aux = reader.readLine();
    	
    	String arrays[] = aux.split(";");
    	String claseAtributo = arrays[arrays.length-1];
    	ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    	for(int i=1; i<arrays.length-1;i++){
    		attributes.add(new Attribute(arrays[i]));
    	}
    	//considerar el ultimo atributo como target 
    	int filas = 1;
    	ArrayList<String> clasesPreviamenteDefinida = new ArrayList<String>(); 
    	while((aux=reader.readLine())!=null){
    		arrays = aux.split(";");
    		if(!clasesPreviamenteDefinida.contains(arrays[arrays.length-1]) )
    			clasesPreviamenteDefinida.add(arrays[arrays.length-1]);
    		filas++;
    	}
    	Attribute classAttribute = new Attribute(claseAtributo,clasesPreviamenteDefinida);
    	attributes.add(classAttribute);
    	
    	Instances isTrainingSet = new Instances("traning", attributes, filas);
    	isTrainingSet.setClassIndex(classAttribute.index());
    	
    	reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
    	reader.readLine();
    	filas = 1;
    	//Lectura de cada instancia
      	while((aux=reader.readLine())!=null){
     		arrays = aux.split(";");
     		correspondencia.put(filas,arrays[0]);
     		
     		DenseInstance inst = new DenseInstance(isTrainingSet.numAttributes());
     		for(int at=0,   i=1; i<arrays.length-1;i++,at++){
     			double valor = Double.parseDouble(arrays[i]);
     			inst.setValue(attributes.get(at), valor);
        	}
     		inst.setValue(classAttribute, arrays[arrays.length-1]);
     		 
     		isTrainingSet.add(inst);
     		filas++;
    	} 
      	return isTrainingSet;
	}
}
