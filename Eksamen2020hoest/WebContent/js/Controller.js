/**
 * Controller
 */
"use strict"

class FormController {

	constructor(root) {
		this.root = root;

		// binding methods
		this.run = this.run.bind(this);
		this.doClick = this.run.bind(this);
	}
	
	
	
	run() {
		// event listener?
		const root = document.getElementById(root);
		const button = rootElement.querySelector(".button");
		button.addEventListener("click", this.doClick);
	}
	
	doClick() {
		console.log("clicked");
	}
}