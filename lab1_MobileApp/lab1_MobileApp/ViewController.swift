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
    
    @IBOutlet var albumCover: UIImageView!
    
    @IBAction func dreamlandButton(_ sender: Any) {
        albumCover.image = UIImage(named: "dreamland")
    }
    @IBAction func humanButon(_ sender: Any) {
        albumCover.image = UIImage(named: "howToBeAHumanBeing")
    }
    @IBAction func zabaButton(_ sender: Any) {
        albumCover.image = UIImage(named: "zaba")
    }
    
}

