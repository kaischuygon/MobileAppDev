//
//  ViewController.swift
//  daVinci
//
//  Created by Kai Schuyler on 8/31/21.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBOutlet weak var imageBox: UIImageView!
    
    @IBOutlet weak var titleBar: UILabel!
    
    
    @IBAction func chooseArt(_ sender: UIButton) {
        if sender.tag == 1 {
            imageBox.image = UIImage(named: "wilderness")
            titleBar.text = "Wilderness"
        } else {
            imageBox.image = UIImage(named: "screw")
            titleBar.text = "Screw"
        }
    }
    
    
}
