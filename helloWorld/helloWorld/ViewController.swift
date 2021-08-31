//
//  ViewController.swift
//  helloWorld
//
//  Created by Kai Schuyler on 8/26/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var messageText: UILabel!
    
    @IBAction func buttonPressed(_ sender: UIButton) {
        messageText.text = "Hello, World!"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
}
