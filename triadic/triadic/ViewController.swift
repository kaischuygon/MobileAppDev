//
//  ViewController.swift
//  triadic
//
//  Created by Kai Schuyler on 9/28/21.
//

import UIKit
import Foundation

class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var primaryColorImage: UIImageView!
    @IBOutlet weak var primaryColorTextfield: UITextField!
    
    @IBOutlet weak var secondaryColorImage: UIImageView!
    @IBOutlet weak var secondaryColorLabel: UILabel!
    
    @IBOutlet weak var tertiaryColorImage: UIImageView!
    @IBOutlet weak var tertiaryColorLabel: UILabel!
    
    @IBOutlet weak var redSlider: UISlider!
    @IBOutlet weak var greenSlider: UISlider!
    @IBOutlet weak var blueSlider: UISlider!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
        // setup all sliders, images, etc.
        let (redFloat, greenFloat, blueFloat) = calculateRGB(h: "FF00FF")
        redSlider.value = redFloat
        greenSlider.value = greenFloat
        blueSlider.value = blueFloat
        primaryColorTextfield.text = "FF00FF"
        updatePrimaryFromSliders()
        calculateTriad()
        
        // delegate textbox
        primaryColorTextfield.delegate = self
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
        
    func calculateHex(red:Int, green:Int, blue:Int) -> String {
        var (rHex, gHex, bHex) = (String(red, radix:16), String(green, radix:16), String(blue, radix:16))
        // force 2 characters for each value
        if rHex.count < 2 {
            rHex = "0" + rHex
        }
        if gHex.count < 2 {
            gHex = "0" + gHex
        }
        if bHex.count < 2 {
            bHex = "0" + bHex
        }
        return "\(rHex)\(gHex)\(bHex)"
    }
    
    func calculateRGB(h:String) -> (Float, Float, Float) {
        let red = Float(Int(h.prefix(2), radix: 16)!)
        let green = Float(Int(h.dropFirst(2).prefix(2), radix: 16)!)
        let blue = Float(Int(h.dropFirst(2).dropFirst(2).prefix(2), radix: 16)!)
        
        return (red, green, blue)
    }
    
    // Updates the primary color on screen
    func updatePrimaryFromSliders() {
        primaryColorTextfield.text = calculateHex(red: Int(redSlider.value), green: Int(greenSlider.value), blue: Int(blueSlider.value)).uppercased()
        let (red, green, blue) = (CGFloat(redSlider.value / 255), CGFloat(greenSlider.value / 255), CGFloat(blueSlider.value / 255))
        primaryColorImage.backgroundColor = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
    }
    
    func updatePrimaryFromTextfield() {
        if checkUserInput(input: primaryColorTextfield.text!) {
            let (redFloat, greenFloat, blueFloat) = calculateRGB(h:primaryColorTextfield.text!)
            redSlider.value = redFloat
            greenSlider.value = greenFloat
            blueSlider.value = blueFloat
            primaryColorImage.backgroundColor = UIColor(red: CGFloat(redSlider.value / 255), green: CGFloat(greenSlider.value / 255), blue: CGFloat(blueSlider.value / 255), alpha: 1.0)
        } else { // alert user if the input is not hexadecimal characters or not the correct length.
            //create a UIAlertController object
            let alert = UIAlertController(title: "Warning", message: "Input must be 6 hexadecimal characters", preferredStyle: UIAlertController.Style.alert)
            //create a UIAlertAction object for the button
            let cancelAction = UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
            alert.addAction(cancelAction) //adds the alert action to the alert object
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
                self.primaryColorTextfield.text = ""
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
    }
    
    // checks that  the user's input is  only hex and at maximum 6 characters long
    func checkUserInput(input: String) -> Bool {
        if input.count != 6 { // must be exactly 6 characters long
            return false
        }
        for chr in input { // check values fall between 0-9, a-f or A-F
            if !(chr >= "a" && chr <= "f") && !(chr >= "A" && chr <= "F") && !(chr >= "0" && chr <= "9") {
             return false
          }
       }
       return true
    }
    
    func calculateTriad() {
        // formula for calculating secondary HEX: original = a1a2a3, secondary = a3a1a2
        // source: http://www.webmaster-forums.net/web-design-and-graphics/color-theory-calculating-complementary-colors
        let (secondaryRed, secondaryGreen, secondaryBlue) = (blueSlider.value, redSlider.value, greenSlider.value)
        secondaryColorImage.backgroundColor = UIColor(red: CGFloat(secondaryRed / 255), green: CGFloat(secondaryGreen / 255), blue: CGFloat(secondaryBlue / 255), alpha: 1.0)
        let secondaryHex = calculateHex(red: Int(secondaryRed), green: Int(secondaryGreen), blue: Int(secondaryBlue))
        secondaryColorLabel.text = "#\(secondaryHex)".uppercased()
        
        // formula for calculating tertiary HEX: original = a1a2a3, tertiary = a2a3a1
        let (tertiaryRed, tertiaryGreen, tertiaryBlue) = (greenSlider.value, blueSlider.value, redSlider.value)
        tertiaryColorImage.backgroundColor = UIColor(red: CGFloat(tertiaryRed / 255), green: CGFloat(tertiaryGreen / 255), blue: CGFloat(tertiaryBlue / 255), alpha: 1.0)
        let tertiaryHex = calculateHex(red: Int(tertiaryRed), green: Int(tertiaryGreen), blue: Int(tertiaryBlue))
        tertiaryColorLabel.text = "#\(tertiaryHex)".uppercased()
    }
    
    @IBAction func updateTextfield(_ sender: Any) {
        updatePrimaryFromTextfield()
        calculateTriad()
    }
    @IBAction func redSliderUpdate(_ sender: Any) {
        updatePrimaryFromSliders()
        calculateTriad()
    }
    @IBAction func greenSliderUpdate(_ sender: Any) {
        updatePrimaryFromSliders()
        calculateTriad()
    }
    @IBAction func blueSliderUpdate(_ sender: Any) {
        updatePrimaryFromSliders()
        calculateTriad()
    }
    
}
