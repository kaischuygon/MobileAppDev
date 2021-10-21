//
//  ViewController.swift
//  tipCalculator
//
//  Created by Kai Schuyler on 9/23/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var checkAmt: UITextField!
    @IBOutlet weak var tipPercent: UITextField!
    @IBOutlet weak var stepper: UIStepper!
    @IBOutlet weak var personCount: UILabel!
    @IBOutlet weak var tipAmt: UILabel!
    @IBOutlet weak var totDue: UILabel!
    @IBOutlet weak var perPersonDue: UILabel!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    @IBAction func updatePersonCount(_ sender: UIStepper) {
        personCount.text = String(format: "%.0f", stepper.value) + " Person(s)"
        updateTipTotals()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        checkAmt.delegate=self
        tipPercent.delegate=self
    }
    
    func updateTipTotals() {
        var amount:Float
        var pct:Float
        
        if checkAmt.text!.isEmpty {
            amount = 0.0
        } else {
            amount = Float(checkAmt.text!)!
        }
        
        if tipPercent.text!.isEmpty {
            pct = 0.0
        } else{
            pct = Float(tipPercent.text!)!/100
        }
        
        let numPeople = stepper.value
        let tip = amount * pct
        let total = amount + tip
        var personTotal : Float = 0.0
        if(numPeople > 0) {
            personTotal = total / Float(numPeople)
        }
        
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle = NumberFormatter.Style.currency
        tipAmt.text = currencyFormatter.string(from: NSNumber(value: tip))
        totDue.text = currencyFormatter.string(from: NSNumber(value: total))
        perPersonDue.text=currencyFormatter.string(from: NSNumber(value: personTotal))
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateTipTotals()
    }
    
}
