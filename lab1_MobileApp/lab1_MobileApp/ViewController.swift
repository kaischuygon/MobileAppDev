//
//  ViewController.swift
//  lab1_MobileApp
//
//  Created by Kai Schuyler on 9/7/21.
//

import UIKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBOutlet var viewController: UIView!
    @IBOutlet var albumCover: UIImageView!
    @IBOutlet var glassAnimalsLabel: UILabel!
    
    @IBAction func dreamlandButton(_ sender: Any) {
        albumCover.image = UIImage(named: "dreamland")
        viewController.backgroundColor = UIColor(named: "dreamlandBg")
        glassAnimalsLabel.textColor = UIColor(named: "dreamlandFg")
    }
    @IBAction func humanButon(_ sender: Any) {
        albumCover.image = UIImage(named: "howToBeAHumanBeing")
        viewController.backgroundColor = UIColor(named: "humanBg")
        glassAnimalsLabel.textColor = UIColor(named: "humanFg")
    }
    @IBAction func zabaButton(_ sender: Any) {
        albumCover.image = UIImage(named: "zaba")
        viewController.backgroundColor = UIColor(named: "zabaBg")
        glassAnimalsLabel.textColor = UIColor(named: "zabaFg")
    }
}

