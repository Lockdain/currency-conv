package com.thereimlearningjava.currency_conv.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdatepicker.JDatePicker;

import com.thereimlearningjava.currency_conv.UIValidation.FieldValidator;
import com.thereimlearningjava.currency_conv.datamodel.DataCoreImpl;
import com.thereimlearningjava.currency_conv.datamodel.JsonResponsePojo;
import com.thereimlearningjava.currency_conv.remote.GsonUtils;
import com.thereimlearningjava.currency_conv.remote.UrlFactoryImpl;

/**
 * The main Swing GUI application window
 * @author Sergeenko
 * @version 0.0.1
 */
public class MainAppearance {

	private JFrame frmTheProfitlossCalculator;
	private JTextField txtEnterTheAmount;
	private JTextField profitField;
	private JTextField spreadField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppearance window = new MainAppearance();
					window.frmTheProfitlossCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainAppearance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmTheProfitlossCalculator = new JFrame();
		
		frmTheProfitlossCalculator.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.setResizable(false);
		frmTheProfitlossCalculator.setTitle("The Profit/Loss Calculator (fixer.io)");
		frmTheProfitlossCalculator.setBounds(100, 100, 520, 364);
		frmTheProfitlossCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmTheProfitlossCalculator.getContentPane().setLayout(springLayout);
		
		JLabel lblIveBought = new JLabel("I've bought:");
		springLayout.putConstraint(SpringLayout.NORTH, lblIveBought, 51, SpringLayout.NORTH, frmTheProfitlossCalculator.getContentPane());
		lblIveBought.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(lblIveBought);
		
		JLabel lblOn = new JLabel("on");
		springLayout.putConstraint(SpringLayout.NORTH, lblOn, 0, SpringLayout.NORTH, lblIveBought);
		lblOn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(lblOn);
		
