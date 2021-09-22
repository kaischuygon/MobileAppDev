//
//  ViewController.swift
//  lab3_MobileApp
//
//  Created by Kai Schuyler on 9/14/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var movieTitle: UILabel!
    @IBOutlet weak var moviePoster: UIImageView!
    @IBOutlet weak var capitalSwitch: UISwitch!
    @IBOutlet weak var fontSlider: UISlider!
    
    let titles = ["fellowship of the ring", "two towers", "return of the king"]
    
    func updatePoster() {
        switch segmentedControl.selectedSegmentIndex {
        case 1:
            moviePoster.image = UIImage(named:"towers")
            movieTitle.text = titles[1]
        case 2:
            moviePoster.image = UIImage(named:"return")
            movieTitle.text = titles[2]
        default:
            moviePoster.image = UIImage(named:"fellowship")
            movieTitle.text = titles[0]
        }
    }
    func updateCaps(){
        if capitalSwitch.isOn {
            movieTitle.text = movieTitle.text?.uppercased()
        } else {
            movieTitle.text = movieTitle.text?.lowercased()
        }
    }
    @IBAction func segmentAction(_ sender: Any) {
        updatePoster()
        updateCaps()
    }
    @IBAction func switchAction(_ sender: Any) {
        updateCaps()
    }
    @IBAction func sliderAction(_ sender: UISlider) {
        let fontSize = sender.value
//        fontSizeLabel.text = String(format: "%.0f", fontSize)
        let fontSizeCGFloat = CGFloat(fontSize)
        movieTitle.font=UIFont.systemFont(ofSize: fontSizeCGFloat)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}
