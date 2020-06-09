/**
 * Sample Skeleton for 'ExtFlightDelays.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Adiacenza;
import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.StatoTuristi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExtFlightDelaysController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxStati"
    private ComboBox<String> cmbBoxStati; // Value injected by FXMLLoader

    @FXML // fx:id="btnVisualizzaVelivoli"
    private Button btnVisualizzaVelivoli; // Value injected by FXMLLoader

    @FXML // fx:id="txtT"
    private TextField txtT; // Value injected by FXMLLoader

    @FXML // fx:id="txtG"
    private TextField txtG; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	List<String> vertex = this.model.buildGrah();
    	cmbBoxStati.getItems().setAll(vertex);
    	
    	txtResult.appendText(String.format("Grafo creato!\nVertici: %d\nArchi: %d\n", this.model.getNumVertex(), this.model.getNumEdge()));
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	String state = cmbBoxStati.getValue();
    	if(state == null) {
    		txtResult.appendText("Selezionare uno stato!");
    		return;
    	}
    	
    	Integer T = null;
    	Integer G = null;
    	try {
    		T = Integer.parseInt(txtT.getText());
    		G = Integer.parseInt(txtG.getText());
    	} catch(NumberFormatException e) {
    		txtResult.appendText("Inserire valori numerici positivi!");
    		return;
    	}
    	if(T <= 0 || G <= 0) {
    		txtResult.appendText("Inserire valori numerici positivi maggiori di zero!");
    		return;
    	}
    	
    	List<StatoTuristi> result = this.model.simula(T, G, state);
    	Integer somma = 0;
    	for(StatoTuristi st : result) {
    		txtResult.appendText(st.toString()+"\n");
    		somma += st.getTuristi();
    	}
    		
    	txtResult.appendText("\n"+somma+"\n");
    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	txtResult.clear();
    	String state = cmbBoxStati.getValue();
    	if(state == null) {
    		txtResult.appendText("Selezionare uno stato!");
    		return;
    	}
    	
    	List<Adiacenza> result = this.model.getAdicenze(state);
    	txtResult.appendText("Adiacenti dello stato "+state+"\n");
    	for(Adiacenza a : result) {
    		txtResult.appendText(a.toString()+"\n");
    	}
    	
    }
    
    public void setModel(Model model) {
		this.model = model;	
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }
}