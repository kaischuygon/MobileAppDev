//
//  ViewController.swift
//  multipleViewControllers
//
//  Created by Kai Schuyler on 9/30/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var bookLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    
    var user = Favorite()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func unwindSegue(_ unwindSegue: UIStoryboardSegue) {
        // let sourceViewController = unwindSegue.source
        // Use data from the view controller which initiated the unwind segue
        bookLabel.text = user.favBook
        authorLabel.text = user.favAuthor
    }
}