		/**JDatePicker additional library component*/
		final JDatePicker datePicker = new JDatePicker();
		springLayout.putConstraint(SpringLayout.EAST, lblOn, -15, SpringLayout.WEST, datePicker);
		springLayout.putConstraint(SpringLayout.WEST, datePicker, 301, SpringLayout.WEST, frmTheProfitlossCalculator.getContentPane());
		
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker, 2, SpringLayout.SOUTH, lblIveBought);
		springLayout.putConstraint(SpringLayout.NORTH, datePicker.getButton(), -2, SpringLayout.NORTH, lblIveBought);
		springLayout.putConstraint(SpringLayout.NORTH, datePicker.getFormattedTextField(), -2, SpringLayout.NORTH, lblIveBought);
		datePicker.getFormattedTextField().setToolTipText("Select a date of the purchase");
		datePicker.setToolTipText("Select the day you bought the currency");
		datePicker.getFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(datePicker);	
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, -87, SpringLayout.SOUTH, frmTheProfitlossCalculator.getContentPane());
		
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, frmTheProfitlossCalculator.getContentPane());
		frmTheProfitlossCalculator.getContentPane().add(scrollPane);
		
		final JProgressBar progressBar = new JProgressBar();
		springLayout.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, scrollPane);
		frmTheProfitlossCalculator.getContentPane().add(progressBar);
		
		/**Logging component*/
		final JTextArea logArea = new JTextArea();
		scrollPane.setViewportView(logArea);
		springLayout.putConstraint(SpringLayout.NORTH, logArea, 80, SpringLayout.SOUTH, datePicker);
		springLayout.putConstraint(SpringLayout.WEST, logArea, 380, SpringLayout.WEST, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, logArea, -148, SpringLayout.SOUTH, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, logArea, -100, SpringLayout.EAST, frmTheProfitlossCalculator.getContentPane());
		logArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		logArea.setEditable(false);
		
		JLabel lblAndMyTodays = new JLabel("And my current profit is:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAndMyTodays, 50, SpringLayout.SOUTH, lblIveBought);
		springLayout.putConstraint(SpringLayout.WEST, lblAndMyTodays, 14, SpringLayout.WEST, frmTheProfitlossCalculator.getContentPane());
		lblAndMyTodays.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(lblAndMyTodays);

		/**Calculate button*/
		final JButton calculateButton = new JButton("Calculate profit");
		springLayout.putConstraint(SpringLayout.SOUTH, calculateButton, -8, SpringLayout.NORTH, progressBar);
		
		calculateButton.setToolTipText("Calculate");
		calculateButton.setVisible(false); //shouldn't be visible until the validation complete
		
		/**Mouse click listener*/
		calculateButton.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/**Rollback UI color changes*/
				datePicker.getFormattedTextField().setBackground(Color.WHITE); // already painted. rollback
				txtEnterTheAmount.setBackground(Color.WHITE); // also rollback
				profitField.setBackground(Color.WHITE); //also rollback
				progressBar.setValue(0); //also rollback
				logArea.setText(""); //also rollback
				calculateButton.setVisible(false);

				logArea.append("Calculating... \n"); //log
				progressBar.setValue(10);
				
				/**Creating an instance of the POJO*/
				DataCoreImpl data = new DataCoreImpl();
				/**Creating an instance of RemoteRequest*/
				UrlFactoryImpl remote = new UrlFactoryImpl();
				
				remote.createSellUrl(); //it's necessary to create this URL at first
				JsonResponsePojo sell = GsonUtils.parseResponse(GsonUtils.getJsonStringFromURL(remote.getSellRateUrl())); //create and parse sell POJO
				remote.setBuyDate(DataCoreImpl.createIsoDate(datePicker.getFormattedTextField().getText())); // !!! Get the buy date from Swing JDatePicker to the RemoteRequest Instance
				remote.createBuyRateUrl();
				
				progressBar.setValue(25);
				
				/**Creating current price POJO parsed from the fixer.io*/
				JsonResponsePojo buy = GsonUtils.parseResponse(GsonUtils.getJsonStringFromURL(remote.getBuyRateUrl()));
				 
				logArea.append("Buy rate is: " + buy.getRates().getRUB() + "\nCurrent rate is: " + sell.getRates().getRUB() + "\n");
	
				/**ConvertibleBlockImpl setters*/
				data.setBuyRate(buy.getRates().getRUB());
				data.setSellRate(sell.getRates().getRUB());
				data.setPrevDate(remote.getBuyDate());
				data.setAmount(Double.parseDouble(txtEnterTheAmount.getText()));
				data.setSpread(Double.parseDouble(spreadField.getText()));
				
				logArea.append("The core object has been created\n");
				progressBar.setValue(50);
				
				profitField.setText(String.valueOf(data.getResult())); //final profit result
				
				/**Paint the profit field depending on its value*/
				if (data.getResult() < 0) {
					profitField.setBackground(Color.RED);
				} else profitField.setBackground(Color.GREEN);
				
				logArea.append("Done!\n");
				progressBar.setValue(100);
				
				/**Mark not needed objects for GC*/
				data = null;
				remote = null;
				sell = null;
				buy = null;
				
				/**Call GC*/
				System.gc();
				
				FieldValidator.purgeValidators(); //set all UI validators to false
				
			}		
			
		});
		
		
		
		calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(calculateButton);
		/**
		 * Input amount text field action listener
		 */
		txtEnterTheAmount = new JTextField();

		springLayout.putConstraint(SpringLayout.NORTH, txtEnterTheAmount, -3, SpringLayout.NORTH, lblIveBought);
		springLayout.putConstraint(SpringLayout.EAST, txtEnterTheAmount, -294, SpringLayout.EAST, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblIveBought, -6, SpringLayout.WEST, txtEnterTheAmount);
		
		txtEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEnterTheAmount.setToolTipText("Enter the amount in EUR");
		frmTheProfitlossCalculator.getContentPane().add(txtEnterTheAmount);
		txtEnterTheAmount.setColumns(10);
		
		profitField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, calculateButton, 40, SpringLayout.SOUTH, profitField);
		springLayout.putConstraint(SpringLayout.WEST, calculateButton, 0, SpringLayout.WEST, profitField);
		springLayout.putConstraint(SpringLayout.EAST, calculateButton, 0, SpringLayout.EAST, profitField);
		springLayout.putConstraint(SpringLayout.EAST, profitField, 175, SpringLayout.EAST, lblAndMyTodays);
		profitField.setToolTipText("Your transaction profit");
		springLayout.putConstraint(SpringLayout.NORTH, profitField, -3, SpringLayout.NORTH, lblAndMyTodays);
		springLayout.putConstraint(SpringLayout.WEST, profitField, 16, SpringLayout.EAST, lblAndMyTodays);
		profitField.setEditable(false);
		profitField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTheProfitlossCalculator.getContentPane().add(profitField);
		profitField.setColumns(10);
		
		JLabel lblEur = new JLabel("EUR");
		springLayout.putConstraint(SpringLayout.WEST, lblOn, 23, SpringLayout.EAST, lblEur);
		springLayout.putConstraint(SpringLayout.WEST, lblEur, 3, SpringLayout.EAST, txtEnterTheAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEur, 0, SpringLayout.SOUTH, datePicker);
		frmTheProfitlossCalculator.getContentPane().add(lblEur);
		
		spreadField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, spreadField, 0, SpringLayout.NORTH, lblAndMyTodays);
		springLayout.putConstraint(SpringLayout.EAST, spreadField, -50, SpringLayout.EAST, frmTheProfitlossCalculator.getContentPane());
		spreadField.setEditable(false);
		spreadField.setToolTipText("A spread value");
		
		spreadField.setText("0.05");
		frmTheProfitlossCalculator.getContentPane().add(spreadField);
		spreadField.setColumns(10);
		
		JLabel lblSpread = new JLabel("Spread:");
		springLayout.putConstraint(SpringLayout.WEST, spreadField, 6, SpringLayout.EAST, lblSpread);
		springLayout.putConstraint(SpringLayout.WEST, lblSpread, 39, SpringLayout.EAST, profitField);
		springLayout.putConstraint(SpringLayout.EAST, lblSpread, -92, SpringLayout.EAST, frmTheProfitlossCalculator.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblSpread, 3, SpringLayout.NORTH, lblAndMyTodays);
		lblSpread.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTheProfitlossCalculator.getContentPane().add(lblSpread);		
		/**A checkbox to unlock spread edit field*/
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 0, SpringLayout.NORTH, lblAndMyTodays);
		springLayout.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 6, SpringLayout.EAST, spreadField);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (spreadField.isEditable()) {
					spreadField.setEditable(false);
				} else spreadField.setEditable(true);
			}
		});
		frmTheProfitlossCalculator.getContentPane().add(chckbxNewCheckBox);
		/**Verification button*/
		JButton btnNewButton = new JButton("Verify");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 43, SpringLayout.SOUTH, lblAndMyTodays);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblIveBought);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 15, SpringLayout.EAST, lblIveBought);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logArea.setText(""); //clean the log
				
				/**Verify amount field*/
				if((txtEnterTheAmount.getText().isEmpty()) || (txtEnterTheAmount.getText().equals("Enter the amount"))) {
					
					txtEnterTheAmount.setBackground(Color.YELLOW); //paint the background
					logArea.append("The currency amount field is not set \n"); //add a note to the log
					//JOptionPane.showMessageDialog(frmTheProfitlossCalculator, "The exchange date isn't set"); //show a message to a user
					FieldValidator.amountIsDefined(false);
				} else FieldValidator.amountIsDefined(true); //assign a validation result
				if(!txtEnterTheAmount.getText().isEmpty()) {
					if(!UtilsUI.isDouble(txtEnterTheAmount.getText())) { //this method returns false if the input string doesn't fit the double regex
						logArea.append("The amount value should be double\n");
						txtEnterTheAmount.setBackground(Color.YELLOW);
						txtEnterTheAmount.setText("");
						FieldValidator.amountIsDouble(false);
				} else {
					FieldValidator.amountIsDouble(true);
					txtEnterTheAmount.setBackground(Color.GREEN);
				}
				}
				
				/**Date picker validation*/
				/**Check if date picker field is empty*/
				if(datePicker.getFormattedTextField().getText().isEmpty()) {
					
					datePicker.getFormattedTextField().setBackground(Color.YELLOW); //check whether the buying date has been set
					logArea.append("The exchange date is not set \n"); //log
					FieldValidator.datePickerIsDefined(false);
				} else FieldValidator.datePickerIsDefined(true);
				/**Check if selected date is from future*/
				if(!datePicker.getFormattedTextField().getText().isEmpty()) {
					if(UtilsUI.isFutureDate(DataCoreImpl.createIsoDate(datePicker.getFormattedTextField().getText()))) { //date is not from future
						datePicker.getFormattedTextField().setText("");
						logArea.append("You have selected a future date\n");
						datePicker.getFormattedTextField().setBackground(Color.YELLOW);
						FieldValidator.datePickerIsNotFuture(false); //switch the validator
				} else {
					FieldValidator.datePickerIsNotFuture(true);
					datePicker.getFormattedTextField().setBackground(Color.GREEN);
				}
			 }
				
				/**Spread validation*/
				if(!spreadField.getText().isEmpty()) //need to check if string is not empty
					if(!UtilsUI.isDouble(spreadField.getText())) { //if it is not double
						logArea.append("The spread should be double\n");
						spreadField.setText("0.05"); //rollback the value
						//spreadField.setBackground(Color.YELLOW);
						FieldValidator.spreadIsDefined(false);
				} else {
					FieldValidator.spreadIsDefined(true);
					spreadField.setBackground(Color.GREEN);

				}
			
				/**Final validate*/
				if(FieldValidator.validated()) {
					calculateButton.setVisible(true);
					txtEnterTheAmount.setBackground(Color.WHITE);
					datePicker.getFormattedTextField().setBackground(Color.WHITE);
					logArea.setText("");
					logArea.append("OK!");
				} else {
					calculateButton.setVisible(false);
					logArea.append("Please check the values\n");
				}
			}
		});
		
		btnNewButton.setToolTipText("Click for input verification");
		frmTheProfitlossCalculator.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clean All");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, lblIveBought);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -6, SpringLayout.NORTH, progressBar);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		/**Clean all text fields*/
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnterTheAmount.setText("");
				//spreadField.setText("");
				datePicker.getFormattedTextField().setText("");
			}
		});
		btnNewButton_1.setToolTipText("Clean all fields");
		frmTheProfitlossCalculator.getContentPane().add(btnNewButton_1);
		
		JLabel lblRub = new JLabel("RUB");
		springLayout.putConstraint(SpringLayout.NORTH, lblRub, 3, SpringLayout.NORTH, lblAndMyTodays);
		springLayout.putConstraint(SpringLayout.WEST, lblRub, 2, SpringLayout.EAST, profitField);
		frmTheProfitlossCalculator.getContentPane().add(lblRub);
		
	}
}
