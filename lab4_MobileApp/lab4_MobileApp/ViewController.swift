//
//  ViewController.swift
//  lab4_MobileApp
//
//  Created by Kai Schuyler on 9/28/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var decimalStepper: UIStepper!
    
    @IBOutlet weak var decimalInput: UITextField!
    @IBOutlet weak var hexInput: UITextField!
    
    @IBOutlet weak var decimalCalculation: UILabel!
    @IBOutlet weak var hexCalculation: UILabel!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        decimalInput.delegate=self
        hexInput.delegate=self
    }

    func calculateDecimal(hexValue:String) {
        if let value = Int(hexValue, radix: 16) { // source: https://stackoverflow.com/questions/28621247/hex-to-decimal-swift
            decimalCalculation.text = "\(value)"
            decimalStepper.value = Double(value)
        }
    }
    func calculateHex(decimalValue:Int) {
        let value = String(decimalValue, radix: 16) // source: https://stackoverflow.com/questions/27189338/swift-native-functions-to-have-numbers-as-hex-strings
        hexCalculation.text = "0x\(value)"
    }
    
    @IBAction func stepperUpdate(_ sender: Any) {
        let newDecimal:Int = Int(decimalStepper.value)
        decimalCalculation.text = "\(newDecimal)"
        calculateHex(decimalValue: newDecimal)
    }
    @IBAction func decimalUpdate(_ sender: Any) {
        hexInput.text = ""
        if !decimalInput.text!.isEmpty {
            let newDecimal:Int = Int(decimalInput.text!)!
            decimalCalculation.text = "\(newDecimal)"
            decimalStepper.value = Double(newDecimal)
            calculateHex(decimalValue: newDecimal)
        }
    }
    
    func containsOnlyHexCharacters(input: String) -> Bool {
       for chr in input {
          if !(chr >= "a" && chr <= "f") && !(chr >= "A" && chr <= "F") && !(chr >= "0" && chr <= "9") {
             return false
          }
       }
       return true
    }
    
    @IBAction func hexUpdate(_ sender: Any) {
        decimalInput.text = ""
        if !hexInput.text!.isEmpty {
            if containsOnlyHexCharacters(input: hexInput.text!) {
                let newHex:String = hexInput.text!
                hexCalculation.text = "0x\(newHex)"
                calculateDecimal(hexValue: newHex)
            } else {
                //create a UIAlertController object
                let alert=UIAlertController(title: "Warning", message: "Input only hexadecimal characters", preferredStyle: UIAlertController.Style.alert)
                //create a UIAlertAction object for the button
                let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
                alert.addAction(cancelAction) //adds the alert action to the alert object
                let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
                    self.decimalStepper.value = 0
                    self.hexCalculation.text? = "0x0"
                    self.decimalCalculation.text? = "0"
                    self.hexInput.text = ""
                })
                alert.addAction(okAction)
                present(alert, animated: true, completion: nil)
            }
        }
    }
}
