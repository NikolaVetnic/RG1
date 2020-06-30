package topic2_P_image_processing.filters.binary;

import javafx.scene.image.Image;


/**
 * Filter je funkcija transformacije slike. Za zadatu ulaznu sliku primenom filtera dobijamo izlaznu sliku. 
 */
public abstract class Filter {
	public abstract Image process(Image input);
}
